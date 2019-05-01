package main;

import model.Bracket;

public class Main {
	
	public static void main(String[] args) {
		String str = "4+(3*2-4/8)*(4-2)^((2v16)/2)";
		//splitBrackets("(2*((4)^2)+(4:3))");
		Bracket b = Bracket.getBracket(str);
		System.out.println(b.toString());
		System.out.println(b.calculate());
	}

}
