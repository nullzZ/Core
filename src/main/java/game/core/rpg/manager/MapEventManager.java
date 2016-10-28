package game.core.rpg.manager;

import java.util.HashMap;

import org.apache.log4j.Logger;

import game.core.event.EventThread;
import game.core.event.IEvent;
import game.core.rpg.event.MapEventThread;
import game.core.rpg.map.GameMap;

/**
 * 地图事件管理器
 * 
 * @author nullzZ
 *
 */
public class MapEventManager {

	private static final Logger logger = Logger.getLogger(MapEventManager.class);
	private static MapEventManager instance = new MapEventManager();
	private EventThread[] threads;
	private HashMap<Integer, EventThread> map_thread = new HashMap<>();

	private MapEventManager() {

	}

	public static MapEventManager getInstance() {
		return instance;
	}

	public void init(int threadSize) {
		threads = new MapEventThread[threadSize];
		for (int i = 0; i < threadSize; i++) {
			threads[i] = new MapEventThread();
		}
		for (GameMap map : MapManager.getInstance().getMaps().values()) {
			int index = map.getMapId() % 3;
			map_thread.put(map.getMapId(), threads[index - 1]);
		}
	}

	public void addEvent(int mapId, IEvent event) {
		EventThread eventTread = map_thread.get(mapId);
		if (eventTread == null) {
			logger.error("[地图事件线程异常]");
			return;
		}
		eventTread.addEvent(event);
	}
}
