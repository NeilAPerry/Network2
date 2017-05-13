import java.util.ArrayList;

public class NetworkShell {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> i = new ArrayList<Integer>();
		i.add(2);
		i.add(3);
		i.add(1);
		Network j = new Network(i);
		j.setCost(CostFunctions.crossEntropyCost);
		
		ArrayList<ArrayList<Double>> input = new ArrayList<ArrayList<Double>>();
		for (int a = 0; a < 4; a++) {
			input.add(new ArrayList<Double>());
			for (int b = 0; b < 2; b++) {
				input.get(a).add(0.0);
			}
		}
		input.get(0).set(0, 0.0);
		input.get(0).set(1, 0.0);
		input.get(1).set(0, 0.0);
		input.get(1).set(1, 1.0);
		input.get(2).set(0, 1.0);
		input.get(2).set(1, 0.0);
		input.get(3).set(0, 1.0);
		input.get(3).set(1, 1.0);
		

		ArrayList<ArrayList<Double>> desiredOutput = new ArrayList<ArrayList<Double>>();
				for (int a = 0; a < 4; a++) {
			desiredOutput.add(new ArrayList<Double>());
			for (int b = 0; b < 2; b++) {
				desiredOutput.get(a).add(0.0);
			}
		}
		desiredOutput.get(0).set(0, 0.0);
		desiredOutput.get(0).set(1, 0.0);
		desiredOutput.get(1).set(0, 0.0);
		desiredOutput.get(1).set(1, 1.0);
		desiredOutput.get(2).set(0, 1.0);
		desiredOutput.get(2).set(1, 0.0);
		desiredOutput.get(3).set(0, 1.0);
		desiredOutput.get(3).set(1, 1.0);

		ArrayList<ArrayList<Double>> altOutput = new ArrayList<ArrayList<Double>>();
		for (int v = 0; v < 4; v++) {
			altOutput.add(new ArrayList<Double>());
		}
		altOutput.get(0).add(0.0);
		altOutput.get(1).add(1.0);
		altOutput.get(2).add(1.0);
		altOutput.get(3).add(0.0);
		for (int t = 0; t < 100000; t++) {
			for (int z = 0; z < input.size(); z++) {
				j.learn(input.get(z), altOutput.get(z), .001);
			}
		}


		for (int u = 0; u < input.size(); u++) {
			System.out.println(j.output(input.get(u)).get(0).getActivation());
		}
		
	}

}
