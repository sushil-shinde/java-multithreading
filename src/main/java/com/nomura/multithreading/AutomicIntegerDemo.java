package com.nomura.multithreading;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public class AutomicIntegerDemo {

	public static void main(String[] args) throws InterruptedException {
		final Counter counter = new Counter();
		System.out.println("Count: " + counter.getCount());

		// create 10 threads
		ArrayList<Task> threads = new ArrayList<Task>();
		for (int x = 0; x < 10; x++) {
			threads.add(new Task(counter));
		}

		// start all of the threads
		Iterator<Task> i1 = threads.iterator();
		while (i1.hasNext()) {
			Task mt = (Task) i1.next();
			mt.start();
		}

		// wait for all the threads to finish
		Iterator<Task> i2 = threads.iterator();
		while (i2.hasNext()) {
			Task mt = (Task) i2.next();
			mt.join();
		}

		System.out.println("Count: " + counter.getCount());
	}
}

// thread that increments the counter 10 times.
class Task extends Thread {
	Counter counter;

	Task(Counter counter) {
		this.counter = counter;
	}

	public void run() {
		for (int x = 0; x < 10; x++) {
			System.out.println("current count = " + counter.getCount());
			counter.incrementCount();
		}
	}
}

// class that uses AtomicInteger for counter
class Counter {
	private AtomicInteger count = new AtomicInteger(0);

	public void incrementCount() {
		count.incrementAndGet();
	}

	public int getCount() {
		return count.get();
	}
}
