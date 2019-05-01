package model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

public class Bracket {
	
	private List<Bracket> values = new ArrayList<>();
	private List<Operation> operations = new ArrayList<>();
	private double val=0;
	private boolean changed=true;

	public Bracket getValue(String str) {
		
		
		changed=true;
		return null;
	}

	public double getDoubleValue() {
		return val;
	}

	public double calculate() {
		if(changed) {
			values.stream().forEach(item->item.calculate());
			List<Bracket> tempValues = new ArrayList<>(values);
			List<Operation> tempOperations = new ArrayList<>(operations);
			for(int i = Operation.MAXID; i>=0; i--) {
				for(int j = 0;j<operations.size();j++) {
					if(i==tempOperations.get(j).getId()) {
						tempValues.get(j).val=tempValues.get(j).doOperation(tempValues.get(j+1), tempOperations.get(j).getBinaryOperator());
						tempValues.remove(j+1);
						tempOperations.remove(j);
						j--;
					}
				}
			}
			val=tempValues.get(0).val;
			changed=false;
		}
		return val;
	}
	
	double doOperation(Bracket bracket, BinaryOperator<Double> o) {
		return o.apply(this.val, bracket.val);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(values.get(0));
		for(int i = 1;i<values.size();i++) {
			sb.append(operations.get(i-1).toChar());
			sb.append(values.get(i).toString());
		}
		sb.append(")");
		return sb.toString();
	}
}
