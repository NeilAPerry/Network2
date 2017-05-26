import java.util.ArrayList;

public class Neuron {
	private BoundedNumber bias;
	private ArrayList<Double> weights;
	private double output = 0.0;

	Neuron(int numInputs) {
		bias = new BoundedNumber(Math.random(), 0, 1);

		weights = new ArrayList<Double>(numInputs);
		for (int i = 0; i < numInputs; i++) {
			weights.add(Math.random());
			if (weights.get(i) != 0) {
				weights.set(i, weights.get(i) / Math.sqrt(weights.get(i)));
			}
		}
	}
	
	public double activate(ArrayList<Double> activations) {
		if (activations.size() != weights.size())
			throw new ArrayIndexOutOfBoundsException("activations and weights inconsistent size.");
		
		// sum $ zipWith (*) activations weights
		double sum = 0;
		for (int i = 0; i < activations.size(); i++) {
			sum += activations.get(i) * weights.get(i);
		}
		
		output = Functions.sigmoid(sum - bias.getNumber());
		return output;
	}
	
	public void backprop(double learningRate, double error, ArrayList<Double> activations) {
		if (activations.size() != weights.size())
			throw new ArrayIndexOutOfBoundsException("activations and weights inconsistent size.");

		for (int i = 0; i < activations.size(); i++) {
			double weight = weights.get(i);
			// This is probably the biggest mistake in the network
			weights.set(i, 
					weight - (learningRate * error * Functions.sigmoidPrime(activations.get(i)))
				);
		}
	}
	
	public double getBias() { return bias.getNumber(); }
	public void setBias(double bias) { this.bias.updateNumber(bias); }

	public ArrayList<Double> getWeight() { return weights; }
	
	public double getOutput() { return output; }
}
