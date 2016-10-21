package game.core.rpg.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import game.core.rpg.Config;
import game.core.rpg.map.GameMap;
import game.core.rpg.map.Grid;
import game.core.rpg.map.Position;
import game.core.rpg.role.Person;
import game.core.util.MapUtil;

public class MapManager {

	private static final Logger logger = Logger.getLogger(MapManager.class);
	private Map<Integer, GameMap> maps = new HashMap<>();
	private static final MapManager instance = new MapManager();

	private MapManager() {

	}

	public static MapManager getInstance() {
		return instance;
	}

	public void load() {
		GameMap m = MapFactory.createGameMap(1, "map1", 50, 50);
		maps.put(1, m);
		logger.info("[加载地图]");
	}

	public boolean enter(Person role) {
		GameMap map = maps.get(role.getMapId());
		if (map == null) {
			return false;
		}
		map.getRoles().put(role.getUid(), role);
		return true;
	}

	public Map<Integer, GameMap> getMaps() {
		return maps;
	}

	public GameMap getMap(int mapId) {
		return maps.get(mapId);
	}

	/**
	 * 准备移动
	 * 
	 * @param role
	 * @param pos
	 *            前端的起始位置
	 * @param roads
	 *            路径(格子key)
	 */
	public void readyRun(Person role, Position pos, List<Integer> roads) {
		int mapId = role.getMapId();
		GameMap map = this.getMap(mapId);

		if (map == null) {
			return;
		}
		Grid startGrid = map.getGrid(pos);// 前端初始点
		if (startGrid == null || startGrid.getBlock() == 1) {
			// log.error("地图 " + player.getMapModelId() + " 起始点阻挡 " +
			// position.getX() + "," + position.getY() + "----->"
			// + (startGrid == null ? "" : (startGrid.getX() + "," +
			// startGrid.getY())));
			// 清空当前移动路径
			// resetPlayerPosition(player);
			return;
		}

		double distance = MapUtil.countDistance(pos, role.getPosition());

		double allow_distance = (double) Config.DISTANCE * role.getSpeed() / Config.BASE_SPEED;
		if (allow_distance < Config.DISTANCE) {
			allow_distance = Config.DISTANCE;
		}

		// if (role.getCheckTimes() > 10 && System.currentTimeMillis() -
		// role.getCheckTime() < 60 * 60 * 1000) {
		// allow_distance = Config.DISTANCE;
		// }

		if (distance > allow_distance) {
			// log.error("player " + player.getId() + " 移动起始点距离过远, distance "
			// + distance + " allow_distance "
			// + allow_distance + ", now " + player.getPosition() + ", start "
			// + position);
			// return;
			// 清空当前移动路径
			// ManagerPool.mapManager.broadcastPlayerForceStop(player);
			return;
		}

		// 跑步中拐点距离检查
		Grid prevGrid = startGrid;
		List<Position> positions = new ArrayList<Position>();
		for (int i = 0; i < roads.size(); i++) {
			int pKey = roads.get(i);// 格子key
			int tx = pKey / 10000;
			int ty = pKey % 10000;
			Grid toGrid = new Grid(tx, ty);
			byte direct = MapUtil.countDirection(startGrid, toGrid);
			int step = MapUtil.getStep(startGrid, toGrid);

			byte[] add = MapUtil.countDirectionAddtion(direct);
			for (int j = 0; j < step; j++) {
				Grid grid = map.getGrid(new Position(prevGrid.getX() + add[0], prevGrid.getY() + add[1]));
				if (grid == null || grid.getBlock() == 1) {
					logger.debug("地图 " + role.getMapId() + "路中阻挡点：" + grid.getX() + "," + grid.getY());
					// broadcastPlayerForceStop(player);
					return;
				}
				prevGrid = grid;
				positions.add(grid.getCenter());
			}

			// // 清空当前移动路径
			// role.getRoads().clear();
			// 设置移动起点
			Position old = role.getPosition();
			role.setDirection(direct);
			// 设置移动路径
			// role.setRoads(positions);
			role.getRoads().addAll(positions);
			setRolePosition(role, startGrid.getCenter());

			// int oldAreaId = ManagerPool.mapManager.getAreaId(old);
			// // 现在所在区域
			// int newAreaId =
			// ManagerPool.mapManager.getAreaId(player.getPosition());
			// // 区域未变
			// if (oldAreaId != newAreaId) {
			// playerChangeArea(player, oldAreaId, newAreaId);
			// }

			role.setPrevStep(System.currentTimeMillis());
			role.setCost(0);
			// ManagerPool.cooldownManager.removeCooldown(player,
			// CooldownTypes.RUN, null);

			HashMap<Long, Person> runnings = map.getRunningRoles();// 开始移动
			if (!runnings.containsKey(role.getUid())) {
				runnings.put(role.getUid(), role);
			}

			// MessageUtil.tell_round_message(player, other_msg);//给地图区域的玩家发送消息

			logger.debug("[移动]准备移动结束");
		}
	}

	public void setRolePosition(Person role, Position pos) {
		role.setPosition(pos);
	}

}
