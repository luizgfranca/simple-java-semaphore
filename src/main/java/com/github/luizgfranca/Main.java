package com.github.luizgfranca;


import com.github.luizgfranca.semaphore.SemaphoreImpl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Main {

	private static final int NUMBER_OF_PRODUCERS = 10;
	private static final int NUMBER_OF_CONSUMERS = 2;

	public static void main( String[] args )
		throws InterruptedException {

		List<Thread> producers = new ArrayList<>();
		List<Thread> consumers = new ArrayList<>();

		var semaphore = new SemaphoreImpl(1);
		Queue<String> messageQueue = new LinkedList<>();

		for(int i = 0; i < NUMBER_OF_PRODUCERS; i ++) {
			producers.add( new Thread( new MessageProducer( semaphore, messageQueue, Integer.toString( i ) )) );
		}

		for(int i = 0; i < NUMBER_OF_CONSUMERS; i ++) {
			consumers.add( new Thread(new MessageConsumer( semaphore, messageQueue, Integer.toString( i ) )) );
		}

		producers.forEach( Thread::start );
		consumers.forEach( Thread::start );

		for ( Thread producer : producers ) { producer.join(); }
		for ( Thread consumer : consumers ) { consumer.join(); }

	}
}