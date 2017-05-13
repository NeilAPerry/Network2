import java.util.ArrayList;

public final class CostFunctions {
	public static CostFunction crossEntropyCost;
	public static CostFunction quadraticCost;
	
	private CostFunctions() {}
	
	static {
		crossEntropyCost = new CostFunction((ArrayList<Double> desiredOutput, ArrayList<Double> output) -> {
			double runningSum = 0.0;
			for (int i = 0; i < output.size(); i++) {
				if (!(output.get(i) == 1.0 && desiredOutput.get(i) == 1.0)) {
					runningSum += ((-1 * desiredOutput.get(i)) * Math.log(output.get(i))) - ((1 - desiredOutput.get(i)) * Math.log(1 - output.get(i)));

				}
			}
			return runningSum;
			}
		);
		
/*		quadraticCost = new CostFunction((Double y, ArrayList<Double> a) -> {
			double runningSum = 0.0;
			for (int i = 0; i < a.size(); i++) {
				if (!(a.get(i) == 1.0 && y == 1.0)) {
					runningSum += ((-1 * y) * Math.log(a.get(i))) - ((1 - y) * Math.log(1 - a.get(i)));

				}
			}
			return runningSum;
			}
		);  */// TODO implement quadratic cost
	}
	
	
}
