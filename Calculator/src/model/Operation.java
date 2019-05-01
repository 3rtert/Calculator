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
	public static final char exponentiation='^';
	public static final char roots='v';
	public static final char multiplication='*';
	public static final char division='/';
	public static final char addition='+';
	public static final char subtraction='-';
	public static final char notInitialized=' ';

	public Operation(int id) {
		this.id = id;
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
			return exponentiation;
		}
		else if(id==1) {
			return roots;
		}
		else if(id==2) {
			return multiplication;
		}
		else if(id==3) {
			return division;
		}
		else if(id==4) {
			return addition;
		}
		else if(id==5) {
			return subtraction;
		}
		else {
			return notInitialized;
		}
	}
}
