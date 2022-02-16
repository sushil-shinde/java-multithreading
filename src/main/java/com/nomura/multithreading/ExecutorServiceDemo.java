package com.nomura.multithreading;

import java.util.concurrent.*;
import java.util.Date;

public class ExecutorServiceDemo {
	public static void main(String[] args) {

		// executor service with pool size of two
		ExecutorService service = Executors.newFixedThreadPool(3);

		// starting 5 threads
		for (int i = 0; i < 5; i++) {
			Worker1 worker = new Worker1();
			service.execute(worker);
		}

		service.shutdown();
	}
}

class Worker1 extends Thread {
	public void run() {
		try {
			System.out.println(this.getName() + " started: " + new Date());
			Thread.sleep(5000);
			System.out.println(this.getName() + " finished:" + new Date());
		} catch (Exception e) {
		}
	}
}
