package model;

import java.util.function.BinaryOperator;

public class Operation {
	/* 0 - exponentiation
	 * 1 - roots
	 * 2 - multiplication
	 * 3 - division
	 * 4 - addition
	 * 5 - subtraction
	 * -1 - not initialized (operation return 1st argument as result)
	 */
	private int id=-1;
	public static final int MAXID=5;
	public static final char EXPONENTIATION='^';
	public static final char ROOTS='v';
	public static final char MULTIPLICATION='*';
	public static final char DIVISION='/';
	public static final char ADDITION='+';
	public static final char SUBTRACTION='-';
	public static final char NOTINITIALIZED=' ';

	public Operation(int id) {
		this.id = id;
	}
	
	public Operation(char c) {
		if(c==EXPONENTIATION)
			id=0;
		else if(c==ROOTS)
			id=1;
		else if(c==MULTIPLICATION)
			id=2;
		else if(c==DIVISION)
			id=3;
		else if(c==ADDITION)
			id=4;
		else if(c==SUBTRACTION)
			id=5;
		else
			id=-1;
	}
	
	public int getId() {
		return id;
	}

	BinaryOperator<Double> getBinaryOperator() {
		if(id==0) {
			return (a, b)->Math.pow(a, b);
		}
		else if(id==1) {
			return (a, b)->Math.pow(b, 1/a);
		}
		else if(id==2) {
			return (a, b)->a*b;
		}
		else if(id==3) {
			return (a, b)->a/b;
		}
		else if(id==4) {
			return (a, b)->a+b;
		}
		else if(id==5) {
			return (a, b)->a-b;
		}
		else {
			return (a, b)->a;
		}
	}
	
	public char toChar()
	{
		if(id==0) {
			return EXPONENTIATION;
		}
		else if(id==1) {
			return ROOTS;
		}
		else if(id==2) {
			return MULTIPLICATION;
		}
		else if(id==3) {
			return DIVISION;
		}
		else if(id==4) {
			return ADDITION;
		}
		else if(id==5) {
			return SUBTRACTION;
		}
		else {
			return NOTINITIALIZED;
		}
	}
	@Override
	public String toString() {
		return Character.toString(toChar());
	}
}
