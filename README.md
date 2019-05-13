#�߳�
1.ͬ�������ࣨ���Ƽ�������countDwonLatch cdl = new countDwonLatch(10);
>1.cdl.countDown()--������һ</br>
>2.cdl.await()------ֱ��������Ϊ��ʱ�ſ�ʼ����ִ��

```
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


```

2.ThreadLocal()

#�߳���
1.ע��:��Ҫ�Ѻ�̨�߳���ͺ�̨�̣߳��ػ��̣߳���Ϊһ̸��</br>
��̨�߳�������������һ���߳�ִ��������һ���̱߳�����ʱ��</br>

��̨�߳����Զ����٣��߳���ֻ��Ϊ��ͳһ�����̵߳�һ����ʽ��

2.�߳�������Ϊ��̨�߳��飬���治һ�����Ǻ�̨�߳�<br/>
��̨�߳���Ҫ�Լ�����

3.�߳�����Ҫ�����ڰ�ȫ���棺
>1.��ͬ�̲߳��ɻ����޸�ֵ</br>
>2.ʹ���߳��飨uncaughtException(Thread t, Throwable e)��Ҫ���£������������߳��쳣</br>
���̷߳�����д��uncaughtException(Thread t, Throwable e)�߳�����</br>
�߳�����Զ������쳣

```  
    //�����쳣
	class MyExceptionHandle extends ThreadGroup{

		public MyExceptionHandle(String name) {
			super(name);
		}
		public MyExceptionHandle(ThreadGroup parent,String name) {
			super(parent, name);
		}
		
		/**
		 * ��д�쳣����
		 * @param t ������߳�
		 * @param e �����쳣ʱ�׳����쳣
		 */
		@Override
		public void uncaughtException(Thread t, Throwable e) {
			System.out.println(t.getThreadGroup()+":"+t.getName()+"�׳���"+e);
		}
	}
```
---------------

#�̳߳�
1.�½�һ���̳߳�pool
>>>
	 * @param corePoolSize �̳߳غ����̴߳�С
	 * @param maximumPoolSize �̳߳�����߳�(�����߳�+�Ǻ����߳�)
	 * @param keepAliveTime ����ʱ���ʱ��
	 * @param unit ʱ�䵥λ ö����
	 * @param workQueue �������
	 * @return �����̳߳�
------
     //�����񽻸��̳߳�ִ��
	 pool.execute(new MyRunnable(i+1));
	 �����ϴ������̣�
	 �������߳�<corePoolSizeʱ���������߳�ִ������,
	 �����߳����ˣ������������������
	 �����������ˣ�(�߳�����<maximumPoolSize)ʱ�����Ǻ����߳�ִ������
	 �Ǻ����߳����ˣ��׳��쳣
######ע�⣺��ͬ�����������߳�ִ��������ϸ΢���



	 