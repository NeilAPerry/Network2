import java.util.ArrayList;
import java.util.function.BiFunction;

public class CostFunction {

	BiFunction<ArrayList<Double>, ArrayList<Double>, Double> function;
	
	CostFunction(BiFunction<ArrayList<Double>, ArrayList<Double>, Double> function) {
		this.function = function;
	}

	public Double apply(ArrayList<Double> a, ArrayList<Double> b) {
		return this.function.apply(a, b);
	}
	
}
