package com.first;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.first.lexer;
public class parser extends lexer  {
	int count=0;
	String token;
	HashMap<String,String> terminals=new HashMap<String,String>();
	ArrayList<String> var=new ArrayList<String>();
		public parser() throws Exception
	{
		super("C:/Users/Vishal/workspace/Interpreter/code.txt");
		
		getToken();
		parsing();
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
	
	
	
	public void var_dec()
	{
		
	}
	

	
	public void parsing() throws Exception
	{
		getToken();
		while(count<words.size())
		{
			
			if( token.equals("let") )
			{
				/*
				 
				 */
				
				var_dec();
			}
			else if(token.contains("let") && !token.equals("let"))
			{
				System.out.println("Error in syntax");
			}
		}
	}
	
	public static void main(String args[]) throws Exception
	{
			parser ob1=new parser();
			
	}
}
