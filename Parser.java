import java.util.List;

public class Parser {
	int current, variableCount, functionCount, statementCount;
	String[] tokens;
	Scanner myScanner;
	
	public boolean isNumber() {
		if (tokens[current].matches("[0-9]+")) {
			current++;
			return true;
		} else
			return false;
	}
 	
	public boolean isString() {
		if(tokens[current].startsWith("\"") && tokens[current].startsWith("\"") )
		{
			current++;
			return true;
		}
		else
			return false;
	}
	
	public boolean isIdentifier(){
		if(tokens[current].startsWith("cs512") || tokens[current].equals("main"))
		{
			current++;
			return true;
		}
		else
			return false;
	}
	
	public boolean isProgram(){
		if(tokens.length<=0)
			System.exit(0);
		
		if(isTypeName()){
			if(isIdentifier() && isDataDecls() && isFuncList())
				return true;
			else
				return false;
		}
		else
			return true;
	}
	
	public boolean isFuncList(){
		if(tokens.length==current)
			return true;
		if(tokens[current].equals("("))
		{
			current++;
			if(isParameterList() && tokens[current++].equals(")") && isFunc() && isFuncDecl())
				return true;
			else
				return false;
		}
		else
			return true;
	}
	
	public boolean isFunc(){
		if(tokens[current].equals(";"))
		{
			current++;
			return true;
		}
		else
		{
			if(tokens[current++].equals("{") && isDataDeclsPrime() && isStatements() && tokens[current++].equals("}"))
			{
				functionCount++;
				return true;
			}
			else
				return false;
		}
		
	}
	
	public boolean isFuncDecl(){
		if(tokens.length==current)
			return true;
		int temp = current;
		if(isTypeName()){
			if(isIdentifier() && tokens[current++].equals("(") && isParameterList() && tokens[current++].equals(")") && isFunc() && isFuncDecl())
				return true;
			else
				return false;
		}
		else
			{
			current = temp;
			return true;
			}
	}
	
	public boolean isTypeName(){
		if(tokens[current].equals("int") || tokens[current].equals("void") || tokens[current].equals("decimal") || tokens[current].equals("binary"))
		{
			current++;
			return true;
		}
		else
			return false;
	}
	
	public boolean isParameterList(){
		if(tokens[current].equals("void")){
			current++;
			return isParameterListPrime();
		}
		else if(isNonEmptyList())
			return true;
		else
			return true;
		
	}
	
 public boolean	isParameterListPrime(){
	 if(isIdentifier())
		return isNonEmptyListPrime();
	 else
		 return true;
 }
 
 public boolean	isNonEmptyList(){
	 if(isTypeName() && isIdentifier() && isNonEmptyListPrime())
		 return true;
		 else
			 return false;
 }

 
 public boolean	isNonEmptyListPrime(){
	 if(tokens[current].equals(","))
		{
		 //variableCount++;
		 current++;
		 if(isTypeName() && isIdentifier() && isNonEmptyListPrime())
			 return true;
			 else
				 return false;
		}
	 else
		 return true;
 }
 
 public boolean isDataDeclsPrime(){
	 if(isTypeName()){
		 if(isIdList() && tokens[current++].equals(";") && isDataDeclsPrime())
		 {
			 return true;
		 }
		 else
			 return false;
	 }
	 else
		 return true;
}
 
 public boolean isDataDecls(){
	 int temp  = current;
	 if(isIdListPlus()){
		 if(tokens[current++].equals(";")){
			 if(isProgram())
			 return true;
		 }
		 else
			 return false;
	 }
	 else
		 if(isIdListPrime()){
			 if(tokens[current].equals(";") )
				 {
				 current++;
				 	if(isProgram())
			 	 		return true;
				 }
				else{
					 current = temp;
					 return true;
				 }
		 }
		 else
			 {
			 current = temp;
			 return true;
			 }
	 return false;
 }
 
 
 public boolean isIdList(){
	 if(isId() && isIdListPrime())
	 {
		 variableCount++;
		 return true;
	 }
	 else
		 return false;
 }
 
 public boolean isIdListPlus(){
	 if(tokens[current++].equals("[") && isExpression() && tokens[current++].equals("]") && isIdListPrime())
	 {
		 variableCount++;
		 return true;
	 }
	 else
		 return false;
 }
 
