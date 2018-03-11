
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
		super("C:/Users/PRIYANSH SANGULE/Rust_Interpreter-master/code.txt");
		
		getToken();
		parsing();
		terminals.put("let", "L");
		terminals.put("mut", "M");
		terminals.put("i64", "D");
		terminals.put("usize", "U");
		
	}
	
	//gets each token from list of words for parsing	
	public void getToken()throws Exception			
	{
		String s=words.get(count);
		count++;
		token=s;
		if(count==words.size())
		{	
			System.out.println("Parsed Successfully!!!");
			System.exit(0);
		}
	}
	//checks whether given string is a terminal or nonterminal
	boolean isNonTerminal(String s)
	{
		System.out.println(s);
		if(terminals.get(s)==null)
		{
			return false;
		}
		char c=terminals.get(s).charAt(0);
		int a=(int)c;
		if(a>=65 && a<=90)
		{
			return true;
		}
		return false;
	}
	
	//Variable name handling
	public void Vari(boolean mutable) throws Exception
	{
		System.out.println("Vari...");
		System.out.println(token);
		System.out.println(" ");
		

		for(String x :var)
			{
				if(x.equals(token))
				{
					System.out.println("Variable already defined");
					System.exit(0);
				}
			}
		
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
				Exp();
				getToken();
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
				return;
			}
			else if(token.equals(":"))//For cases like "let mut x: i32 = 5;"
			{
				getToken();
				//currently: token == Datatype
				Datatype(var_name);

				//currently: token == Datatype
				Init();
				if(token.equals(";")) //For case "let mut n;"
				{
					return;
				}
				else
				{
					System.out.println("Variable declaration ';' error");
					System.exit(0);
				}
			}
	}
	
	public void S()throws Exception
	{
		
		if(E())
		{
			if(token.equals("{"))
			{
				c++;
			}
			if(token.equals("if"))
			{
				S();
			}
			
			else if(token.equals("else"))
			{
				R();
			}
			
			else if(token.equals("}"))
			{
				c--;
			}
			if(c<0)
			{
				System.out.println("ERROR!!");
				System.exit(0);
			}
			getToken();
			S();
		}
		else
		{
			getToken();
			R();
		}
	}
	
	public boolean E() throws Exception
	{
		getToken();
		return true;
	}
	
	public void R() throws Exception
	{
		getToken();
		if(token.equals("else"))
		{
			S();
		}
		else
		{
			return;
		}
	}
	
	public void parsing() throws Exception
	{
		System.out.println("parsing...");
		System.out.println(token);
		System.out.println(" ");
		
		while(count<words.size())
		{

			if( token.equals("let") )
			{
				/*
				Variable declaration
        		Var_dec :=      "let" MUTA Var ":" DataType Init ";"
       	 		MUTA     :=      mut | @
        		Init    :=      "=" Exp
        		*/
				Var_dec();
			}
			getToken();
			/*if(token.equals("if"))
			{
				/*
				 * S':R
				 * Grammar:
				 * S-> iEtSS' | a
				 * S'-> eS | epsilon
				 * E-> b
				 
				S();
				if(c!=0)
				{
					System.out.println("Error");
				}
			}*/
		}
		
	}
	
	public static void main(String args[]) throws Exception
	{
			vtester ob1=new vtester();
			
	}
}
