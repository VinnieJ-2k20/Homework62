package com.gmail.ptimofejev;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		int[] numbersArray = new int[10000000];
		fillArrayRandom11(numbersArray);
		int totalSumMulti = 0;
		int totalSumSingle = 0;
		Thread[] threads = new Thread[145];
		long multiStart = System.currentTimeMillis();
		int[] sums = multiThreadSum(numbersArray, threads);
		for (int i = 0; i < sums.length; i++) {
			totalSumMulti += sums[i];
		}
		long multiEnd = System.currentTimeMillis();
		long multiDuration = multiEnd - multiStart;
		System.out.println("Multi-Thread sum: " + totalSumMulti + ". Duration: " + multiDuration + " milliseconds.");

		long singleStart = System.currentTimeMillis();
		totalSumSingle = singleThreadSum(numbersArray);
		long singleEnd = System.currentTimeMillis();
		long singleDuration = singleEnd - singleStart;
		System.out.println("Single-Thread sum: " + totalSumSingle + ". Duration: " + singleDuration + " milliseconds.");

	}

	public static void fillArrayRandom11(int[] array) {
		for (int i = 0; i < array.length; i++) {
			array[i] = (int) (Math.random() * 11);
		}
	}

	public static int[] multiThreadSum(int[] numbers, Thread[] threads) {
		int[] sums = new int[threads.length];
		for (int i = 0; i < threads.length; i++) {
			int startIndex = i * numbers.length / threads.length;
			int endIndex = 0;
			if (i == threads.length - 1) {
				endIndex = numbers.length;
			} else {
				endIndex = (i + 1) * numbers.length / threads.length;
			}
			SumTask sum = new SumTask(numbers, startIndex, endIndex, 0);
			threads[i] = new Thread(sum);
			threads[i].start();
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sums[i] = sum.getSum();

		}
		return sums;
	}

	public static int singleThreadSum(int[] numbers) {
		int sum = 0;
		for (int i = 0; i < numbers.length; i++) {
			sum += numbers[i];
		}
		return sum;
	}

}
