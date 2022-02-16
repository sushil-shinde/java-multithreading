package com.nomura.multithreading;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {
	public static void main(String[] args) {

		Exchanger<String> exchanger = new Exchanger<String>();

		Thread t1 = new MyThread1(exchanger, "I like coffee.");
		Thread t2 = new MyThread1(exchanger, "I like tea");
		t1.start();
		t2.start();
	}
}

class MyThread1 extends Thread {

	Exchanger<String> exchanger;
	String message;

	MyThread1(Exchanger<String> exchanger, String message) {
		this.exchanger = exchanger;
		this.message = message;
	}

	public void run() {
		try {
			System.out.println(this.getName() + " message: " + message);
			// exchange messages
			message = exchanger.exchange(message);

			System.out.println(this.getName() + " message: " + message);
		} catch (Exception e) {
		}
	}
}