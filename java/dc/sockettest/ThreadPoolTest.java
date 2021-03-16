//**********************************************
//文件名：ThreadPoolTest
//运行空间：java/dc/sockettest
//功能：源代码中自带的，可省略
//作者：丁源 18030100116
//生成日期：21:52 2021/3/16
//修改日志：（日期：修改信息）



//*********************************************
package dc.sockettest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
     public static void main(String[] args) {   
         ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
          
         for(int i=0;i<15;i++){
             MyTask myTask = new MyTask(i);
             executor.execute(myTask);
             System.out.println("The number of threads in the ThreadPool:"+executor.getPoolSize());
			 System.out.println("The number of tasks in the Queue:" + executor.getQueue().size());
             System.out.println("The number of tasks completed:"+executor.getCompletedTaskCount());
         }
         executor.shutdown();
     }
}
 
 
class MyTask implements Runnable {
    private int taskNum;
     
    public MyTask(int num) {
        this.taskNum = num;
    }
     
    @Override
    public void run() {
		int sum = 0;
		
        System.out.println("Task"+taskNum+"is running!");
		
        try {
			for(int i=0; i<15; i++) {
				sum += i;
			}
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task "+taskNum+" has been done!");
    }
}