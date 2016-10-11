package game.core.statistics;

import java.util.HashMap;
import java.util.Map;

/**
 * action统计
 * 
 * @author nullzZ
 *
 */
public class StatisticsAction {
	private static final StatisticsAction instance = new StatisticsAction();
	private Map<Integer, ActionModel> actionMap = new HashMap<>();
	
	private StatisticsAction() {

	}

	public static final StatisticsAction getInstance() {
		return instance;
	}

	public void STaction(int cmd, float prossTime) {
		if (actionMap.containsKey(cmd)) {
			ActionModel am = actionMap.get(cmd);
			addActionCount(am);
		} else {
			ActionModel am = new ActionModel();
			am.setCmd(cmd);
			am.setCountSum(1);
			am.setCurrCount(1);
			am.setProssMaxTime(prossTime);
			actionMap.put(cmd, am);
		}
	}

	private void addActionCount(ActionModel am) {
		am.setCurrCount(am.getCurrCount() + 1);
		am.setCountSum(am.getCountSum() + 1);
	}

	public Map<Integer, ActionModel> getActionMap() {
		return actionMap;
	}

}
