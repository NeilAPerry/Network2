import java.util.ArrayList;
import java.util.List;

public class Neuron {
	
	private double bias;
	private ArrayList<Double> weights;
	private double activation;
	private double error = 0.0;
	private double delta = 0.0;
	private double output = 0.0;
	




	Neuron(int numInputs) {
		weights = new ArrayList<Double>(numInputs);
		for (int i = 0; i < numInputs; i++) {
			weights.add(0.0);
		}
		bias = Math.random();
		for (int i = 0; i < weights.size(); i++) {
			weights.set(i,Math.random());
			if (weights.get(i) != 0) {
				weights.set(i, weights.get(i) / Math.sqrt(weights.get(i)));
			}
		}
	}
	
	public double activate(List<Double> activations) {
		if (activations.size() != weights.size())
			throw new ArrayIndexOutOfBoundsException("activation and weights inconsistent size.");
		
		double sum = 0;
		for (int i = 0; i < activations.size(); i++) {
			sum += activations.get(i) * weights.get(i);
		}
		
		output = sum + bias;
		activation = Functions.sigmoid(output);
		return activation;
	}

	public void setWeight(int i, double value) {
		weights.set(i, value);
	}
	public void setBias(double bias) {
		this.bias = bias;
	}
	
	public double getNumWeights() {
		return weights.size();
	}
	public double getWeight(int i) {
		return weights.get(i);
	}
	public double getBias() {
		return bias;
	}
	public Double getActivation() {
		return activation;
	}
	
	public double getError() {
		return error;
	}

	public void setError(double error) {
		this.error = error;
	}

	public double getDelta() {
		return delta;
	}

	public void setDelta(double delta) {
		this.delta = delta;
	}
	public double getOutput() {
		return output;
	}

	public void setOutput(double output) {
		this.output = output;
	}

	public void setActivation(Double activation) {
		// TODO Auto-generated method stub
		this.activation = activation;
	}
}
