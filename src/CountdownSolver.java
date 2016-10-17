import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

// Reads in and input a dictionary file and string (up to 9 letters) and 
// searches the dictionary for any existing permutation of that string.
public class CountdownSolver {
	
	private static String alphabet = "abcdefghijklmnopqrstuvwxyz'";
	
	private static boolean isValid(String input) {
		for(int i = 0; i < input.length(); i++) {
			if(!alphabet.contains(input.substring(i, i+1)))
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		// Make sure user has entered valid command line arguments
		if(args.length < 1) {
			System.out.println("Error, usage: java -jar countdown.jar dictionaryfile");
			System.exit(1);
		} 
		
		// Get ready to read dictionary file input
		Scanner reader = null;		
		try {
			reader = new Scanner(new FileInputStream(args[0]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Create dictionary instance and populate with words
		Dictionary<Integer> dictionary = new Dictionary<Integer>(alphabet);
		int count = 0;
		while(reader.hasNext()) {
			String key = reader.nextLine();
			dictionary.put(key, count);
			count++;
		}
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			
			while(true) {
				String input;
				do {
					System.out.print("Enter a word (9 letters or less): ");
					input = br.readLine().toLowerCase();
				} while(!isValid(input));
				
				if("q".equals(input)) {
					System.out.println("Exit!");
					System.exit(0);
				}
				
				// Get permutations and check if dictionary contains that word
				HashSet<String> set = new HashSet<String>(); 
//				Permutations p = new Permutations(input);
				HeapsAlgorithm p = new HeapsAlgorithm(input);
				for(String permutation : p.generate()) {
					for(String subword : dictionary.subwordsIn(permutation)) {
						set.add(subword); // lot of duplication here. Need to optimize
					}
				}
				
				System.out.println("input: " + input);
				System.out.println("Valid words: ");
				System.out.println("-----------");
				Object[] words = set.toArray();
				// Sort words by length
				Arrays.sort(words, new StringLengthComparator());
				for(Object word : words)
					System.out.println(word);
				System.out.println("-----------");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
