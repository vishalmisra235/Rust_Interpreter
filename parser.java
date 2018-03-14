import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.io.*;
public class parser extends lexer  {
	int count=0;int c=0;
	String token;
	HashMap<String,String> terminals=new HashMap<String,String>();//for storing non terminals as characters

	ArrayList<String> var=new ArrayList<String>();//for storing all variables defined
	ArrayList<String> immutable=new ArrayList<String>();//for storing immutable variables
	ArrayList<String> mutables=new ArrayList<String>();//for storing mutable variables
		public parser() throws Exception
	{
		super("/home/xan/Rust_Interpreter-master/code.txt"); //// CHANGE PATH

		getToken();
		parsing();
	}

	//gets each token from list of words for parsing
	public void getToken()throws Exception
	{

		do {
			if(count==words.size())
			{
				System.out.println("Parsed Successfully!!!");
				System.exit(0);
			}
			String s=words.get(count);
			count++;
			token=s;
		} while (token.equals("$"));
	}

	//Variable name handling
	public void Vari(boolean mutable) throws Exception
	{
		System.out.println("Vari...");
		System.out.println(token);
		System.out.println(" ");

		////////////////////////////////////////////////////////UPDATE with isDefined
		for(String x :var)
			{
				if(x.equals(token))
				{
					System.out.println("Variable already defined");
					System.exit(0);
				}
			}
		////////////////////////////////////////////////////////////

		//If variable not defined earlier
		//Add to mut/immut and var lists accordingly
		if(mutable==true)
			{
				mutables.add(token);
				var.add(token);
			}
			else
			{
				immutable.add(token);
				var.add(token);
			}

		getToken();
		return;
	}

	//Mutability
 	boolean Muta() throws Exception
	{
		System.out.println("Muta...");
		System.out.println(token);
		System.out.println(" ");

		if(token.equals("mut"))
		{
			getToken();
			return true;
		}
		else
		{
			getToken();
			return false;
		}
	}



	//checks data types
	//PASSING STRING TO DEFINE VARIABLE AND LINKING MEMORY LOCATION MAP
	public void Datatype(String v) throws Exception
	{
		System.out.println("Datatype...");
		System.out.println(token);
		System.out.println(" ");

		if(token.equals("i64")||token.equals("i32")||token.equals("u32")||token.equals("u64"))
		{
			getToken();
			return;
		}
		else
		{
			System.out.println("Error in defining datatype");
			System.exit(0);
		}
	}

	//Expression
	public void Exp() throws Exception
	{
		System.out.println("Exp...");
		System.out.println(token);
		System.out.println(" ");

		getToken();
		return;
	}


	public void Init() throws Exception
	{
		System.out.println("Init...");
		System.out.println(token);
		System.out.println(" ");

		if(token.equals("="))
			{
				getToken();
				Exp();
				return;
			}
		else
			{
				System.out.println("Parse error1");
				System.exit(0);
			}
	}
	//function for variable declaration
	public void Var_dec() throws Exception
	{
		System.out.println("Var_dec...");
		System.out.println(token);

		if(!token.equals("let") )
			{
				System.out.println("Variable declaration error");
				System.exit(0);
			}
		//currently: token == mut | variable

		//temp string containg name of var
		String var_name="";

		//Checking if mut (optional)
		getToken();
		boolean mutable=Muta();

		Vari(mutable);

		var_name=token;

			//currently: token == ; | :
			if(token.equals(";")) //For case "let mut n;"
			{
				getToken();
				return;
			}
			else if(token.equals(":"))//For cases like "let mut x: i32 = 5;"
			{
				getToken();
				Datatype(var_name);
				Init();
				if(token.equals(";")) //For case "let mut n;"
				{
					System.out.println(token);
					getToken();
					return;
				}
				else
				{
					System.out.println("Variable declaration ';' error");
					System.exit(0);
				}
			}
			else
			{
				System.out.println("Variable declaration error.");
				System.exit(0);
			}
	}


	public void parsing() throws Exception
	{
		System.out.println("parsing...");
		System.out.println(token);
		System.out.println(" ");

		while(count<words.size()&&token!="@")
		{
			stmt();
		}
		return;

	}

	public boolean isDefined() throws Exception
{
	for(String x :var)
			{
				if(x.equals(token))
				{
					getToken();
					return true;
				}
			}

	getToken();
	return false;
}

//(To develop)bool_exp 	:= 	term logic_operator term
//				term 	:=	isdefined_variable | constant
//Improvements to be done: Type checking for boolean_exp

//(CURRENT: Simplified) Bool expression of type:=  variable logic_operator const
public void bool_exp() throws Exception
{
	System.out.println("Bool_exp...");
	System.out.println(token);
	System.out.println(" ");

	if(!isDefined())
	{
		System.out.println("Variable"+ token +" not defined. ");
		System.exit(0);
	}

	System.out.println("Bool_exp logic op...");
	System.out.println(token);
	System.out.println(" ");

	if(token.equals("==")||token.equals("!=")||token.equals("<")||token.equals(">")||token.equals("<=")||token.equals(">="))
		{
			getToken();
			//Get the Constant;

			System.out.println("Bool_exp compare...");
			System.out.println(token);
			System.out.println(" ");
		}
	else
		{
			//Error Handling
			System.out.println("Boolean Expression error in if statement.");
			System.out.println(" '"+token+"' is an unknown logical operator");
			System.exit(0);
		}

	//Accepts whatever value assigned to variable
	getToken();
	return;

}

//else_tail := "else" stmt | epsilon(@)
public void else_tail() throws Exception
{
	if(token.equals("else"))
	{
		getToken();
		System.out.println("else_tail...");
		System.out.println(token);
		System.out.println(" ");


		if(token.equals("{"))
		{
			getToken();
			System.out.println(token);
			System.out.println(" ");

			stmt();

			if(token.equals("}"))
			{
				getToken();
				System.out.println(token);
				System.out.println(" ");
				return;
			}
			else
			{
				System.out.println("Error! Expecting '}' at else-block");
				System.exit(0);
			}


		}
		else
		{
			System.out.println("Error! Expecting '{' at else-block");
			System.exit(0);
		}
	}
	else
	{
		getToken();
		return;
	}
}
//Stmt := "if" bool_exp "{" Stmt "}" else_tail | Var_dec |
public void stmt() throws Exception
{
	System.out.println("Stmt...");
	System.out.println(token);
	System.out.println(" ");

	if(token.equals("if"))
	{
		getToken();
		System.out.println("if...");
		System.out.println(token);
		System.out.println(" ");

		bool_exp();

		if(token.equals("{"))
		{

			getToken();
			System.out.println(token);
			System.out.println(" ");

			stmt();

			if(token.equals("}"))
			{
				getToken();
				System.out.println(token);
				System.out.println(" ");

				else_tail();
				return;
			}
			else
			{
				//Error Handling
				System.out.println("Error! Expecting '}' at if-block");
				System.exit(0);
			}


		}
		else
		{
			//Error Handling
			System.out.println("Error! Expecting '{' after 'if' ");
			System.exit(0);
		}
	}
	else if(token.equals("let"))
	{
		Var_dec();
		return;
	}
	else if(token.equals(";"))
		{
			System.out.println("; AAAA");
			getToken();
			return;}
	else
	{
			getToken();
			System.out.print(token);
			stmt();
		 ////////////////////////SUPPOSE A STATEMENT
	}
}

	public static void main(String args[]) throws Exception
	{
			parser ob1=new parser();

	}
}

