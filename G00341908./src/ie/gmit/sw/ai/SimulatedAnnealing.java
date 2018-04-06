package ie.gmit.sw.ai;

import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.Map;

/**
 * simulated annealing class 
 * @author garret
 *
 */
public class SimulatedAnnealing {
	//local private variables
	
	private KeyGen key;
	private PlayFairAnnealing pf;
	private FourGrams gram;
	private SecureRandom SecRand;
	private int temperature;
	private int iterations;
	private Map<String, Integer> gramsMap;

	// Constructor
	public SimulatedAnnealing(int temperature, int iterations, String cipherText) {
		super();
		this.gram = new FourGrams("4grams.txt");
		this.pf = new PlayFairAnnealing();
		this.pf.setCipherText(cipherText);
		this.key = KeyGen.keyInstance();
		this.temperature = temperature;
		this.iterations = iterations;
	}

	/***
	 * actual annealing method here i generate my parent key load my grams
	 * textfile set my parent score and my best score
	 * 
	 * @throws Throwable
	 */
	public void SimAnnealing() throws Throwable {

		gramsMap = gram.loadFile();
		String parentKey = key.generateKey();
		String decryptedText = pf.decrypt(parentKey);
		double parentScore = gram.scoreText(decryptedText);
		double bestScore = parentScore;
		double Prob;
		SecRand = new SecureRandom();
		double startScore = bestScore;
		for (int temp = temperature; temp > 0; temp--) {
			for (int index = iterations; index > 0; index--) {

				String childKey = key.shuffleKey(parentKey);

				decryptedText = pf.decrypt(childKey);
				double childScore = gram.scoreText(decryptedText);
				double delta = childScore - parentScore;

				if (delta > 0) {

					parentKey = childKey;

					parentScore = childScore;
				} else {

					Prob = (Math.exp((delta / temp)));
					if (Prob > SecRand.nextDouble()) {
						parentKey = childKey;
						parentScore = childScore;
					}
				}
				if (parentScore > bestScore) {
					bestScore = parentScore;
					System.out.println("Temperature is Currently : " + temp);
					System.out.println("Iteration Number: is Currently : " + index);
					System.out.println("Best score is Currently : " + bestScore);
					System.out.println("");
				}
			}
			if (bestScore > (startScore / 1.5)) {
				System.out.println("Temperature is : " + temp);
				System.out.println("Key is : " + parentKey);
				System.out.println("best score so far is  :" + bestScore);

				if (bestScore > (startScore / 1.6))
					break;
			}
		}
		System.out.println("\n\nKey found: " + parentKey + "\nDecrypted message: " + pf.decrypt(parentKey));

		// Print the result to textfile called result.txt
		try (PrintWriter out = new PrintWriter("DecrytedFile.txt")) {
			out.println("Key found: " + parentKey + "\r\nDecrypted message: " + pf.decrypt(parentKey));
		}
	}
}
