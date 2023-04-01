package com.github.luizgfranca;


import com.github.luizgfranca.semaphore.SemaphoreImpl;

import java.util.Queue;


public class MessageProducer implements Runnable {

	private final SemaphoreImpl mSemaphore;

	private final Queue<String> mQueue;

	private final String mName;

	public MessageProducer( SemaphoreImpl semaphore, Queue<String> queue, String name ) {
		mSemaphore = semaphore;
		mQueue = queue;
		mName = name;
	}

	private void log(String message) {

		System.out.println("PRODUCER " + mName + ": " + message);
	}

	@Override
	public void run() {

		int counter = 0;

		log( "started" );

		while(true) {
			mSemaphore.acquire();
			mQueue.add( "PRODUCER " + mName + ": " + "msg " + Integer.toString( counter ) );
			mSemaphore.release();
			counter ++;
		}

	}
}
