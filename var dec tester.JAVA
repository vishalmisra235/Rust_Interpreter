package com.first;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.first.lexer;
public class parser extends lexer  {
	int count=0;
	String token;
	HashMap<String,Character> terminals=new HashMap<String,Character>();//for storing non terminals as characters
	HashMap<String,Integer> value=new HashMap<String,Integer>();//for storing variables and values
	ArrayList<String> var=new ArrayList<String>();//for storing all variables defined
	ArrayList<String> immutable=new ArrayList<String>();//for storing immutable variables
	ArrayList<String> mutables=new ArrayList<String>();//for storing mutable variables
		public parser() throws Exception
	{
		super("C:/Users/Vishal/workspace/Interpreter/code.txt");
		
		getToken();
		parsing();
		terminals.put("let", 'L');
		terminals.put("mut", 'M');
		terminals.put("i64", 'D');
		terminals.put("usize", 'U');
		
	}
	
	//gets each token from list of words for parsing	
	public void getToken()throws Exception			
	{
		String s=words.get(count);
		count++;
		token=s;
		if(count==words.size())
		{
			System.out.println("File input finished");
			System.exit(0);
		}
	}
	//checks wether given string is a terminal or nonterminal
	boolean isNonTerminal(String s)
	{
		System.out.println(s);
		char c=terminals.get(s);
		int a=(int)c;
		if(a>=65 && a<=90)
		{
			return true;
		}
		return false;
	}
	//checks mutability
	boolean M()
	{
		if(token.equals("mut"))
		{
			return true;
		}
		return false;
	}
	//checks datatype
	public void D(String v) throws Exception
	{
		if(token.equals("i64"))
		{
			getToken();
			if(token.equals(":"))
			{
			getToken();
			value.put(v,Integer.parseInt(token) );
			}
			else
			{
				System.out.println("Parse error");
			}
		}
		else
		{
			System.out.println("Error in defining datatype");
			System.exit(0);
		}
	}
	//fn for variable declaration
	public void V() throws Exception
	{
		getToken();String v="";
		
		boolean mutable=M();
		
		getToken();
		
			for(String x:var)
			{
				if(x.equals(token))
				{
					System.out.println("Variable already defined");
					System.exit(0);
				}
			}
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
			 v=token;
			getToken();
			if(token.equals(";"))
			{
				return;
			}
		
		
		getToken();
		if(isNonTerminal(token))
		{
			D(v);
		}
		else
		{
			System.out.println("Parse error");
			System.exit(0);
		}
		
	}
	

	
	public void parsing() throws Exception
	{
		
		while(count<words.size())
		{
			
			if( token.equals("let") )
			{
				V();
			}
			/*else if( !token.equals("let"))
			{
				System.out.println("Error in syntax");
				System.exit(0);
			}*/
		}
	}
	
	public static void main(String args[]) throws Exception
	{
			parser ob1=new parser();
			
	}
}
