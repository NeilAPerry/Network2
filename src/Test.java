import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		ArrayList<Double> a = new ArrayList<Double>();
		a.add(1.0);
		a.add(1.0);
		a.add(1.0);
		
		ArrayList<Double> b = new ArrayList<Double>();
		b.add(0.5);
		b.add(0.5);
		b.add(0.5);
		
		System.out.println(CostFunctions.crossEntropyCost.apply(a, b));
	}
}