 public boolean isIdListPrime(){
	 if(tokens[current].equals(",")){
		variableCount++;
		 current++;
		 if(isId() && isIdListPrime())
			 return true;
		 else
			 return false;
	 }
	 else
		 return true;
 }
 
 public boolean isId(){
	 if(isIdentifier() && isIdPrime())
		 return true;
	 else
		 return false;
	 
 }
 
 public boolean isIdPrime(){
	 if(tokens[current].equals("["))
	 {
		 current++;
		 if(isExpression() && tokens[current].equals("]"))
			 return true;
		 else
			 return false;
	 }
	 else
		 return true; 
 }
 
 public boolean isBlockStatements(){
	 if(tokens[current++].equals("{") && isStatements() && tokens[current++].equals("}"))
		 return true;
	 else
		 return false;
	
 }
 
 
 public boolean isStatements(){
	 if(isStatement())
	 {
		 statementCount++;
		 return isStatements();
	 }
	 else
		 return true;

 }

 public boolean isStatement(){
	 if(tokens[current].equals("read"))
	 {
		 current++;
		 if(tokens[current++].equals("(") && isIdentifier() && tokens[current++].equals(")") && tokens[current++].equals(";"))
			 return true;
	 }	
	 else if(isIfStatement())
		 return true;
	 else if(isWhileStatement())
		 return true;
	 else if(isReturnStatement())
		 return true;
	 else if(isBreakStatement())
		 return true;
	 else if(isContinueStatement())
		 return true;
	 else
		 if(tokens[current].equals("write"))
		 {
			 current++;
			 if(tokens[current++].equals("(") && isExpression() && tokens[current++].equals(")") && tokens[current++].equals(";"))
				 return true;
		 }	 
		 else
			 if(tokens[current].equals("print"))
			 {
				 current++;
				 if(tokens[current++].equals("(") && isString() && tokens[current++].equals(")") && tokens[current++].equals(";"))
					 return true;
			 }	 
			 else
				 if(isIdentifier() && isStatementPrime())
					 return true;	 
	 return false;
 }
 
 public boolean isStatementPrime(){
	 if(isAssignment() || isFuncCall())
		 return true;
	 else
		 return false;		  
 }
	
 public boolean isAssignment(){
	 if(tokens[current].equals("="))
	 {
		 current++;
		 if(isExpression()  && tokens[current++].equals(";"))
			 return true;
	 }	 
	 else
		 if(tokens[current].equals("["))
		 {
			 current++;
			 if(isExpression() && tokens[current++].equals("]") && tokens[current++].equals("=") && isExpression() && tokens[current++].equals(";"))
				 return true;
		 }	 
	 return false;
 }
 
 public boolean isFuncCall(){
	 if( tokens[current++].equals("(") && isExprList() && tokens[current++].equals(")") && tokens[current++].equals(";"))
		 return true;
	 else
		 return false;
 }
 
 public boolean isExprList(){
	 if(isNonEmptyExprList())
		 return true;
	 else
		 return true;	 
 }

 public boolean isNonEmptyExprListPrime(){
	 if(tokens[current].equals(","))
	 {
		 current++;
		 if(isExpression() && isNonEmptyExprListPrime())
			 return true;
		 else
			 return false;
	 }
	 else
		 return true;
 }
 
 public boolean isNonEmptyExprList(){
	 if(isExpression() && isNonEmptyExprListPrime())
		 return true;
	 else
		 return false;
 }
 
 public boolean isIfStatement(){
	 int temp = current;
	 if( tokens[current++].equals("if") && tokens[current++].equals("(") && isConditionExpression() && tokens[current++].equals(")") && isBlockStatements())
		 return true;
	 else
		 {
		 current = temp;
		 return false;
		 }
 }
 
 public boolean isWhileStatement(){
	 int temp = current;
	 if( tokens[current++].equals("while") && tokens[current++].equals("(") && isConditionExpression() && tokens[current++].equals(")") && isBlockStatements())
		 return true;
	 else
	 {
		 current = temp;
		 return false;
	 }
 }
 
 public boolean isReturnStatement(){
	 int temp = current;
	 if( tokens[current++].equals("return") && isReturnStatementPrime() )
		 return true;
	 else
	 {
	 current = temp;
	 return false;
	 }
 }
 
