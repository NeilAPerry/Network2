import java.util.*;

public class Functions {
	
	// Cost Functions
	static Double crossEntropyCost(ArrayList<Double> a, Double y) {
		
		double runningSum = 0.0;
		for (int i = 0; i < a.size(); i++) {
			if (!(a.get(i) == 1.0 && y == 1.0)) {
				runningSum += ((-1 * y) * Math.log(a.get(i))) - ((1 - y) * Math.log(1 - a.get(i)));

			}
		}
		
		return runningSum;
	}
	
	static double[] delta (double[] a, double y) {
		double[] temp = new double[(a.length)];
		for (int i = 0; i < a.length; i ++) {
			temp[i] = a[i] - y;
		}
		return temp;
	}
	
	// Activation Functions
	static double sigmoid(double z) {
		return 1.0 / (1.0 + (Math.exp(-1 * z)));
	}
	
	static double sigmoidPrime(double z) {
		return sigmoid(z) * (1 - sigmoid(z));
	}

}
