package game.core.rpg.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import game.core.rpg.event.RoleRunEvent;
import game.core.rpg.manager.MapEventManager;

/**
 * @author nullzZ
 *
 */
public class RoleRunJob implements Job {

	// private static final Logger logger = Logger.getLogger(RoleRunJob.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		int mapId = context.getMergedJobDataMap().getInt("mapId");
		RoleRunEvent event = new RoleRunEvent(mapId);
		MapEventManager.getInstance().addEvent(mapId, event);
	}

}