 public boolean isReturnStatementPrime(){
	 if(isExpression()){
		 if(tokens[current].equals(";")){
			 current++;
			 return true;
		 }
		 else
			 return false;
	 }
	 else
		 if (tokens[current++].equals(";"))
			 return true; 
	 return false;
 }
 
 
 public boolean isBreakStatement(){
	 int temp = current;
	 if( tokens[current++].equals("break") && tokens[current++].equals(";") )
		 return true;
	 else
	 {
	 current = temp;
	 return false;
	 }
}

 public boolean isContinueStatement(){
	 int temp = current;
	 if( tokens[current++].equals("continue") && tokens[current++].equals(";") )
		 return true;
	 else
	 {
	 current = temp;
	 return false;
	 }
 }
 
 
 public boolean isConditionExpression(){
	 if( isCondition() &&  isConditionExpressionPrime())
		 return true;
	 else
		 return false;
 }
 
 public boolean isConditionExpressionPrime(){
	 if( isConditionOp())
		 {
		 if( isCondition())
			 return true;
		 else
			 return false;
		 }
	 else
		 return true;
 }
 
 public boolean isConditionOp(){
	 if( tokens[current].equals("||") || tokens[current].equals("&&") )
	 {
		 current++;
		 return true;
	 }
	 else
		 return false;
 }
 
 public boolean isCondition(){
	 if(isExpression() && isComparisonOp() && isExpression() )
		 return true;
	 else
		 return false;
 }
 
 
 public boolean isComparisonOp(){
	 if( tokens[current].equals("==") || tokens[current].equals("!=")|| tokens[current].equals(">") || tokens[current].equals(">=") || tokens[current].equals("<")|| tokens[current].equals("<=")  )
	 {
		 current++;
		 return true;
	 }
	 else
		 return false;
 }
 
 public boolean isExpression(){
	 if(isTerm() && isExpressionPrime() )
		 return true;
	 else
		 return false;
 }
 
 public boolean isExpressionPrime(){
	 if(isAddOp()){
		 if(isTerm() && isExpressionPrime())
			 return true;
		 else
			 return false;
	 }
	 else
		 return true;
 }
 
 
 public boolean isAddOp(){
	 if( tokens[current].equals("+") || tokens[current].equals("-") )
	 {
		 current++;
		 return true;
	 }
	 else
		 return false;
 }
 
 public boolean isTerm(){
	 if(isFactor() && isTermPrime())
		 return true;
	 else
		 return false;
 }
 

 public boolean isTermPrime(){
	 if(isMulOp()){
		 if(isFactor() && isTermPrime())
			 return true;
		 else
			 return false;
	 }
	 else
	 return true;
 }
 
 public boolean isFactor(){
	 int temp = current;
	 if(isIdentifier() && isFactorPrime())
		 return true;
	 else
		 if(isNumber())
			 return true;
		 else
			 if( tokens[current].equals("-") )
			 {
				 current++;
				 if(isNumber())
					 return true;
				 else
					 return false;
			 }
			 else
				 if( tokens[current++].equals("(") && isExpression() && tokens[current++].equals(")") )
					 return true;
				 else
					 {
					 current = temp;
					 return false;
					 }			 
 }
 
 public boolean isFactorPrime(){
	 if( tokens[current].equals("[") )
	 {
		 current++;
		 if(isExpression() && tokens[current++].equals("]"))
			 return true;
		 else
			 return false;
	 }
	 else
		 if( tokens[current].equals("(") )
		 {
			 current++;
			 if(isExprList() && tokens[current++].equals(")"))
				 return true;
			 else
				 return false;
		 }
		 else
			 return true;
 }
 
 
 public boolean isMulOp(){
	 if( tokens[current].equals("*") || tokens[current].equals("/") )
	 {
		 current++;
		 return true;
	 }
	 else
		 return false;
 }
 
 public Parser() {
		myScanner = new Scanner();
		current = 0;
		variableCount = 0;
		functionCount = 0;
		statementCount = 0;
	}

	public static void main(String[] args) {
		Parser obj = new Parser();
		List<String> tokens = obj.myScanner.getAllTokens(args);
			if(tokens!=null){
			obj.tokens = tokens.toArray(new String[tokens.size()]);
				if(obj.isProgram())
					System.out.println("Pass. variable " + obj.variableCount + " function " + obj.functionCount + " statement " + obj.statementCount );
				else
					System.out.println("Fail");			
		}
	}
}
