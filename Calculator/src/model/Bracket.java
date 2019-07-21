package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bracket {
	
	private List<Bracket> values = new ArrayList<>();
	private List<Operation> operations = new ArrayList<>();
	private double val=0;
	private boolean changed=true;

	public Bracket() {}
	
	private Bracket(String str, Map<Character,List<String>> map) {
		String temp = str.replace(Operation.EXPONENTIATION, Operation.SUBTRACTION);
		temp = temp.replace(Operation.ROOTS, Operation.SUBTRACTION);
		temp = temp.replace(Operation.MULTIPLICATION, Operation.SUBTRACTION);
		temp = temp.replace(Operation.DIVISION, Operation.SUBTRACTION);
		temp = temp.replace(Operation.ADDITION, Operation.SUBTRACTION);
		char[] c = str.toCharArray();
		
		String[] brackets = temp.split(Character.toString(Operation.SUBTRACTION));
		if(brackets.length==1) {
			char[] chars = brackets[0].toCharArray();
			if(chars.length==1 && map.containsKey(chars[0])) {
				str=map.get(chars[0]).remove(0);
				Bracket tempBracket=new Bracket(str, map);
				this.values=tempBracket.values;
				this.operations=tempBracket.operations;
			}
			else {
				val=Double.parseDouble(brackets[0]);
			}
		}
		else {
			int index=0;
			
			for(int i = 0;i<brackets.length-1;i++) {
				index+=brackets[i].length();
				values.add(new Bracket(brackets[i], map));
				operations.add(new Operation(c[index++]));
			}
			values.add(new Bracket(brackets[brackets.length-1], map));
		}
	}

	protected static Bracket getBracket(String str) {
		Map<Character,List<String>> map = new HashMap<>();
		String pattern="\\([^()]*?\\)";
		Pattern pat = Pattern.compile(pattern);
		char placeholder = 'a'; //97
		boolean bracketsLeft=true;
		while(bracketsLeft) {
			List<String> brackets = new ArrayList<>();
			Matcher matcher = pat.matcher(str);
			matcher.reset();
			bracketsLeft=false;
			while(matcher.find()) {
				brackets.add((String) str.subSequence(matcher.start()+1, matcher.end()-1));
				bracketsLeft=true;
			}
			if(!brackets.isEmpty())
				map.put(placeholder, brackets);
			str=str.replaceAll(pattern, Character.toString(placeholder));
			placeholder++;
		}
		Bracket newBracket = new Bracket(str, map);
		return newBracket;
	}

	protected double getDoubleValue() {
		return val;
	}

	protected double calculate() {
		if(changed && !operations.isEmpty()) {
			values.stream().forEach(item->item.calculate());
			List<Bracket> tempValues = new ArrayList<>(values);
			List<Operation> tempOperations = new ArrayList<>(operations);
			for(int i = 0; i<=Operation.MAXID; i++) {
				for(int j = 0;j<tempOperations.size();j++) {
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
	
	private double doOperation(Bracket bracket, BinaryOperator<Double> o) {
		return o.apply(this.val, bracket.val);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(!operations.isEmpty()) {
			sb.append("(");
			sb.append(values.get(0));
			for(int i = 1;i<values.size();i++) {
				sb.append(operations.get(i-1).toChar());
				sb.append(values.get(i).toString());
			}
			sb.append(")");
		}
		else {
			sb.append(val);
		}
		return sb.toString();
	}
}
