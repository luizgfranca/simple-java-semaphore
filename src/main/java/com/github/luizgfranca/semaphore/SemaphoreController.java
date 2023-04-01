package com.github.luizgfranca.semaphore;


import java.util.LinkedList;
import java.util.Queue;


public class SemaphoreController {

	private int mAvailableResources;
	private final Queue<Thread> mThreadsWaitingQueue;

	public SemaphoreController(int numberOfResources) {
		mAvailableResources = numberOfResources;
		mThreadsWaitingQueue = new LinkedList<>();
	}

	public boolean saveWaitingThread(Thread resourceRequester) {
		if(mAvailableResources > 0) {
			mAvailableResources --;
			return false;
		}

		mThreadsWaitingQueue.add( resourceRequester );
		return true;
	}

	public void freeNextWaitingThread() {
		var threadToFree = mThreadsWaitingQueue.poll();

		if(threadToFree == null) {
			mAvailableResources ++;
			return;
		}

		threadToFree.interrupt();
	}

}
