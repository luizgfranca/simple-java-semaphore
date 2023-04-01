package com.github.luizgfranca;


import com.github.luizgfranca.semaphore.SemaphoreImpl;

import java.util.Queue;
import java.util.logging.Logger;


public class MessageConsumer implements Runnable{

	private final SemaphoreImpl mSemaphore;

	private final Queue<String> mQueue;

	private final String mName;

	public MessageConsumer( SemaphoreImpl semaphore, Queue<String> queue, String name ) {
		mSemaphore = semaphore;
		mQueue = queue;
		mName = name;
	}

	private void log(String message) {

		System.out.println("CONSUMER " + mName + ": " + message);
	}

	@Override public void run() {

		log( "started" );

		while(true) {
			mSemaphore.acquire();
			var item = mQueue.poll();
			var size = mQueue.size();
			mSemaphore.release();

			if(item == null) {
				log( "no item found to process" );
				return;
			}

			log( "<" + item + ">" + "; waiting on queue: " + size);
		}

	}
}
