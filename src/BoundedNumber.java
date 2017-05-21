
public class BoundedNumber {

	private double number;
	private double min;
	private double max;
	
	public BoundedNumber(double number, double min, double max) {
		this.number = number;
		this.min = min;
		this.max = max;
	}
	
	public void updateNumber(double update) {
		if (update > this.max) {
			this.number = this.max;
		} else if (update < this.min) {
			this.number = this.min;
		} else {
			this.number = update;
		}
	}
	
	public double getNumber() {
		return this.number;
	}
}
