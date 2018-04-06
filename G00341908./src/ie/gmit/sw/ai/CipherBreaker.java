package ie.gmit.sw.ai;

import java.util.Scanner;
/***
 * 
 * @author garret
 *main class for running the application
 */
public class CipherBreaker {

	/***
	 * main Method to run the application it calls the cipher breaker Method 
	 * @param args
	 * @throws Exception
	 * @throws Throwable
	 */
	public static void main(String[] args) throws Exception, Throwable {
		
		cipherBreaker();
		
	}
	
	
	/***
	 * cipherbreaker method that takes the input and decides what to do with input
	 * based on the menu selections
	 *  
	 * @throws Exception
	 * @throws Throwable
	 */
	
	public static void cipherBreaker() throws Exception, Throwable{	
		Scanner input = new Scanner(System.in);
		String Choice;
		int choice1;
		String pattern = "[1-3]{1}";
		String pattern1 = "[x,y]{1}";
		int iterations = 60000;
		String filename = "";

		Menu menu = new Menu();
		menu.menu();

		Choice = input.nextLine();
		if (!(Choice.matches(pattern))) {
			System.out.println("invalid input !\n" + "Only numeric characters allowed\n" + "Please Try Again");
			cipherBreaker();// recall the main
		} else {
			choice1 = Integer.parseInt(Choice);
			switch (choice1) {
			case 1:
				PlayFairWithKey pfwk = new PlayFairWithKey();
				pfwk.PlayFairCipher();
				break;

			case 2:
				System.out.print("please paste encrypted text to decrypt: ");
				String cText = input.nextLine();
				
				cText = cText.toUpperCase(); // make sure keyword is all upper case
				cText = cText.replaceAll("J", "I"); // replace all js with is
				cText = cText.replaceAll("\\s+", ""); // remove all whitespace
				cText = cText.replaceAll("[^a-zA-Z]", ""); // remove all nonalphabet
				if (cText.length()%2 != 0){
					
					//add a extra x to it to make even
					System.out.println("not even length of text so adding extra X to end of text");
					cText = cText.concat("X");
				}
				System.out.println("Simulated Annealing Running...");
				int Temp = (int) ((12 + 0.087 * (cText.length() - 84)));
				int BestTemp = Temp / 3;
				SimulatedAnnealing sa = new SimulatedAnnealing(BestTemp, iterations, cText);
				sa.SimAnnealing();
				
				System.out.println("\nTo Retry enter 'y'\n"
						+ "To return to previous menu  enter 'n'");
				String choice = input.nextLine();
				if (!(choice.matches(pattern1))){
					System.out.println("invalid input please enter either 'y' to retry\n"
							+ "Or 'n' to return to previous menu in future Attempts\n......Restarting Application");
							cipherBreaker();
				}
				if (choice.equalsIgnoreCase("y")) {
					System.out.println("\nRetrying Annealing for better results...");
					sa.SimAnnealing();
					cipherBreaker();
					break;
				} 
				else if (choice.equalsIgnoreCase("n")) {
					System.out.println("returning to menu.......");
					cipherBreaker();
					break;
				} 
				else
					break;
			case 3:
				System.out.println("Exiting System......GoodBye");
				System.exit(0);
				break;
			}
		}
	}	
}