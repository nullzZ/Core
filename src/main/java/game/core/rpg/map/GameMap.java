package game.core.rpg.map;

import java.util.HashMap;
import java.util.Map;

import game.core.rpg.Config;
import game.core.rpg.role.Person;

/**
 * @author nullzZ
 *
 */
public class GameMap {

	private int mapId;
	private String name;
	private int height = 100;
	private int width = 100;
	public Grid[][] grids;

	private Map<Long, Person> roles = new HashMap<>();
	// 跑动玩家列表
	private HashMap<Long, Person> runningRoles = new HashMap<>();

	public GameMap(int mapId, String name, int height, int width) {
		this.mapId = mapId;
		this.name = name;
		this.height = height;
		this.width = width;
		grids = new Grid[width][height];
	}

	public Grid getGrid(Position pos) {
		int y = pos.getY() / Config.GRID_BORDER;
		int x = pos.getX() / Config.GRID_BORDER;
		if (x < 0 || x >= grids[0].length || y < 0 || y >= grids.length) {
			return null;
		}
		return grids[y][x];
	}

	public Map<Long, Person> getRoles() {
		return roles;
	}

	public HashMap<Long, Person> getRunningRoles() {
		return runningRoles;
	}

	public int getMapId() {
		return mapId;
	}

	/**
	 * 进入地图限制
	 * 
	 * @return
	 */
	// public abstract boolean mapEnterLimit();

}
