

import java.util.LinkedList;
import java.util.Queue;

public class HeapsAlgorithm {
	private char[] A;
	private int N;
	private int[] c;
	
	public HeapsAlgorithm(String s) {
		this.N = s.length();
		this.A = s.toCharArray();
		this.c = new int[N];
	}
	
	public Queue<String> generate() {
		Queue<String> q = new LinkedList<String>();
		q.add(new String(this.A));
		
		int i = 1;
		while(i < N) {
			if(c[i] < i) {
				if(i % 2 == 0)
					swap(0, i);
				else
					swap(c[i], i);
				q.add(new String(this.A));
				c[i] += 1;
				i = 1;
			} else {
				c[i] = 0;
				i++;
			}
		}
		return q;
	}

	private void swap(int i, int j) {
		char tmp = this.A[i];
		this.A[i] = this.A[j];
		this.A[j] = tmp;
	}
	
	public static void main(String[] args) {
		HeapsAlgorithm h = new HeapsAlgorithm("test");
		for(String perm: h.generate()) {
			System.out.println(perm);
		}
	}
}
