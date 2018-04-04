package ie.gmit.sw.ai;

import java.util.Scanner;
import javax.swing.JFileChooser;
import java.io.File;

public class Menu {
	
	private Scanner in ;
	protected int choice;
	private int i; String input ;
	private String pattern = "[1-3]{1}";
	 /**
	  * menu system constructor
	  * with attributes needed
	  */
	 public Menu() throws Exception{
		input = "";
		in = new Scanner(System.in);
	 }
	 
	
	 void Menu1() throws Exception{
	
		String input;
		System.out.println("================================");
		System.out.println("||         MENU SELECTION     ||");
		System.out.println("================================");
		System.out.println("||1.PlayFair Cipher 	      ||");
		System.out.println("||2.Simulated annealing	      ||");
		System.out.println("||3.EXIT SYSTEM	              ||");
		System.out.println("================================");
		
		input = (in.nextLine());
		if (!(input.matches(pattern))) {
				System.out.println("Invalid entry please uses options numbers available");
				Menu1();
		}		
		else{
			choice = Integer.parseInt(input);
	      switch(choice) {
	         case 1 :
	            System.out.println("string playFairCipher");
	            PlayFair pf = new PlayFair();
	            pf.PlayFairCipher();
	            break;
	         case 2 :
	        	 System.out.println("sim annealing");
	        	 Menu2();
		         break;
	         case 3 :
	        	 System.out.println("GoodBye");
	        	 System.exit(0);
		         break;
	      }
		}
	}
	void Menu2() throws Exception {
		
		System.out.println("================================");
		System.out.println("||         MENU SELECTION     ||");
		System.out.println("================================");
		System.out.println("||1.Prev Menu	              ||");
		System.out.println("||2.Load Encryted URL	      ||");
		System.out.println("||3.Paste Encryted File	      ||");
		System.out.println("================================");
		
		input = (in.nextLine());
		if (!(input.matches(pattern))) {
				System.out.println("Invalid entry please use options available");
				Menu2();
		}
		else{
			choice = Integer.parseInt(input);
		      switch(choice) {
		         case 1 :
		            System.out.println("prev");
		            Menu1();
		            break;
		         case 2 :
		            System.out.println("Input URL for Decryption and Annealing" +
		            		"\nExample https://learnonline.gmit.ie/pluginfile.php/329082/mod_resource/content/1/cyphertext-2018.txt");
		            String Url = in.nextLine();
		            DocReader dr = new DocReader();
		            dr.URLReader(Url);
		            break;
		         case 3 :
		            System.out.println("option 3");
		            // input encryted  ;
		            break;
		      }
		}	
	}
}
