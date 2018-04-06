package ie.gmit.sw.ai;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
/***
 * PlayFairWithkeyClasss 
 * @author garret
 *
 */
public class PlayFairWithKey extends CipherBreaker {

	static String ciphertext = "";
	static String keyword = "";
	static List<Digraph> Digraph1 = new LinkedList<Digraph>();
	static List<Digraph> Digraph2 = new LinkedList<Digraph>();
	static char[][] table = new char[5][5];
	static Hashtable<Character, Integer> RowValue = new Hashtable<Character, Integer>();
	static Hashtable<Character, Integer> ColumnValue = new Hashtable<Character, Integer>();

	public PlayFairWithKey() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void PlayFairCipher() throws Exception, Throwable {
		
		Digraph1.removeAll(Digraph1);
		Digraph2.removeAll(Digraph2);
		RowValue.clear();
		ColumnValue.clear();
		
		Scanner input = new Scanner(System.in);
		System.out.println("Would you like to (encrypt) or (decrypt) a message?\n"
							+ "Type ('e' or 'encrypt') for encrypting\n"
							+ "Type ('d' or 'decrypt') for decrypting\n"
							+ "Input: ");

		String Decision = input.nextLine();
			if (Decision.equalsIgnoreCase("e") || Decision.equalsIgnoreCase("encrypt")) {
				System.out.println("Please type a message for encryption\nInput:");
				ciphertext = input.nextLine();
				
				System.out.println("Please input keyword\nInput: ");
				keyword = input.next();

				buildKeyTable();
				buildDigraphList();
				encryptDigraphList();
				PrintWriter writer = new PrintWriter("PlayFairEncryptedResults.txt", "UTF-8");
				// print decrpyted digraph
				System.out.println("Encrypted message:");
				for (Digraph d : Digraph2) {
					System.out.print(d.char1);
					System.out.print(d.char2);
					writer.append(d.char1);
					writer.append(d.char2);
				}
				writer.close();
			}

			else if (Decision.equals("d") || Decision.equalsIgnoreCase("decrypt")) {
				System.out.println("Please type a message for decryption\nInput: ");
				ciphertext = input.nextLine();
				
				System.out.println("Please input keyword\nInput: ");
				keyword = input.nextLine();

				buildKeyTable();
				buildDigraphList();
				decryptDigraphList();
				PrintWriter writer = new PrintWriter("PlayFairDecryptedResults.txt", "UTF-8");
				System.out.println("Decrypted message (ignore extra Xs and Qs):");
				for (Digraph d : Digraph2) {
					System.out.print(d.char1);
					System.out.print(d.char2);
					writer.append(d.char1);
					writer.append(d.char2);
				}
				writer.close();
			}
			else {
				System.out.println("Invalid input restarting !");
				PlayFairCipher();
			}
			
			System.out.println("\nEncrypt/Decrypt another message? (yes/no)");
			Decision = input.next();
			
			if (Decision.equalsIgnoreCase("yes")) {
				
				PlayFairCipher();
			}
			else if (Decision.equalsIgnoreCase("no")){
				
				cipherBreaker(); //recall main method
			}
			else {
				System.out.println("Invalid input restarting !!");
				PlayFairCipher();
			}
	}
	/***
	 * method to buildtheKeyTable
	 */
	private static void buildKeyTable() {
		keyword = keyword.toUpperCase(); // make sure keyword is all upper case
		keyword = keyword.replaceAll("J", "I"); // replace all js with is
		keyword = keyword.replaceAll("\\s+", ""); // remove all whitespace
		keyword = keyword.replaceAll("[^a-zA-Z]", ""); // remove all nonalphabet
														// chars
		keyword = keyword.concat("ABCDEFGHIKLMNOPQRSTUVWXYZ");
		keyword = removeDups(keyword); // remove all duplicate characters
		
		int k = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				table[i][j] = keyword.charAt(k);
				RowValue.put(keyword.charAt(k), i); // while we're here let's
													// store the row
				ColumnValue.put(keyword.charAt(k), j); // and col values of the chars
													// in a fast hashtable
				k++;
			}
		}

		System.out.println("Key Table:");
		for (int i = 0; i < 5; i++) { // print out the key table
			System.out.println();
			for (int j = 0; j < 5; j++) {
				System.out.print(table[i][j]);
			}
		}
	}
	/***
	 * method to buildDigraphList
	 */
	private static void buildDigraphList() {
		ciphertext = ciphertext.toUpperCase(); // change to all upper case
		ciphertext = ciphertext.replaceAll("\\s+", ""); // remove all whitespace
		ciphertext = ciphertext.replaceAll("J", "I"); // replace all j with i
		ciphertext = ciphertext.replaceAll("[^a-zA-Z]", "");// remove all non-alphabet characters
		
		char last = 7; char current; 
		StringBuilder sb = new StringBuilder();

		// split all repeated letters up with X
		int k = 0;
		for (int i = 0; i < ciphertext.length(); i++) {
			current = ciphertext.charAt(i);
			if (current == last && k % 2 == 1) { // if we have repeating characters
				sb.append('X'); // put a X in between them only if we need to
				k++;
				sb.append(current);
				k++;
			} else {
				sb.append(current);
				k++;
			}
			last = ciphertext.charAt(i); // store the last character
			
		}

		String newtext = sb.toString();
		if (newtext.length() % 2 == 1) { // if there is an odd number of chars,
											// pad with an Q
			newtext = newtext + "Q";
		}

		System.out.println("\nFormatted cipher: " + newtext);
		for (int i = 0; i < newtext.length(); i = i + 2) {
			Digraph toadd = new Digraph(newtext.charAt(i), newtext.charAt(i + 1));
			Digraph1.add(toadd);
		}
	}
	/***
	 * Method to EncryptDigraphList
	 */
	private static void encryptDigraphList() {
		char one;
		int rowone = -1;
		int colone = -1;
		char two;
		int rowtwo = -2;
		int coltwo = -2;

		for (Digraph d : Digraph1) {
			one = d.char1;
			two = d.char2;
			System.out.println("Checking digraph: " + one + two);
			rowone = RowValue.get(one);
			colone = ColumnValue.get(one);
			rowtwo = RowValue.get(two);
			coltwo = ColumnValue.get(two);
			// System.out.println(one+" row "+rowone+" col " +colone+" "+two+"
			// row "+rowtwo+" col "+coltwo);

			Digraph newDigraph = new Digraph();
			if (rowone == rowtwo) {
				// System.out.println("rows match");
				newDigraph.char1 = table[rowone][(colone + 1) % 5];
				newDigraph.char2 = table[rowtwo][(coltwo + 1) % 5];
				System.out.println("Row replacing with: " + newDigraph.char1 + newDigraph.char2);
				// System.out.println(newDigraph.char1+" row "+rowone+" col
				// "+((colone+1)%5));
			}

			else if (colone == coltwo) {
				// System.out.println("cols match");
				if (rowone == 4 && rowtwo != 4) {
					newDigraph.char1 = table[0][colone];
					newDigraph.char2 = table[(rowtwo + 1) % 5][coltwo];
				} else if (rowone != 4 && rowtwo == 4) {
					newDigraph.char1 = table[(rowone + 1) % 5][colone];
					newDigraph.char2 = table[0][coltwo];
				} else if (rowone == 4 && rowtwo == 4) {
					newDigraph.char1 = table[0][colone];
					newDigraph.char2 = table[0][coltwo];
				} else {
					newDigraph.char1 = table[(rowone + 1) % 5][colone];
					newDigraph.char2 = table[(rowtwo + 1) % 5][coltwo];
				}
				System.out.println("Column replacing with: " + newDigraph.char1 + newDigraph.char2);
			}

			else {
				// System.out.println("rectangle");
				newDigraph.char1 = table[rowone][coltwo];
				newDigraph.char2 = table[rowtwo][colone];
				System.out.println("Rectangle replacing with: " + newDigraph.char1 + newDigraph.char2);
			}

			Digraph2.add(newDigraph);
		}

	}
	/***
	 * Method to DecryptDigraphList
	 */
	private static void decryptDigraphList() {
		char one;
		int rowone = -1;
		int colone = -1;
		char two;
		int rowtwo = -2;
		int coltwo = -2;

		for (Digraph d : Digraph1) {
			one = d.char1;
			two = d.char2;
			System.out.println("Checking digraph: " + one + two);
			rowone = RowValue.get(one);
			colone = ColumnValue.get(one);
			rowtwo = RowValue.get(two);
			coltwo = ColumnValue.get(two);
			// System.out.println(one+" row "+rowone+" col " +colone+" "+two+"
			// row "+rowtwo+" col "+coltwo);

			Digraph newDigraph = new Digraph();
			if (rowone == rowtwo) {
				// System.out.println("rows match");
				if (colone == 0 && coltwo == 0) {
					newDigraph.char1 = table[rowone][4];
					newDigraph.char2 = table[rowtwo][4];
				} else if (colone == 0 && coltwo != 0) {
					newDigraph.char1 = table[rowone][4];
					newDigraph.char2 = table[rowtwo][(coltwo - 1) % 5];
				} else if (colone != 0 && coltwo == 0) {
					newDigraph.char1 = table[rowone][(colone - 1) % 5];
					newDigraph.char2 = table[rowtwo][4];
				} else {
					newDigraph.char1 = table[rowone][(colone - 1) % 5];
					newDigraph.char2 = table[rowtwo][(coltwo - 1) % 5];
				}
				System.out.println("Row replacing with: " + newDigraph.char1 + newDigraph.char2);
				// System.out.println(newDigraph.char1+" row "+rowone+" col
				// "+((colone+1)%5));
			}

			else if (colone == coltwo) {
				// System.out.println("cols match");
				if (rowone == 0 && rowtwo != 0) {
					newDigraph.char1 = table[4][colone];
					newDigraph.char2 = table[(rowtwo - 1) % 5][coltwo];
				} else if (rowone != 0 && rowtwo == 0) {
					newDigraph.char1 = table[(rowone - 1) % 5][colone];
					newDigraph.char2 = table[4][coltwo];
				} else if (rowone == 0 && rowtwo == 0) {
					newDigraph.char1 = table[4][colone];
					newDigraph.char2 = table[4][coltwo];
				} else {
					newDigraph.char1 = table[(rowone - 1) % 5][colone];
					newDigraph.char2 = table[(rowtwo - 1) % 5][coltwo];
				}
				System.out.println("Column replacing with: " + newDigraph.char1 + newDigraph.char2);
			}

			else {
				// System.out.println("rectangle");
				newDigraph.char1 = table[rowone][coltwo];
				newDigraph.char2 = table[rowtwo][colone];
				System.out.println("Rectangle replacing with: " + newDigraph.char1 + newDigraph.char2);
			}

			Digraph2.add(newDigraph);
		}

	}
	
	
	/***
	 * Method to RemoveDuplicate characters
	 */
	private static String removeDups(String str) {

		Set<Character> set = new LinkedHashSet<Character>();

		for (int i = 0; i < str.length(); i++) {
			set.add(str.charAt(i));
		}

		StringBuilder sb = new StringBuilder();
		for (Character character : set) {
			sb.append(character);
		}

		return sb.toString();
	}

}
