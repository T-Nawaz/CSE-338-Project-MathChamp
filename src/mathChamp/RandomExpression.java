package mathChamp;

import java.util.HashMap;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class RandomExpression {
	
	private String expression;
	private double result;
	private int expressionLength;
	private int digitSize;
	private HashMap<String, Integer> operatorPrecedenceTable = new HashMap<>();
	
	private static ScriptEngine jsEngine = new ScriptEngineManager().getEngineByName("javascript");
	
	
	public RandomExpression() {
		
		operatorPrecedenceTable.put("+", 1);
		operatorPrecedenceTable.put("-", 1);
		//operatorPrecedenceTable.put("*", 2);
		//operatorPrecedenceTable.put("/", 2);
		
		expressionLength = 3;
		digitSize = 2;
		
		generateNewExpression();
		
	}
	
	public void generateNewExpression() {
		expression = "";
		for(int i = 0; i < expressionLength; i++)
			expression +=  Integer.toString((int)(Math.random() * 10e5 % Math.pow(10, digitSize))) 
							+ (i < expressionLength - 1 ? 
									operatorPrecedenceTable
										.keySet()
											.toArray()[ 
									            (int)(Math.random() * 10e5) % operatorPrecedenceTable.size()
							            ] 
				            		: ""
		            			);
		
		try {
			result =  new Double( (jsEngine.eval(expression)).toString() );
		} catch (ScriptException e) {
			//e.printStackTrace();
			System.err.println("Java Environment doesn't support JS engine! Need to build your own expression evaluator");
		}
		
	}
	
	public String getExpression() {
		return expression;
	}
	
	public double getResult() {
		return result;
	}
	
	public void setExpressionLength (int length) {
		expressionLength = length;
	}
	
	public void setDigitSize(int size) {
		digitSize = size;
	}
	
	public void addOperator(String symbol, int precendence) {
		operatorPrecedenceTable.put(symbol, precendence);
	}
	
	
}
