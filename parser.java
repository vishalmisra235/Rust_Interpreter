package com.first;
import java.util.ArrayList;
import java.util.HashMap;

import com.first.lexer;
public class parser extends lexer  {
	int count=0;int errors=0;String s,s1;
	String token;
	ArrayList<String> mutable=new ArrayList<String>();
	ArrayList<String> immutable=new ArrayList<String>();
	ArrayList<String> var=new ArrayList<String>();
	HashMap<String, String> value=new HashMap<String,String>();
	public parser() throws Exception
	{
		super("C:/Users/Vishal/workspace/Interpreter/code.txt");
		try
		{
			lexer ob=new lexer("C:/Users/Vishal/workspace/Interpreter/code.txt");
		}
		catch(Exception e)
		{
			System.out.println("Error!!!");
		}
		getToken();
		parsing();
	}
	public void attribute(String s,String s1)
	{
		this.s=s;
		this.s1=s;
	}
	public void getToken()throws Exception
	{
		String s=words.get(count);
		count++;
		token=s;
	}
	
	public void varDeclaration()throws Exception
	{
		String v="";
		if(token.equals("mut"))
		{
			getToken();
			for(String x:keywords)
			{
				if(x==token)
				{
					System.out.println("ERROR!! : INVALID DECLARATION ");
					errors++;
					System.exit(0);
				}
			}
			for(int i=0;i<var.size();i++)
			{
				if(token==var.get(i))
				{
					System.out.println("ERROR!! : INVALID DECLARATION ");
				}
			}
			v=token;
			var.add(token);
			mutable.add(token);
			getToken();
			
		}
		else
		{
			for(String x:keywords)
			{
				if(x==token && x!="mut")
				{
					System.out.println("ERROR!! : INVALID DECLARATION ");
					errors++;
					System.exit(0);
					
				}
			}
			for(int i=0;i<var.size();i++)
			{
				if(token==var.get(i))
				{
					System.out.println("ERROR!! : INVALID DECLARATION ");
				}
			}
			v=token;
			immutable.add(token);
			var.add(token);
			
			getToken();
		}
		if(token!=":")
		{
			System.out.println(" : Expected");
			errors++;
			System.exit(0);
		}
		getToken();
		getToken();
		getToken();
		value.put(v,token);
		
	}
	
	
	
	public void parsing() throws Exception
	{
		while(count<words.size())
		{
			if(token.equals("let"))
			{
				getToken();
				
				varDeclaration();
			}
		}
	}
	
	public static void main(String args[])
	{
		try
		{
			parser ob1=new parser();
			
		}
		catch(Exception e)
		{
			System.out.println("Error!");
		}
	}
}
