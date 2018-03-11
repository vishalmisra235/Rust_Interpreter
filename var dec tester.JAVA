package com.first;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.first.lexer;
public class parser extends lexer  {
	int count=0;int c=0;
	String token;
	HashMap<String,String> terminals=new HashMap<String,String>();//for storing non terminals as characters
	
	ArrayList<String> var=new ArrayList<String>();//for storing all variables defined
	ArrayList<String> immutable=new ArrayList<String>();//for storing immutable variables
	ArrayList<String> mutables=new ArrayList<String>();//for storing mutable variables
		public parser() throws Exception
	{
		super("C:/Users/Vishal/workspace/Interpreter/code.txt");
		
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
	//checks mutability
	boolean M() throws Exception
	{
		if(token.equals("mut"))
		{
			getToken();
			return true;
		}
		getToken();
		return false;
	}
	//checks data types
	public void D(String v) throws Exception
	{
		if(token.equals("i64"))
		{
			getToken();
			if(token.equals("="))
			{
			getToken();
			
			}
			else
			{
				System.out.println("Parse error1");
				System.exit(0);
			}
		}
		else
		{
			System.out.println("Error in defining datatype");
			System.exit(0);
		}
	}
	//function for variable declaration
	public void V() throws Exception
	{
		getToken();String v="";
		
		boolean mutable=M();
		
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
			D(v);
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
		
		while(count<words.size())
		{
			getToken();
			if( token.equals("let") )
			{
				V();
			}
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
			parser ob1=new parser();
			
	}
}
