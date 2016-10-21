package game.core.rpg.job;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import game.core.rpg.manager.CooldownManager;
import game.core.rpg.manager.CooldownType;
import game.core.rpg.manager.MapManager;
import game.core.rpg.map.GameMap;
import game.core.rpg.map.Grid;
import game.core.rpg.map.Position;
import game.core.rpg.role.Person;
import game.core.util.MapUtil;

/**
 * @author nullzZ
 *
 */
public class RoleRunJob implements Job {

	private static final Logger logger = Logger.getLogger(RoleRunJob.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		int mapId = context.getMergedJobDataMap().getInt("mapId");
		GameMap map = MapManager.getInstance().getMap(mapId);
		if (map == null) {
			return;
		}

		Iterator<Person> roles = map.getRunningRoles().values().iterator();
		while (roles.hasNext()) {
			logger.debug("[移动]开始");
			Person role = roles.next();
			// if (role.getMapId() != mapId || role.getLine() != lineId) {
			// iter.remove();
			// continue;
			// }

			if (CooldownManager.getInstance().isCooldowning(role, CooldownType.RUN)) {
				continue;
			}

			// 移动路径
			List<Position> roads = role.getRoads();

			// 路径为空或不存在
			if (roads == null || roads.size() == 0) {
				// stop;
				logger.debug("[移动]没有路径stop");
			}

			int time = 0;
			double cost = role.getPrevStep() + role.getCost() - System.currentTimeMillis();

			while (time <= 0 && roads.size() > 0) {
				// 移动距离
				double speed = role.getSpeed();
				// 第一点
				Position position = roads.remove(0);
				// 与第一拐点距离
				double distance = MapUtil.countDistance(role.getPosition(), position);
				double useTime = distance * 1000 / speed;
				cost += useTime;

				Grid destGrid = map.getGrid(position);
				role.setDirection(MapUtil.countDirection(map.getGrid(role.getPosition()), destGrid));
				MapManager.getInstance().setRolePosition(role, position);

				time = (int) cost;
				logger.debug("[移动]时间:" + time);
				if (time > 0) {
					role.setPrevStep(System.currentTimeMillis());
					role.setCost(time);
					CooldownManager.getInstance().addCooldown(role, CooldownType.RUN);
				}

			}

			// ManagerPool.playerManager.savePlayer(player);

			/** 计算区域 **/
			// 原来所在区域
			// int oldAreaId = ManagerPool.mapManager.getAreaId(old);
			// 现在所在区域
			// int newAreaId =
			// ManagerPool.mapManager.getAreaId(player.getPosition());
			// 区域未变
			// if (oldAreaId != newAreaId) {
			// ManagerPool.mapManager.playerChangeArea(player, oldAreaId,
			// newAreaId);
			// }
		}

	}

}
