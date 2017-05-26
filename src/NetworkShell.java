import java.util.ArrayList;

public class NetworkShell {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// set size of network
		ArrayList<Integer> sizes = new ArrayList<>();
		sizes.add(2);
		sizes.add(8);
		sizes.add(1);
		
		// create network
		Network xor = new Network(sizes);
		
		// test data input
		ArrayList<Double> input1 = new ArrayList<>();
		input1.add(0.0);
		input1.add(0.0);
		
		ArrayList<Double> input2 = new ArrayList<>();
		input2.add(1.0);
		input2.add(0.0);
		
		ArrayList<Double> input3 = new ArrayList<>();
		input3.add(0.0);
		input3.add(1.0);
		
		ArrayList<Double> input4 = new ArrayList<>();
		input4.add(1.0);
		input4.add(1.0);
		
		// test data expected output	
		ArrayList<Double> expectedOutput1 = new ArrayList<>();
		expectedOutput1.add(0.0);

		ArrayList<Double> expectedOutput2 = new ArrayList<>();
		expectedOutput2.add(1.0);
		
		ArrayList<Double> expectedOutput3 = new ArrayList<>();
		expectedOutput3.add(1.0);
		
		ArrayList<Double> expectedOutput4 = new ArrayList<>();
		expectedOutput4.add(0.0);
		
		///////////////////////////////////////////////////////
		// train network
		int epochNum = 1000;
		double nabla = 0.2;
		for (int i = 0; i < epochNum; i++) {
			xor.learn(input1, expectedOutput1, nabla);
			xor.learn(input2, expectedOutput2, nabla);
			xor.learn(input3, expectedOutput3, nabla);
			xor.learn(input4, expectedOutput4, nabla);
		}

		////////////////////////////////////////////////////////
		// print result
		System.out.println(xor.output(input1).get(0));
		System.out.println(xor.output(input2).get(0));
		System.out.println(xor.output(input3).get(0));
		System.out.println(xor.output(input4).get(0));
	}
}
