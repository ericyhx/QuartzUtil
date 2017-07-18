package quartzUtil;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description：
 *
 * @author 马肖男
 * @version 1.0
 *          Time :2017-03-17.
 */
public class TaskOperator {
	private static TaskOperator ourInstance = new TaskOperator();
	private static Logger logger = LoggerFactory.getLogger(TaskOperator.class);
	private static CopyOnWriteArrayList<BasePoint> hasCreateTask = new CopyOnWriteArrayList<>();
	private ReentrantLock lock = new ReentrantLock();
	private long startTime=0;

	public static TaskOperator getInstance() {
		return ourInstance;
	}

	private TaskOperator() {
	}
	public void executeTaskScheduled() {
        QuartzManager manager = new QuartzManager();
        Scheduler scheduled = null;
		try {
            scheduled  = manager.getScheduler();
            scheduled.start();
        }catch (Exception e){
		    e.printStackTrace();
		    if(scheduled!=null){
                try {
                    scheduled.shutdown();
                    executeTaskScheduled();
                } catch (SchedulerException e1) {
                    e1.printStackTrace();
                }
            }
        }

//		ScheduledExecutorService executor = Executors.newScheduledThreadPool(100);
//		executor.scheduleAtFixedRate(()->{
//			try{
//				if(CarTools.hasFreeCar()){
//					List<Task> taskList = TaskUtil.getInstance().getActiveTask();
//					if(taskList == null ||taskList.isEmpty()){
//						return;
//					}
//					RobotCar car =null;
//					for(Task task :taskList){
//						BasePoint startPoint=task.getPoint(task.getFMLOC());
//						int fromTag = startPoint.getMap_tag();
//						if(MapUtil.getInstance().isCounter(fromTag)){
//							if(startPoint.getMap_type()==2){
//								car= getBackCar(task);
//							}
//						}else if(MapUtil.getInstance().isWaiting(fromTag)){
//							car= getShelfCar(task);
//						}else{
//							car = getNearestCar(startPoint);
//						}
//						if(car!=null){
//							sendTask(task,car);
//							return ;
//						}
//					}
//
//				}
//			}catch (Exception e){
//				e.printStackTrace();
//				logger.error(e.getMessage());
//				executor.shutdownNow();
//				executeTaskScheduled();
//			}
//
//			},0,1000,TimeUnit.MILLISECONDS);

	}

	public static RobotCar getShelfCar(Task task) {

		return null;
	}

	public void sendTask(Task task, RobotCar car) {

	}

}
