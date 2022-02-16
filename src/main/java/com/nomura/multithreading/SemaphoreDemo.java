package com.nomura.multithreading;

import java.util.concurrent.*;

public class SemaphoreDemo {

	public static void main(String args[]) {
		Semaphore semaphore = new Semaphore(2);
		MyThread2 mt1 = new MyThread2(semaphore, "T1");
		MyThread2 mt2 = new MyThread2(semaphore, "T2");
		MyThread2 mt3 = new MyThread2(semaphore, "T3");
		MyThread2 mt4 = new MyThread2(semaphore, "T4");
		mt1.start();
		mt2.start();
		mt3.start();
		mt4.start();
	}
}

class MyThread2 extends Thread {
	Semaphore semaphore;

	MyThread2(Semaphore semaphore, String name) {
		super(name);
		this.semaphore = semaphore;
	}

	public void run() {
		try {
			semaphore.acquire();
			System.out.println("Hello " + this.getName());
			try {
				sleep(2000);
			} catch (Exception e) {
			}
		} catch (InterruptedException ie) {
		} finally {
			semaphore.release();
			System.out.println("Goodbye " + this.getName());
		}
	}

}
