public class Functions {
	// Activation Functions
	static double sigmoid(double z) {
		return 1.0 / (1.0 + (Math.exp(-1 * z)));
	}	
	static double sigmoidPrime(double z) {
		return sigmoid(z) * (1 - sigmoid(z));
	}
}
