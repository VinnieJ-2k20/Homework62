package com.gmail.ptimofejev;

public class SumTask implements Runnable {
	private int[] numbers;
	private int startIndex;
	private int endIndex;
	private int sum;

	public SumTask(int[] numbers, int startIndex, int endIndex, int sum) {
		this.numbers = numbers;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.sum = sum;
	}

	public int[] getNumbers() {
		return numbers;
	}

	public void setNumbers(int[] numbers) {
		this.numbers = numbers;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	@Override
	public void run() {
		for (int i = startIndex; i < endIndex; i++) {
			sum += numbers[i];
		}
		System.out.println(Thread.currentThread().getName() + ": " + sum);
	}
	
}
