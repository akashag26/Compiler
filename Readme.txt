To untar the file, execute the following command:
gunzip Parser.tar.gz
tar -xvf Parser.tar

This project contains the following files:

1. Readme
2. Scanner.class
3. Scanner.java
4, Parser.class
5. Parser.java

Compilation:
To compile the project, execute the following command:
javac Scanner.java
javac Parser.java

How to Run this file?
This parser accepts only one parameter which is the name of input file. Hence, use the following command:
java Parser “Name of input file”
Failing to provide less than or more than one parameter which not let the Parser to execute. 

What does the Scanner work?
1. Tokenize meta-statements as it is.
2. Tokenize keywords without modificiation.
3. Tokenize identifiers with the prefix cs512.
4. Tokenize digits as it is.
5. Deals with space, without generating an error.
6. Tokenize the symbols which are mentioned in the project description.
7. Tokenize everything between two  “…”(inverted comma's)
   characters considering it as a string.
8. If encounters any other character, it will generate an error.

How does the program work?
1. The parser first calls scanner to tokenize the input file and to see if any valid characters are present in the file.
2. If the input file is scanned successfully then it will be parsed.
3.There are 44 non terminals and 89 production rules in the grammar.

If the input file is parsed successfully scanner returns the status (Pass) along with following parameters:
1. Variable count(Both Local and Global)
2. Function count
3. statement

If the input file is not parsed successfully scanner returns the status (Fail).

<program>   			  -   	<typename> ID <data decls> <func list> | empty
<func list>   		          -   	empty | left parenthesis <parameter list> right parenthesis <func> <func decl>
<func>         			  -   	semicolon | left brace <data decls prime> <statements> right brace
<func decl>  			  -   	empty | <typename> ID left parenthesis <parameter list> right parenthesis <func> <func decl>
<typename> 			  -   	int | void | decimal | binary
<parameter list> 		  - 	empty | <non empty list> | void <parameter list prime>
<parameter list prime>  	  -  	empty | ID <non empty list prime>
<non empty list>   		  - 	<typename> ID <non empty list prime>
<non empty list prime>  	  -  	empty | comma <typename> ID <non empty list prime>
<data decls prime> 		  -	empty | <typename> <id list> semicolon <data decls prime>
<data decls>			  - 	empty | <id list plus> semicolon <program> | <id list prime> semicolon <program>
<id list>			  -	<id> <id list prime>
<id list plus> 			  - 	left bracket <expression> right bracket <id list prime>
<id list prime>		  	  -   	empty | comma <id> <id list prime>
<id>				  - 	ID <id prime>
<id prime> 			  - 	empty | left bracket <expression> right bracket
<block statements>		  - 	left brace <statements> right bracket
<statements> 		  	  -	empty | <statement> <statements>
<statement>			  -	read left parenthesis ID right parenthesis semicolon | <if statement> | <while statement> | <return statement> | <break statement> | <continue statement> | write left parenthesis <expression> right parenthesis semicolon | print left parenthesis STRING right parenthesis semicolon | ID <statement prime>
<statement prime>		  - 	<assignment> | <func call>
<assignment>		 	  -	equal sign <expression> semicolon | left bracket <expression> right bracket equal sign <expression> semicolon
<func call>			  -	left parenthesis <expr list> right parenthesis semicolon
<expr list>			  -	empty | <non empty expr list>
<non empty expr list prime> - 	comma <expression> <non empty expr list prime> | empty
<non empty expr list> 	  	  -	<expression> <non empty expr list prime>
<if statement>		  	  - 	if left parenthesis <condition expression> right parenthesis <block statements>
<while statement>		  - 	while left parenthesis <condition expression> right parenthesis <block statements>
 <return statement>		  -	return <return statement prime>
<return statement prime>	  -	<expression> semicolon | semicolon
<break statement>		  - 	break semicolon
<continue statement>	  	  -	continue semicolon
<condition expression>	  	  -	<condition>	<condition expression prime>
<condition expression prime> 	  -	<condition op> <condition> | empty
<condition op>		  	  -	double_and_sign | double_or_sign
<condition>			  -	<expression> <comparison op> <expression>
<comparison op>		  	  -	== | != | > | >= | < | <=
<expression>		  	  - 	<term> <expression prime>
<expression prime>		  -	<add op> <term> <expression prime> | empty
<add op>			  - 	plus_sign | minus_sign
<term>			  	  -   	<factor> <term prime>
<term prime>			  - 	empty | <mul op> <factor> <term prime>
<mul op>			  - 	star_sign | forward_slash
<factor>			  - 	ID <factor prime> | NUMBER | minus_sign NUMBER | left parenthesis <expression> right parenthesis
<factor prime>	 	  	  -	empty | left bracket <expression> right bracket | left parenthesis <expr list> right parenthesis

FIRST+ SETS:

First+(program)  				- 		{int, void, decimal, binary, empty, left parenthesis}
First+(func list)				- 		{empty, left parenthesis}
First+(func)					-		{semicolon, left brace}
First+(func decl)				-		{int, void, decimal, binary, empty, left parenthesis}
First+(typename)				-		{int, void, decimal, binary}
First+(parameter list)				-		{int, void, decimal, binary, empty, right parenthesis}
First+(parameter list prime)			-		{empty, ID, right parenthesis}
First+(non empty list)				- 		{int, void, decimal, binary}
First+(non empty list prime)			-		{empty, comma, right parenthesis}
First+(data decls prime)			-		{int, void, decimal, binary, empty, right bracket, right brace}
First+(data decls)				-		{empty, left bracket, comma, left parenthesis}
First+(id list)					-		{ID}
First+(id)					-		{ID}
First+(id list plus)				-		{left bracket}
First+(id list prime)				-		{empty, comma, semicolon}				
First+(id prime)				-		{empty, left bracket, semicolon}	
First+(block statements)			-		{left brace}
First+(statements)				-		{empty, right brace, right bracket, read, write, print, ID, if, while, return, break, continue}
First+(statement)				-		{read, write, print, ID, if, while, return, break, continue}
First+(statement prime)			-		{equal sign, left parenthesis}
First+(assignment)				-		{equal sign}
First+(func call)				-		{left parenthesis}
First+(expr list)				-		{empty, right parenthesis, ID, NUMBER, minus_sign, left parenthesis}
First+(non empty expr list prime)		-		{empty, counterexamplemma, right parenthesis}
First+(non empty expr list)			-		{ID, NUMBER, minus_sign, left parenthesis}
First+(while statement)			-		{while}
First+(if statement)				-		{if}
First+(continue statement)			-		{continue}
First+(return statement)			-		{return}
First+(break statement)			-		{break}
First+(return statement prime)			-		{ID, NUMBER, minus_sign, left parenthesis}
First+(condition expression)			-		{ID, NUMBER, minus_sign, left parenthesis}
First+(condition expression prime)		-		{double_and_sign, double_OR_sign, empty, right parenthesis}
First+(condition op)				-		{double_and_sign, double_OR_sign}
First+(condition)				-		{ID, NUMBER, minus_sign, left parenthesis}
First+(comparison op)				-		{==, !=, >, >=, <, <=}
First+(expression)				-		{ID, NUMBER, minus_sign, left parenthesis}
First+(expression prime)			-		{plus_sign, minus_sign}
First+(add op)					-		{plus_sign, minus_sign}	
First+(term)					-		{ID, NUMBER, minus_sign, left parenthesis}
First+(term prime)				-		{star_sign, forward_slash, empty, plus_sign, minus_sign}
First+(mul op)					-		{star_sign, forward_slash}
First+(factor)					-		{ID, NUMBER, minus_sign, left parenthesis}
First+(factor prime)				-		{empty, left bracket, left parenthesis, star_sign, forward_slash}

FOLLOW SETS:

Follow(program)  				- 		{left parenthesis}
Follow(func list)				- 		{left parenthesis}
Follow(func)					-		{int, void, decimal, binary}
Follow(func decl)				-		{left parenthesis}
Follow(typename)				-		{ID}
Follow(parameter list)				-		{right parenthesis}
Follow(parameter list prime)			-		{right parenthesis}
Follow(non empty list)				- 		{right parenthesis}
Follow(non empty list prime)			-		{right parenthesis}
Follow(data decls prime)			-		{right bracket, right brace}
Follow(data decls)				-		{left parenthesis}
Follow(id list)					-		{semicolon}
Follow(id)					-		{semicolon}
Follow(id list plus)				-		{semicolon}
Follow(id list prime)				-		{semicolon}				
Follow(id prime)				-		{semicolon}	
Follow(block statements)			-		{read, write, print, ID, if, while, return, break, continue}
Follow(statements)				-		{right brace, right bracket}
Follow(statement)				-		{read, write, print, ID, if, while, return, break, continue}
Follow(statement prime)			-		{read, write, print, ID, if, while, return, break, continue}
Follow(assignment)				-		{left parenthesis}
Follow(func call)				-		{read, write, print, ID, if, while, return, break, continue}
Follow(expr list)				-		{right parenthesis}
Follow(non empty expr list prime)		-		{right parenthesis}
Follow(non empty expr list)			-		{right parenthesis}
Follow(while statement)			-		{read, write, print, ID, if, while, return, break, continue}
Follow(if statement)				-		{read, write, print, ID, if, while, return, break, continue}
Follow(continue statement)			-		{read, write, print, ID, if, while, return, break, continue}
Follow(return statement)			-		{read, write, print, ID, if, while, return, break, continue}
Follow(break statement)			-		{read, write, print, ID, if, while, return, break, continue}
Follow(return statement prime)			-		{read, write, print, ID, if, while, return, break, continue}
Follow(condition expression)			-		{right parenthesis}
Follow(condition expression prime)		-		{right parenthesis}
Follow(condition op)				-		{ID, NUMBER, minus_sign, left parenthesis}
Follow(condition)				-		{double_AND_sign, double_OR_sign, right parenthesis}
Follow(comparison op)				-		{ID, NUMBER, minus_sign, left parenthesis}
Follow(expression)				-		{right bracket, right parenthesis, semicolon, comma, ==, !=, >, >=, <, <=, double_AND_sign, double_OR_sign}
Follow(expression prime)			-		{plus_sign, minus_sign}
Follow(add op)					-		{ID, NUMBER, minus_sign, left parenthesis}	
Follow(term)					-		{plus_sign, minus_sign}	
Follow(term prime)				-		{plus_sign, minus_sign}	
Follow(mul op)					-		{ID, NUMBER, minus_sign, left parenthesis}
Follow(factor)					-		{star_sign, forward_slash}
Follow(factor prime)				-		{star_sign, forward_slash}


