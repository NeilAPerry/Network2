import java.util.ArrayList;
import java.util.function.BiFunction;

public class CostFunction {

	BiFunction<Double, ArrayList<Double>, Double> function;
	
	public CostFunction(BiFunction<Double, ArrayList<Double>, Double> function) {
		this.function = function;
	}

	public Double apply(Double a, ArrayList<Double> b) {
		return this.function.apply(a, b);
	}
	
}
