package edu.eci.arsw.primefinder;

import java.util.LinkedList;
import java.util.List;

public class PrimeFinderThread extends Thread{

	
	int a,b;
	
	private List<Integer> primes=new LinkedList<Integer>();
	private volatile boolean paused = false;
	private final Object pauseLock = new Object();
	
	public PrimeFinderThread(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	public void run(){
		for (int i=a;i<=b;i++){
			synchronized(pauseLock) {
				while (paused) {
					try {
						pauseLock.wait();
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						return;
					}
				}
			}
			
			if (isPrime(i)){
				primes.add(i);
				System.out.println(i);
			}
		}
	}
	
	boolean isPrime(int n) {
	    if (n%2==0) return false;
	    for(int i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}

	public List<Integer> getPrimes() {
		return primes;
	}
	
	/**
	 * Pausa la ejecución del hilo
	 */
	public void pauseThread() {
		synchronized(pauseLock) {
			paused = true;
		}
	}
	
	/**
	 * Reanuda la ejecución del hilo
	 */
	public void resumeThread() {
		synchronized(pauseLock) {
			paused = false;
			pauseLock.notifyAll();
		}
	}
	
	/**
	 * Obtiene el número de primos encontrados hasta el momento
	 */
	public int getPrimesCount() {
		return primes.size();
	}
	
}
