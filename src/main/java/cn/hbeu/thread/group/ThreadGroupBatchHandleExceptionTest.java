package cn.hbeu.thread.group;

import cn.hbeu.thread.group.ThreadGroupTest.MyThread;

/**
 * 线程组测试
 * 
 * @author Zero
 *
 */
public class ThreadGroupBatchHandleExceptionTest {
	public static void main(String[] args) {
		// 创建一个线程组
		MyExceptionHandle myGroup = new MyExceptionHandle("线程组zhang：");
		// 新建线程
		new Thread(myGroup, new ThreadGroupBatchHandleExceptionTest(). new MyThread2(null, "乙线程"), "乙线程").start();;
		new ThreadGroupBatchHandleExceptionTest(). new MyThread2(myGroup,"甲线程").start();
		new ThreadGroupBatchHandleExceptionTest(). new MyThread2(myGroup,"甲线程").start();
	}
	
	
	// 测试批量处理异常
	// 测试线程
	class MyThread2 extends Thread{
		public MyThread2(ThreadGroup group,String name) {
			super(group, name);
		}
		@Override
		public void run() {
			int a = 5/0;
		}
	}
	
	//处理异常
	static class MyExceptionHandle extends ThreadGroup{

		public MyExceptionHandle(String name) {
			super(name);
		}
		/**
		 * 重写异常处理
		 * @param t 捕获的线程
		 * @param e 捕获异常时抛出的异常
		 */
		@Override
		public void uncaughtException(Thread t, Throwable e) {
			System.out.println(t.getThreadGroup()+":"+t.getName()+"抛出："+e);
		}
	}
	
	
}
