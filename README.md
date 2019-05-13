#线程
1.同步辅助类（类似计数器）countDwonLatch cdl = new countDwonLatch(10);
>1.cdl.countDown()--计数减一</br>
>2.cdl.await()------直到计数器为零时才开始朝下执行

```
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


```

2.ThreadLocal()

#线程组
1.注意:不要把后台线程组和后台线程（守护线程）混为一谈，</br>
后台线程组的特性是最后一个线程执行完或最后一个线程被销毁时，</br>

后台线程组自动销毁，线程组只是为了统一管理线程的一个方式！

2.线程组设置为后台线程组，里面不一定都是后台线程<br/>
后台线程需要自己设置

3.线程组主要集中在安全方面：
>1.不同线程不可互相修改值</br>
>2.使用线程组（uncaughtException(Thread t, Throwable e)需要重新）批量处理多个线程异常</br>
将线程放入重写（uncaughtException(Thread t, Throwable e)线程组中</br>
线程组会自动处理异常

```  
    //处理异常
	class MyExceptionHandle extends ThreadGroup{

		public MyExceptionHandle(String name) {
			super(name);
		}
		public MyExceptionHandle(ThreadGroup parent,String name) {
			super(parent, name);
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
```
---------------

#线程池
1.新建一个线程池pool
>>>
	 * @param corePoolSize 线程池核心线程大小
	 * @param maximumPoolSize 线程池最多线程(核心线程+非核心线程)
	 * @param keepAliveTime 空闲时存活时间
	 * @param unit 时间单位 枚举类
	 * @param workQueue 任务队列
	 * @return 返货线程池
------
     //将任务交给线程池执行
	 pool.execute(new MyRunnable(i+1));
	 大体上处理流程：
	 当核心线程<corePoolSize时创建核心线程执行任务,
	 核心线程满了，将任务放入阻塞队列
	 阻塞队列满了，(线程总数<maximumPoolSize)时创建非核心线程执行任务，
	 非核心线程满了，抛出异常
######注意：不同的阻塞队列线程执行流程有细微差别



	 