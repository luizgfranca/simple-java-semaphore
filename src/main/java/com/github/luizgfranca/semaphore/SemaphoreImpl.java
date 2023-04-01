package com.github.luizgfranca.semaphore;


import java.util.LinkedList;
import java.util.Queue;


public class SemaphoreImpl {

	private final SemaphoreController mSemaphoreController;

	public SemaphoreImpl( int numberOfResources ) {
		mSemaphoreController = new SemaphoreController( numberOfResources );
	}

	public void acquire() {
		var currentThread = Thread.currentThread();
		boolean isSetToWait = true;

		synchronized ( mSemaphoreController ) {
			isSetToWait = mSemaphoreController.saveWaitingThread( currentThread );
		}

		if(isSetToWait) {
			while ( true ) {
				try {
					Thread.sleep(Long.MAX_VALUE);
				} catch ( InterruptedException e ) {
					return;
				}
			}
		}
	}

	public void release() {

		synchronized ( mSemaphoreController ) {
			mSemaphoreController.freeNextWaitingThread();
		}

	}

}
