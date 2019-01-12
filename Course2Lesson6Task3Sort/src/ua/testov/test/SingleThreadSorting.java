package ua.testov.test;

public class SingleThreadSorting implements Runnable {
	private int[] array;
	private int begin;
	private int end;
	private Thread thr;
	private int index;
	private boolean stop = false;

	public SingleThreadSorting(int[] array, int begin, int end) {
		super();
		this.array = array;
		this.begin = begin;
		this.end = end;
		thr = new Thread(this);
		thr.start();
		this.index = begin;
	}

	public Thread getThr() {
		return thr;
	}

	public int peekElement() {
		return array[index];
	}

	public int pollElement() {
		int temp = array[index];
		check();
		return temp;
	}

	public boolean isStop() {
		return stop;
	}

	@Override
	public void run() {
		int d = end / 2;
		while (d > 0) {
			for (int i = begin; i < (array.length - d); i++) {
				int j = i;
				while (j >= 0 && (array[j] > array[j + d])) {
					int temp = array[j];
					array[j] = array[j + d];
					array[j + d] = temp;
					j--;
				}
			}
			d = d / 2;
		}
	}

	
	private void check() {
		this.index++;
		if (this.index >= this.end) {
			this.stop = true;
		}
	}
}
