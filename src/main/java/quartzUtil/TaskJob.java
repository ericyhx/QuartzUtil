package quartzUtil;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * Created by erita on 2017/7/18.
 */
public class TaskJob implements Job {

    private Logger logger = LoggerFactory.getLogger(TaskJob.class);

    public TaskJob() {
    }
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try{
            if(CarTools.hasFreeCar()){
                List<Task> taskList = TaskUtil.getInstance().getActiveTask();
                if(taskList == null ||taskList.isEmpty()){
                    return;
                }
                RobotCar car =null;
                for(Task task :taskList){
                    BasePoint startPoint=task.getPoint(task.getFMLOC());
                    int fromTag = startPoint.getMap_tag();
                    if(MapUtil.getInstance().isCounter(fromTag)){
                        if(startPoint.getMap_type()==2){
                        }
                    }else if(MapUtil.getInstance().isWaiting(fromTag)){
                        car= TaskOperator.getShelfCar(task);
                    }else{
                    }
                    if(car!=null){
                        TaskOperator.getInstance().sendTask(task,car);
                        return ;
                    }
                }

            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
