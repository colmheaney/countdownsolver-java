

import java.util.HashSet;
// Takes a string and returns a set of permutations of that string
// E.g. abc, acb, bac, bca etc..
// Based on http://introcs.cs.princeton.edu/java/23recursion/Permutations.java.html
public class Permutations {
	// use a set to hold permutations to avoid duplicates
	private static HashSet<String> set;
	private static String s;
	
	public Permutations(String s) {
		set = new HashSet<String>();
		Permutations.s = s;
	}
	
	public HashSet<String> generate() { 
		int n = s.length();
		char[] a = new char[n];
		for(int i = 0; i < n; i++) {
			a[i] = s.charAt(i);
		}
		perm(a, n);
		return set;
	}
	
	public static void perm(char[] a, int n) {
		if(n == 1) {
			set.add(new String(a));
			return;
		}
		for(int i = 0; i < n; i++) {
			swap(a, i, n-1);
			perm(a, n-1);
			swap(a, i, n-1);
		}
	}
	
	private static void swap(char[] a, int i, int j) {
        char c = a[i];
        a[i] = a[j];
        a[j] = c;
	}
	
	public static void main(String[] args) {
		Permutations p = new Permutations("abcd");
		for(String perm: p.generate()) {
			System.out.println(perm);
		}
	}
}
