package game.core.rpg.manager;

import game.core.rpg.Config;
import game.core.rpg.map.GameMap;
import game.core.rpg.map.Grid;
import game.core.rpg.map.Position;

/**
 * @author nullzZ
 *
 */
public class MapFactory {

	public static GameMap createGameMap(int mapId, String name, int height, int width) {
		GameMap map = new GameMap(mapId, name, height, width);
		Grid[][] grids = map.grids;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				grids[i][j] = new Grid(i, j);
				grids[i][j].setCenter(new Position((grids[i][j].getX() * Config.GRID_BORDER + Config.GRID_BORDER / 2),
						(grids[i][j].getY() * Config.GRID_BORDER + Config.GRID_BORDER / 2)));
			}
		}
		return map;
	}
}
