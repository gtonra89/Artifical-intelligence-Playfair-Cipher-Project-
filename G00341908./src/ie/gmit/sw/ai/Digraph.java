package ie.gmit.sw.ai;

/***
 * Digraph Class used for the playfair cipher with a keyword given 
 * @author garret
 *
 */
public class Digraph {
	
		public char char1;
		public char char2;
		public int row;
		public int col;
		
		public Digraph(char c1, char c2) {
			this.char1 = c1;
			this.char2 = c2;
		}
		
		public Digraph() {
			// no args default constructor
		}		
}

