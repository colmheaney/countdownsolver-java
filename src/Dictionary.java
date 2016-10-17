

import java.util.HashSet;

// Implements a Trie Symbol table with get/put/contains operations.
// Based on http://algs4.cs.princeton.edu/52trie/TrieST.java.html
public class Dictionary<Value> {
	private static int R; //Radix
	private static String alphabet;
	private Node root;
	
	private static class Node {
		private Object val;
		private Node[] next = new Node[R];
	}
	
	public Dictionary(String alphabet) {
		Dictionary.alphabet = alphabet;
		R = alphabet.length();
	}

	public Value get(String key) {
		Node x = get(root, key, 0);
		if(x == null) return null;
		return (Value) x.val;
	}
	
	private Node get(Node x, String key, int d) {
		if(x == null) return null;
		if(d == key.length()) return x;
		char c = key.charAt(d);
		return get(x.next[alphabet.indexOf(c)], key, d+1);
	}
	
	public Iterable<String> subwordsIn(String string) {
		HashSet<String> set = new HashSet<String>();
		subwordsIn(root, string, 0, set);
		return set;
	}
	
	private void subwordsIn(Node x, String string, int d, HashSet<String> set) {
		if(x == null) return;
		if(d == string.length()) { // at end of linked list so add if whole word and return
			if(x.val != null) set.add(string.substring(0, d)); 				
			return; 
		}
		if(x.val != null) set.add(string.substring(0, d)); // subword has been found do add it to set
		char c = string.charAt(d);
		subwordsIn(x.next[alphabet.indexOf(c)], string, d+1, set);
	}
	
	public boolean contains(String key) {
		return get(key) != null;
	}
	
    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }
	
    private Node put(Node x, String key, Value val, int d) {
        if (x == null) x = new Node();
        // is the key was already in the dictionary update the x.val to new val and return or put for first time
        if (d == key.length()) {
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        int i = alphabet.indexOf(c);
        x.next[i] = put(x.next[i], key, val, d+1);
        return x;
    }
    
	public static void main(String[] args) {
		Dictionary<Integer> d = new Dictionary<Integer>("abcdefghijklmnopqrstuvwxyz");
		d.put("ignobility", 0);
		d.put("ignoble", 1);
		d.put("ignobleness", 2);
		d.put("ignobly", 3);
		
		for(Object s : d.subwordsIn("ignob")) {
			System.out.println(s.toString());
		}
	}
}
