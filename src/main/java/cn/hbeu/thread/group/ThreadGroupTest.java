package cn.hbeu.thread.group;

/**
 * �߳������
 * 
 * @author Zero
 *
 */
public class ThreadGroupTest {
	public static void main(String[] args) {
		test1();
	}
	
	/**
	 * ����
	 */
	public static void test1() {
		// �����߳���
		ThreadGroup group1 = new ThreadGroup("�߳���һ");
		ThreadGroup group2 = new ThreadGroup("�߳����");
		group1.setDaemon(true);
		
		// �����߳�
//		���Ǿ�̬�ڲ���ʱ�����������
//		new Thread(group1, new ThreadGroupTest().new MyThread("�̼߳�"));
		//����
		Thread t = new Thread(group2, new MyThread("�̼߳�2"));
		t.setDaemon(true);
		System.out.println(t.isDaemon());
		//******************
		
		new MyThread(group1, "�̼߳�1").start();
		new MyThread(group1, "�߳���1").start();
		// group2�Ǻ�̨�߳���
		//******
		//ע��:��Ҫ�Ѻ�̨�߳���ͺ�̨�̣߳��ػ��̣߳���Ϊһ̸��
		//��̨�߳�������������һ���߳�ִ��������һ���̱߳�����ʱ��
		//��̨�߳����Զ����٣��߳���ֻ��Ϊ��ͳһ�����̵߳�һ����ʽ��
		new MyThread(group2, "�̼߳�2").start();
		new MyThread(group2, "�߳���2").start();

	}
	
	
	// �����߳���
	// �����߳�
	static class MyThread extends Thread {
		public MyThread(String name) {
			super(name);
		}

		public MyThread(ThreadGroup group, String name) {
			super(group, name);
		}

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + "���ڣ�" + this.getThreadGroup() + "�߳��飬" + "�Ƿ��Ǻ�̨�̣߳�"
					+ this.isDaemon());
		}
	}
	
}
