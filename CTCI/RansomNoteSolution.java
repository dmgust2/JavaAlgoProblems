package CTCI;
/**
 * HackerRank CTCI:
 * https://www.hackerrank.com/challenges/ctci-ransom-note/problem
 * @author Gusto
 */


import java.util.*;


public class RansomNoteSolution {

	// Word -> Count Mapping for each
	Map<String, Integer> magazineMap;
    Map<String, Integer> noteMap;

    /**
     * CTOR
     * @param magWordSize
     * @param magazine
     * @param noteWordSize
     * @param note
     */
	public RansomNoteSolution(int magWordSize, String magazine, int noteWordSize, String note) {
		// Sounds like HashMap is preferable to HashTable...

		// Create the Magazine HashMap from the passed magazine String
		magazineMap = new HashMap<String, Integer>(magWordSize);
		// Iterate through the magazine String, splitting on white space
		for (String word : magazine.split("\\s+")) {
			// If the word already exists in the HashMap, increment the word count, otherwise simply add the new word
			if (magazineMap.containsKey(word)) {
				magazineMap.put(word, magazineMap.get(word) + 1);
			}
			else {
				magazineMap.put(word, 1);
			}

			/** So it seems I don't need to do this explicitly, HashMap put figures out the hashing for us...
			int hashcode = getHashCode(word);
			int index = covertToIndex(hashcode);
			magazineMap.put(word, index);
			*/
		}

		// Create the Note HashMap from the passed note string
		noteMap = new HashMap<String, Integer>(noteWordSize);
		for (String word : note.split("\\s+")) {
			if (noteMap.containsKey(word)) {
				noteMap.put(word, noteMap.get(word) + 1);
			}
			else {
				noteMap.put(word, 1);
			}
		}
    }


	public void printMaps() {
		System.out.println("\nPrinting the HashMaps...");

		// Get all the keys (i.e. words) in the magazineMap and print out the word counts of each
		System.out.println("Magazine:");
		for (String word : magazineMap.keySet()) {
            System.out.println(word + ": " + magazineMap.get(word));
        }

		// Get all the keys (i.e. words) in the noteMap and print out the word counts of each
		System.out.println();
		System.out.println("Ransom Note:");
		for (String word : noteMap.keySet()) {
            System.out.println(word + ": " + noteMap.get(word));
        }

		System.out.println();
	}


	/**
	 * 
	 * @return
	 */
    public boolean solve() {
        // If the word count of the ransom note is > than the word count of the magazine then automatic NO
    	// TODO: Better to compare words/key counts or simply ints m & n?
    	if (noteMap.size() > magazineMap.size()) {
    		return false;
    	}

    	// Now see if each word in the ransom note can be created from the magazine
    	for (Map.Entry<String, Integer> noteWord : noteMap.entrySet()) {
    		if (magazineMap.containsKey(noteWord.getKey())) {
    			if (noteWord.getValue() > magazineMap.get(noteWord.getKey())) {
    				return false;
    			}
    		}
    		else {
    			return false;
    		}
    	}

		return true;
    }


	/**
	 * MAIN
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();

        // Eat whitespace to beginning of next line
        scanner.nextLine();

        // Populate the HashMaps from the user input
        RansomNoteSolution s = new RansomNoteSolution(m, scanner.nextLine(), n, scanner.nextLine());
        scanner.close();

        // Print the HashMaps
        s.printMaps();

        boolean answer = s.solve();
        if (answer) {
        	System.out.println("Yes");
        }
        else {
        	System.out.println("No");
        }
	}

}
