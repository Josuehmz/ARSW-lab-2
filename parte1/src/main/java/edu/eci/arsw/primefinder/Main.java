package edu.eci.arsw.primefinder;

public class Main {

	public static void main(String[] args) {
		int start = 0;
		int end = 50000000;
		int range = (end-start)/3;
		PrimeFinderThread pft1=new PrimeFinderThread(start, start+range);
		PrimeFinderThread pft2=new PrimeFinderThread(start+range+1, start+2*range);
		PrimeFinderThread pft3=new PrimeFinderThread(start+2*range+1, end);
		
		
		pft1.start();
		pft2.start();
		pft3.start();
		
	}
	
}
