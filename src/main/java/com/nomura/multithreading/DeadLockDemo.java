package com.nomura.multithreading;

// Java program to illustrate Deadlock
// in multithreading.
class Util {
	// Util class to sleep a thread
	static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

// This class is Resource by both threads
class Resource {
	// first synchronized method
	synchronized void test1(Resource s2) {
		System.out.println("test1-begin");
		Util.sleep(1000);

		// taking object lock of s2 enters
		// into test2 method
		s2.test2();
		System.out.println("test1-end");
	}

	// second synchronized method
	synchronized void test2() {
		System.out.println("test2-begin");
		Util.sleep(1000);
		// taking object lock of s1 enters
		// into test1 method
		System.out.println("test2-end");
	}
}

class Thread1 extends Thread {
	private Resource s1;
	private Resource s2;

	// constructor to initialize fields
	public Thread1(Resource s1, Resource s2) {
		this.s1 = s1;
		this.s2 = s2;
	}

	// run method to start a thread
	@Override
	public void run() {
		// taking object lock of s1 enters
		// into test1 method
		s1.test1(s2);
	}
}

class Thread2 extends Thread {
	private Resource s1;
	private Resource s2;

	// constructor to initialize fields
	public Thread2(Resource s1, Resource s2) {
		this.s1 = s1;
		this.s2 = s2;
	}

	// run method to start a thread
	@Override
	public void run() {
		// taking object lock of s2
		// enters into test2 method
		s2.test1(s1);
	}
}

public class DeadLockDemo {
	public static void main(String[] args) {
		// creating one object
		Resource s1 = new Resource();

		// creating second object
		Resource s2 = new Resource();

		// creating first thread and starting it
		Thread1 t1 = new Thread1(s1, s2);
		t1.start();

		// creating second thread and starting it
		Thread2 t2 = new Thread2(s1, s2);
		t2.start();

		// sleeping main thread
		Util.sleep(2000);
	}
}
