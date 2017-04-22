import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Network {
	private CostFunction cost;
	private ArrayList<ArrayList<Neuron>> neurons;
	
	Network(ArrayList<Integer> sizes, CostFunction cost) {
		neurons = new ArrayList<ArrayList<Neuron>>(sizes.size());
		for (int i = 0; i < sizes.size(); i++) {
			neurons.add(new ArrayList<Neuron>());
		}
		for (int i = 0; i < sizes.size(); i++) {
			neurons.set(i, new ArrayList<Neuron>(sizes.get(i)));
			for (int j = 0; j < sizes.get(i); j++) {
				neurons.get(i).add(new Neuron(0));
			}
			
		}
		
		for (int i = 0; i < neurons.size(); i++) {
			for (int j = 0; j < neurons.get(i).size(); j++) {
				if (i > 0) { // Hidden Layers and Output Layer Neurons
					neurons.get(i).set(j, new Neuron(neurons.get(i - 1).size()));
				} else { // Input Layer Neuron
					// input neurons have no weights
					neurons.get(i).set(j, new Neuron(0));
				}
			}
		}
		this.cost = cost;
	}
	
	public ArrayList<Neuron> output(ArrayList<Double> input) {
		//TODO
		feedForward(input);
		ArrayList<Neuron> output = neurons.get(neurons.size() - 1);
		return output;
	}
	public void learn(ArrayList<Double> input, ArrayList<Double> desiredOutput, double nabla) {
		// feed forward
		feedForward(input);
		// back propagation
		backPropogate(input, desiredOutput, nabla);
	}
	
	private void feedForward(ArrayList<Double> input) {
		// maybe input neurons' output should be the input to the network?
		for (int j = 0; j < neurons.get(0).size(); j++) {
			neurons.get(0).get(j).setOutput(input.get(j));
			neurons.get(0).get(j).setActivation(input.get(j));
		}
		
		// Start with first hidden layer of neurons	
		for (int i = 1; i < neurons.size(); i++) {
			List<Double> activations = 
					neurons.get(i - 1).stream()
						.map((Neuron n) -> { return n.getActivation();})
						.collect(Collectors.toList());
			neurons.get(i).stream()
					.map((Neuron n) -> { return n.activate(activations); })
					.collect(Collectors.toList());
		}
	}
	
	private void backPropogate(ArrayList<Double> input, ArrayList<Double> desiredOutput, double nabla) {
		//TODO
/*//		double error = cost(input, output);
		ArrayList<Double> delta = new ArrayList<Double>(neurons.get(neurons.size() - 1).size());
		ArrayList<Double> deltaWeights = new ArrayList<Double>(neurons.get(neurons.size() - 1).size());
		ArrayList<Double> deltaBiases = new ArrayList<Double>(neurons.get(neurons.size() - 1).size());
		
		for (int i = 0; i < neurons.get(neurons.size() - 1).size(); i++) {
			delta.add(0.0);
		}
		
		for (int i = 0; i < deltaWeights.size(); i++) {
			delta.set(i, desiredOutput.get(i) - neurons.get(neurons.size() - 1).get(i).getActivation());
		}
		
		
		// change deltas to apply to weights of each neuron
		
		// update weights in hidden layer
		for (int i = neurons.size() - 1; i > 0; i--) {
			for (int j = 0; j < neurons.get(i).size(); j++) {
				// for each neuron find deltas of weights
		/*		for (int k = 0; k < neurons.get(i).get(j).getNumWeights(); k++) {
					// i think this is the wrong delta... but...
					deltaWeights.set(k, )
				}*//*
				
				for (int k = 0; k < neurons.get(i).get(j).getNumWeights(); k++) {
					
					neurons.get(i).get(j).setWeight(k, neurons.get(i).get(j).getWeight(k) - (neurons.get(i - 1).get(k).getActivation() * delta.get(k) * Functions.sigmoidPrime(neurons.get(i - 1).get(k).getActivation()) * nabla));
					//neurons.get(i).get(j).setWeight(k, neurons.get(i).get(j).getWeight(k) - (neurons.get(i - 1).get(k).getActivation() * delta.get(k) *  nabla));
				}
			}
		}
		// update biases
		for (int i = neurons.size() - 1; i > 0; i--) {
			for (int j = 0; j < neurons.get(i).size(); j++) {
				
					neurons.get(i).get(j).setBias(neurons.get(i).get(j).getBias() - (neurons.get(i).get(j).getActivation() * delta.get(j) *  nabla));
				
			}
		}*/
		backpropError(desiredOutput);
		updateWeights(nabla);
		
	}
	private void backpropError(ArrayList<Double> desiredOutput) {

		for (int i = neurons.size() - 1; i >= 0; i--) {
			ArrayList<Neuron> layer = neurons.get(i);
			ArrayList<Double> errors = new ArrayList<Double>();
			
			if (i < neurons.size() - 1) {
				for (int j = 0; j < layer.size(); j++) {
					double error = 0.0;
					for (int k = 0; k < layer.get(j).getNumWeights(); k++) {
						error += layer.get(j).getWeight(k) * layer.get(j).getDelta();
					}
					errors.add(error);
				}
			} else {
				for (int j = 0; j < layer.size(); j++) {
					Neuron neuron = layer.get(j);
					errors.add(desiredOutput.get(j) - neuron.getActivation());
				}
			}
			for (int j = 0; j < layer.size(); j++) {
				Neuron neuron = layer.get(j);
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
		 */
		
		
		
	}

}
