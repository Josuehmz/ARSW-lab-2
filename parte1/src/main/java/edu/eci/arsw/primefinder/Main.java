package edu.eci.arsw.primefinder;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int start = 0;
		int end = 500000000;
		int range = (end-start)/3;
		PrimeFinderThread pft1=new PrimeFinderThread(start, start+range);
		PrimeFinderThread pft2=new PrimeFinderThread(start+range+1, start+2*range);
		PrimeFinderThread pft3=new PrimeFinderThread(start+2*range+1, end);
		
		System.out.println("Iniciando búsqueda de números primos desde " + start + " hasta " + end);
		System.out.println("Distribuyendo trabajo entre 3 hilos...");
		
		pft1.start();
		pft2.start();
		pft3.start();
		
		try {
			Thread.sleep(5000);
			
			System.out.println("\n=== PAUSA DESPUÉS DE 5 SEGUNDOS ===");
			pft1.pauseThread();
			pft2.pauseThread();
			pft3.pauseThread();
			
			int totalPrimes = pft1.getPrimesCount() + pft2.getPrimesCount() + pft3.getPrimesCount();
			
			System.out.println("Números primos encontrados hasta el momento:");
			System.out.println("Hilo 1: " + pft1.getPrimesCount() + " primos");
			System.out.println("Hilo 2: " + pft2.getPrimesCount() + " primos");
			System.out.println("Hilo 3: " + pft3.getPrimesCount() + " primos");
			System.out.println("TOTAL: " + totalPrimes + " números primos encontrados");
			
			System.out.println("\nPresione ENTER para continuar...");
			Scanner scanner = new Scanner(System.in);
			scanner.nextLine();
			
			System.out.println("Reanudando búsqueda...");
			pft1.resumeThread();
			pft2.resumeThread();
			pft3.resumeThread();
			
			pft1.join();
			pft2.join();
			pft3.join();
			
			int finalTotalPrimes = pft1.getPrimesCount() + pft2.getPrimesCount() + pft3.getPrimesCount();
			System.out.println("\n=== RESULTADOS FINALES ===");
			System.out.println("Hilo 1: " + pft1.getPrimesCount() + " primos");
			System.out.println("Hilo 2: " + pft2.getPrimesCount() + " primos");
			System.out.println("Hilo 3: " + pft3.getPrimesCount() + " primos");
			System.out.println("TOTAL FINAL: " + finalTotalPrimes + " números primos encontrados");
			
			scanner.close();
			
		} catch (InterruptedException e) {
			System.err.println("Error durante la ejecución: " + e.getMessage());
		}
	}
	
}
