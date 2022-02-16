package com.nomura.multithreading;

import java.util.concurrent.*;

public class ProducerConsumerDemo {
	public static void main(String[] args) throws Exception {
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);

		System.out.println("Starting Producer.");
		new Thread(producer).start();
		System.out.println("Starting Consumer.");
		new Thread(consumer).start();
	}
}

// Producer class. It sleeps 3 seconds between each put() call. 
// This will cause the Consumer to block, while waiting for objects in the queue. 
class Producer implements Runnable {
	protected BlockingQueue<String> queue = null;

	public Producer(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			for (int i = 0; i <= 49; i++) {
				queue.put(i + "");
				System.out.println("Added element " + i);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

// Consumer class. It takes out the objects from the queue, 
// and prints them to System.out. 
class Consumer implements Runnable {
	protected BlockingQueue<String> queue = null;

	public Consumer(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			for (int i = 0; i <= 49; i++) {
				queue.take();
				//System.out.println(queue.take());
				System.out.println("Got element " + i);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
