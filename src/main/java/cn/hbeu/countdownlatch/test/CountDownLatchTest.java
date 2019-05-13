package cn.hbeu.countdownlatch.test;

import java.util.concurrent.CountDownLatch;

/**
 * ������ԣ������߳�ִ��������ʱ��
 * @author Zero
 *
 */
public class CountDownLatchTest {
	public static void main(String[] args) throws InterruptedException {
		// ����ֻ�ǻ�ȡ�����߳�ִ��������ʱ��
		test1();
		// ��ȷ��
		test2();
	}

	private static void test1() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) { 
			// ����10���̺߳��ڹ����ǣ���������ִ��
			// ���⣺������ִ����������ִ��
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
		System.out.println("����ʱ��Ϊ��"+(end-start));
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
					countDownLatch.countDown(); //������һ
				}
			}).start();
		}
		countDownLatch.await(); // һֱ�ȴ�ֱ������Ϊ0
		long end = System.currentTimeMillis();
		System.out.println("����ʱ��Ϊ��"+(end-start));
		
	}
}
