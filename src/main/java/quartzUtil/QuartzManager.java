package quartzUtil;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;


/**
 * Created by erita on 2017/7/18.
 */
public class QuartzManager {
    private static String JOB_GROUP = "TASK_JOB_GROUP";
    private static String TRIGGER_GROUP="TASK_TRIGGER_GROUP";
    /**
     * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
     *
     */
    public Scheduler getScheduler() throws Exception {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDetail job = JobBuilder.newJob(TaskJob.class).withIdentity("Task_JOB", JOB_GROUP)
                .build();
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInMilliseconds(1000).repeatForever();
        // Trigger the job to run now, and then repeat every 40 seconds
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("Task_Trigger", TRIGGER_GROUP)
                .startNow()
                .withSchedule(simpleScheduleBuilder)
                .build();

        // Tell quartz to schedule the job using our trigger

        scheduler.scheduleJob(job, trigger);
        return scheduler;
    }

}
