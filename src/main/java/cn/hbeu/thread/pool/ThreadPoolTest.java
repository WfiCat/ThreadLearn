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
			//�����񽻸��̳߳�ִ��
			treadPool.execute(new MyRunnable(i+1));
		}
		
		//3 7
		//2 5
		//2 3
	}
	
	/**
	 * ��ȡ�̳߳�
	 * @param corePoolSize �̳߳غ����̴߳�С
	 * @param maximumPoolSize �̳߳�����߳�(�����߳�+�Ǻ����߳�)
	 * @param keepAliveTime ����ʱ���ʱ��
	 * @param unit ʱ�䵥λ ö����
	 * @param workQueue �������
	 * @return �����̳߳�
	 */
	public static Executor getThreadPool(int corePoolSize,
            int maximumPoolSize,
            long keepAliveTime,
            TimeUnit unit,
            BlockingQueue<Runnable> workQueue)  {
		return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		
	}
	
	
	
	
	// �����߳�
	static class MyRunnable implements Runnable{
		int num;
		/**
		 * ���
		 * @param num
		 */
		public MyRunnable(int num) {
			super();
			this.num = num;
		}

		public void run() {
			System.out.println("��ʼִ������"+num);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.err.println("����ִ������"+num);
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
