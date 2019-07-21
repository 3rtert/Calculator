package main;

import model.Calculator;

public class Main {
	
	public static void main(String[] args) {
		String str = "4+(3*2-4/8)*(4-2)^((2v16)/2)";
		Calculator cal = new Calculator(str);
		System.out.println(cal.toString());
		System.out.println(cal.calculate());
	}
	
}
