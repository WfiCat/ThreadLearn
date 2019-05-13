package cn.hbeu.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
	public static void main(String[] args) {
		Executor treadPool = getThreadPool(3, 5, 30, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(2));
		for (int i = 0; i < 10; i++) {
			//将任务交给线程池执行
			treadPool.execute(new MyRunnable(i+1));
		}
		
		//3 7
		//2 5
		//2 3
	}
	
	/**
	 * 获取线程池
	 * @param corePoolSize 线程池核心线程大小
	 * @param maximumPoolSize 线程池最多线程(核心线程+非核心线程)
	 * @param keepAliveTime 空闲时存活时间
	 * @param unit 时间单位 枚举类
	 * @param workQueue 任务队列
	 * @return 返货线程池
	 */
	public static Executor getThreadPool(int corePoolSize,
            int maximumPoolSize,
            long keepAliveTime,
            TimeUnit unit,
            BlockingQueue<Runnable> workQueue)  {
		return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		
	}
	
	
	
	
	// 测试线程
	static class MyRunnable implements Runnable{
		int num;
		/**
		 * 编号
		 * @param num
		 */
		public MyRunnable(int num) {
			super();
			this.num = num;
		}

		public void run() {
			System.out.println("开始执行任务："+num);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.err.println("结束执行任务："+num);
		}
		
	}
	
	class MyCallable implements Callable<String>{
		public String call() throws Exception {
			return null;
		}
		
	}
	
	class MyThread extends Thread{
		@Override
		public void run() {
			super.run();
			System.out.println(this.getThreadGroup());
		}
	}
	
}
