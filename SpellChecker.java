
public class SpellChecker {

	public static void main(String[] args) {
		// String word = args[0];
		// int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		// String correction = spellChecker(word, threshold, dictionary);
		// System.out.println(correction);
		System.out.println(spellChecker("hell0", 1, dictionary));
	}

	public static String tail(String str) {
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		if (word2.length() == 0) {
			return word1.length();
		}
		if (word1.length() == 0) {
			return word2.length();
		}
		if (word1.charAt(0) == word2.charAt(0)) {
			return levenshtein(tail(word1), tail(word2));
		}
		int optA = levenshtein(tail(word1), word2);
		int optB = levenshtein(word1, tail(word2));
		int optC = levenshtein(tail(word1), tail(word2));

		int min1 = Math.min(optA, optB);
		int minAll = Math.min(min1, optC);
		return 1 + minAll;
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		int i = 0;
		while (!in.isEmpty()) {
			dictionary[i] = in.readLine();
			i++;

		}

		return dictionary;

	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int max = threshold+1;
		String newWord = word;
		for (int i = 0; i < dictionary.length; i++) {
			int lev = levenshtein(word, dictionary[i]);
			if (lev < max) {
				max = lev;
				newWord = dictionary[i];
			}
		}
		return newWord;
	}

}
