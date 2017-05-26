import java.util.*;
import java.util.stream.Collectors;

public class Network {
	private ArrayList<ArrayList<Neuron>> neurons;
	
	Network(ArrayList<Integer> sizes) {
		// create network
		neurons = new ArrayList<ArrayList<Neuron>>(sizes.size() - 1);
		
		// creates layers - starting with first hidden layer
		for (int i = 0; i < sizes.size() - 1; i++) {
			neurons.add(new ArrayList<Neuron>(sizes.get(i + 1)));
			
			// creates neurons
			for (int j = 0; j < sizes.get(i + 1); j++) {
				neurons.get(i).add(new Neuron(sizes.get(i)));
			}
		}
	}
	
	
	private ArrayList<Double> feedForward(ArrayList<Double> input) {
		
		ArrayList<Double> output = new ArrayList<Double>();
		
		for (int i = 0; i < neurons.size(); i++) {
			// first hidden layer
			if (i == 0) {
				for (Neuron neuron : neurons.get(i)) {
					output.add(neuron.activate(input));
				}
			// other hidden layers or output layer
			} else {
				input = output;
				output = new ArrayList<Double>();
				for (Neuron neuron : neurons.get(i)) {
					output.add(neuron.activate(input));
				}
			}
		}
		
		return output;
	}
	
	private void backPropogate(ArrayList<Double> input, ArrayList<Double> desiredOutput, double nabla) {
		
		double error = CostFunctions.quadraticCost.apply(feedForward(input), desiredOutput);
		
		for (int i = neurons.size() - 1; i >= 0; i--) {
			for (Neuron neuron : neurons.get(i)) {
				if (i == 0) {
					neuron.backprop(nabla, error, input);
				} else {
					neuron.backprop(nabla, error, (ArrayList<Double>) neurons.get(i - 1).stream().map(n -> { return n.getOutput(); } ).collect(Collectors.toList()));
				}
			}
		}
		
//		backpropError(desiredOutput);
//		updateWeights(nabla);
		
	}
	
	public ArrayList<Double> learn(ArrayList<Double> input, ArrayList<Double> desiredOutput, double nabla) {
		
		backPropogate(input, desiredOutput, nabla);
		return feedForward(input);
	}
	
	public ArrayList<Double> output(ArrayList<Double> input) {
		return feedForward(input);
	}
/*	
	private void backpropError(ArrayList<Double> desiredOutput) {

		// for all layers starting at last layer
		for (int i = neurons.size() - 1; i >= 0; i--) {
			ArrayList<Neuron> layer = neurons.get(i);
			ArrayList<Double> errors = new ArrayList<Double>();
			
			// if not output layer
			if (i < neurons.size() - 1) {
				for (int j = 0; j < layer.size(); j++) {
					double error = 0.0;
					for (int k = 0; k < layer.get(j).getNumWeights(); k++) {
						// not sure about this...
						error += layer.get(j).getWeight(k) * layer.get(j).getDelta();
					}
					errors.add(error);
				}
			// if output layer
			} else {
				for (int j = 0; j < layer.size(); j++) {
					Neuron neuron = layer.get(j);
					// output or activation for neuron?
					errors.add(desiredOutput.get(j) - neuron.getActivation());
				}
			}
			for (int j = 0; j < layer.size(); j++) {
				Neuron neuron = layer.get(j);
				// output or activation for neuron?
				// is this right for cross entropy cost?
				neuron.setDelta(errors.get(j) * Functions.sigmoidPrime(neuron.getOutput()));
			}
		}
	}
	private void updateWeights(double nabla) {
		// most likely using wrong error and input
		for (int i = 0; i < neurons.size(); i++) {
			for (int j = 0; j < neurons.get(i).size(); j++) {
				for (int k = 0; k < neurons.get(i).get(j).getNumWeights(); k++) {
					neurons.get(i).get(j).setWeight(k, neurons.get(i).get(j).getWeight(k) + (nabla * neurons.get(i - 1).get(k).getError() * neurons.get(i - 1).get(k).getOutput()));
				}
			}
		}
		
		//weight = weight + learning_rate * error * input
		/*Where weight is a given weight, learning_rate is a parameter that you must specify, 
		 * error is the error calculated by the backpropagation procedure for the neuron and 
		 * input is the input value that caused the error.
		 
		
		
		
	}*/

}