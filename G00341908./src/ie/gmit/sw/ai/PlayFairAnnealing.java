package ie.gmit.sw.ai;

import java.util.LinkedList;
import java.util.List;
/****
 * PlayFair Annealing class
 * has your usual getters and setters 
 * for the constructor paramaters
 * and local variables set to private 
 * @author garret
 * 
 */
public class PlayFairAnnealing {

	private List<Position> positions;
	private StringBuilder txt;
	private char[][] cipher;
	private String cipTxt;

	//Constructor
	public PlayFairAnnealing() {
		super();
		this.positions = new LinkedList<Position>();
		this.txt = new StringBuilder();
		this.cipher = new char[5][5];
		this.cipTxt = "";
	}
	
	/***
	 * 
	 * Method to Decrypt encrypted text using the key
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String decrypt(String key) throws Exception {
		
		String decryptKey = key;
		char[][] cipher = new char[5][5];

		int index = 0;

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				cipher[i][j] = decryptKey.charAt(index);
				index++;
			}
		}
		this.cipher = cipher;
		
		StringBuilder sb = new StringBuilder();
		
		for(index = 0; index < this.cipTxt.length() / 2; index ++) {
			char a = this.cipTxt.charAt(2 * index);
			char b = this.cipTxt.charAt(2 * index + 1);
			int r1 = (int) Position.getPos(a, cipher).getX();
			int c1 = (int) Position.getPos(a, cipher).getY();
			int r2 = (int) Position.getPos(b, cipher).getX();
			int c2 = (int) Position.getPos(b, cipher).getY();

			if (r1 == r2) {
				c1 = (c1 + 4) % 5; 
				c2 = (c2 + 4) % 5;
			} else if (c1 == c2) {
				r1 = (r1 + 4) % 5;
				r2 = (r2 + 4) % 5;
			} else {
		        int temp = c1;
		        c1 = c2;
		        c2 = temp;
		    }
			sb.append(cipher[r1][c1] +""+ cipher[r2][c2]);
		}
		
		return sb.toString();
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public String getPlainText() {
		return txt.toString();
	}

	public void setPlainText(String plainText) {
		this.txt.append(plainText);
	}

	public char[][] getCipherTable() {
		return cipher;
	}

	public void setCipherTable(char[][] cipherTable) {
		this.cipher = cipherTable;
	}

	public String getCipherText() {
		return cipTxt;
	}

	public void setCipherText(String cipTxt) {
		this.cipTxt = cipTxt;
	}
}