package cn.hbeu.thread.group;

/**
 * 线程组测试
 * 
 * @author Zero
 *
 */
public class ThreadGroupTest {
	public static void main(String[] args) {
		test1();
	}
	
	/**
	 * 测试
	 */
	public static void test1() {
		// 创建线程组
		ThreadGroup group1 = new ThreadGroup("线程组一");
		ThreadGroup group2 = new ThreadGroup("线程组二");
		group1.setDaemon(true);
		
		// 创建线程
//		不是静态内部类时必须加以限制
//		new Thread(group1, new ThreadGroupTest().new MyThread("线程甲"));
		//类似
		Thread t = new Thread(group2, new MyThread("线程甲2"));
		t.setDaemon(true);
		System.out.println(t.isDaemon());
		//******************
		
		new MyThread(group1, "线程甲1").start();
		new MyThread(group1, "线程乙1").start();
		// group2是后台线程组
		//******
		//注意:不要把后台线程组和后台线程（守护线程）混为一谈，
		//后台线程组的特性是最后一个线程执行完或最后一个线程被销毁时，
		//后台线程组自动销毁，线程组只是为了统一管理线程的一个方式！
		new MyThread(group2, "线程甲2").start();
		new MyThread(group2, "线程乙2").start();

	}
	
	
	// 测试线程组
	// 测试线程
	static class MyThread extends Thread {
		public MyThread(String name) {
			super(name);
		}

		public MyThread(ThreadGroup group, String name) {
			super(group, name);
		}

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + "属于：" + this.getThreadGroup() + "线程组，" + "是否是后台线程："
					+ this.isDaemon());
		}
	}
	
}
