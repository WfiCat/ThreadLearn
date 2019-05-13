package cn.hbeu.countdownlatch.test;

import java.util.concurrent.CountDownLatch;

/**
 * 本意测试：所有线程执行完所用时间
 * @author Zero
 *
 */
public class CountDownLatchTest {
	public static void main(String[] args) throws InterruptedException {
		// 错误：只是获取到主线程执行完所用时间
		test1();
		// 正确的
		test2();
	}

	private static void test1() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) { 
			// 开辟10个线程后不在管他们，继续往下执行
			// 本意：等他们执行完在往下执行
			new Thread(new Runnable() {
				
				public void run() {
					for (int j = 0; j < 10000; j++) {
						// todo
						Object object = new Object();
					}
				}
			}).start();
		}
		long end = System.currentTimeMillis();
		System.out.println("所有时间为："+(end-start));
	}

	private static void test2() throws InterruptedException {
		int threadCount = 10;
		final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
		long start = System.currentTimeMillis();
		for (int i = 0; i < threadCount; i++) {  //
			new Thread(new Runnable() {
				
				public void run() {
					for (int j = 0; j < 10000; j++) {
						// todo
						Object object = new Object();
					}
					countDownLatch.countDown(); //计数减一
				}
			}).start();
		}
		countDownLatch.await(); // 一直等待直到计数为0
		long end = System.currentTimeMillis();
		System.out.println("所有时间为："+(end-start));
		
	}
}
