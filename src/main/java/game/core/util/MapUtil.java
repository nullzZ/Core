package game.core.util;

import game.core.rpg.map.Grid;
import game.core.rpg.map.Position;

/**
 * @author nullzZ
 *
 */
public class MapUtil {
	/**
	 * 计算2点间距离(返回像素)
	 *
	 * @param pos1
	 * @param pos2
	 * @return
	 */
	public static double countDistance(Position pos1, Position pos2) {
		return countDistance(pos1.getX(), pos1.getY(), pos2.getX(), pos2.getY());
	}

	/**
	 * 计算2点间距离(返回像素)
	 *
	 * @param sx
	 *            开始点X
	 * @param sy
	 *            开始点Y
	 * @param tx
	 *            结束点X
	 * @param ty
	 *            结束点Y
	 * @return
	 */
	public static double countDistance(int sx, int sy, int tx, int ty) {
		return Math.sqrt(Math.pow((sx - tx), 2) + Math.pow((sy - ty), 2));
	}

	/**
	 * 俩格子间的步数
	 * 
	 * @param sx
	 * @param sy
	 * @param tx
	 * @param ty
	 * @return
	 */
	public static int getStep(Grid grid1, Grid grid2) {
		return (int) Math.floor(countDistance(grid1.getX(), grid1.getY(), grid2.getX(), grid2.getY()));
	}

	public static byte[] countDirectionAddtion(int dir) {
		byte[] add = new byte[2];
		// 坐标2相对坐标1的角度 7：↖， 6：←， 5：↙， 4：↓， 3：↘， 2：→，1：↗，0：↑
		switch (dir) {
		case 0:
			add[1] = -1;
			break;
		case 1:
			add[1] = -1;
			add[0] = 1;
			break;
		case 2:
			add[0] = 1;
			break;
		case 3:
			add[1] = 1;
			add[0] = 1;
			break;
		case 4:
			add[1] = 1;
			break;
		case 5:
			add[1] = 1;
			add[0] = -1;
			break;
		case 6:
			add[0] = -1;
			break;
		case 7:
			add[1] = -1;
			add[0] = -1;
			break;
		}

		return add;
	}

	/**
	 * 计算角度（格子1和格子2相邻）
	 *
	 * @return 格子2相对格子1的角度 7：↖， 6：←， 5：↙， 4：↓， 3：↘， 2：→，1：↗，0：↑
	 */
	public static byte countDirection(Grid grid1, Grid grid2) {
		if (grid1.getY() == grid2.getY() && grid1.getX() == grid2.getX()) {
			return (byte) 4;
		} else if (grid1.getY() == grid2.getY()) {
			if (grid1.getX() < grid2.getX()) {
				return (byte) 2;
			} else {
				return (byte) 6;
			}
		} else if (grid1.getX() == grid2.getX()) {
			if (grid1.getY() < grid2.getY()) {
				return (byte) 4;
			} else {
				return (byte) 0;
			}
		} else if (grid1.getX() < grid2.getX()) {
			if (grid1.getY() < grid2.getY()) {
				return (byte) 3;
			} else {
				return (byte) 1;
			}
		} else {
			if (grid1.getY() < grid2.getY()) {
				return (byte) 5;
			} else {
				return (byte) 7;
			}
		}
	}

}
