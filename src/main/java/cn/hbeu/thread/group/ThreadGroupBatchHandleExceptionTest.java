package cn.hbeu.thread.group;

import cn.hbeu.thread.group.ThreadGroupTest.MyThread;

/**
 * �߳������
 * 
 * @author Zero
 *
 */
public class ThreadGroupBatchHandleExceptionTest {
	public static void main(String[] args) {
		// ����һ���߳���
		MyExceptionHandle myGroup = new MyExceptionHandle("�߳���zhang��");
		// �½��߳�
		new Thread(myGroup, new ThreadGroupBatchHandleExceptionTest(). new MyThread2(null, "���߳�"), "���߳�").start();;
		new ThreadGroupBatchHandleExceptionTest(). new MyThread2(myGroup,"���߳�").start();
		new ThreadGroupBatchHandleExceptionTest(). new MyThread2(myGroup,"���߳�").start();
	}
	
	
	// �������������쳣
	// �����߳�
	class MyThread2 extends Thread{
		public MyThread2(ThreadGroup group,String name) {
			super(group, name);
		}
		@Override
		public void run() {
			int a = 5/0;
		}
	}
	
	//�����쳣
	static class MyExceptionHandle extends ThreadGroup{

		public MyExceptionHandle(String name) {
			super(name);
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
	
	
}
