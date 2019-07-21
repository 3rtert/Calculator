package model;

public class Calculator {
	
	private Bracket bracket = new Bracket();
	
	public Calculator() {}
	
	public Calculator(String str) {
		bracket=Bracket.getBracket(str);
	}
	
	public double getDoubleValue() {
		return bracket.getDoubleValue();
	}
	
	public double calculate() {
		return bracket.calculate();
	}
	
	@Override
	public String toString() {
		return bracket.toString();
	}
}
