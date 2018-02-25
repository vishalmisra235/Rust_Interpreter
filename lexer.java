package com.first;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.first.tokentype;
public class lexer  {
	String[] keywords={"let","as","break","const","continue","crate","else","enum","extern","false","fn","for","if","impl","loop",
			"match","mod","move","mut","pub","ref","return","Self","self","static","struct","super","main","while","true","Vec","new",
			"macro","use","text","io","println","read","in"};
	String[] operators={"+","-","*","/","%","==","!=","<",">","<=",">=","||",".","->","=","!",":","..","#","&&"};
	String[] seperators={";","(",")","{","}","[","]",","};
	String[] datatype={"i64","i32","usize","u64","u32"};
	
	ArrayList<String> words=new ArrayList<String>();
	List<String> l1=new ArrayList<String>();
	
	public lexer(String code)throws Exception
	{
		
			FileReader fr=new FileReader(code);
			BufferedReader br=new BufferedReader(fr);
			String s;
			while((s=br.readLine())!=null)
			{
				s=s.trim();
				if(s.length()==1)
				{
					words.add(s);
					continue;
				}
				for(int i=0;i<s.length()-1;i++)
				{
					if(s.charAt(i)==' ')
					{
						continue;
					}
					else if(((int)s.charAt(i)>=65 && (int)s.charAt(i)<=90)||(int)s.charAt(i)>=97 && (int)s.charAt(i)<=122)
					{
						String s1="";
						
						while(((int)s.charAt(i+1)>=65 && (int)s.charAt(i+1)<=90)||((int)s.charAt(i+1)>=97 && (int)s.charAt(i+1)<=122)||((int)s.charAt(i+1)>=48 && (int)s.charAt(i+1)<=57))
						{
							if(i<s.length()-2)
							{
							s1=s1+s.charAt(i);
							i=i+1;
							}
							
							else
							{
								break;
							}
						}
						s1=s1+s.charAt(i);
						words.add(s1);
					}
					else if(((int)s.charAt(i)>=48 && (int)s.charAt(i)<=57))
					{
						String s1="";
						while(((int)s.charAt(i+1)>=48 && (int)s.charAt(i+1)<=57)&&(i<s.length()-2))
						{
							s1=s1+s.charAt(i);
							i++;
						}
						s1=s1+s.charAt(i);
						words.add(s1);
					}
					else if(s.charAt(i)=='\"')
					{
						String s1="\"";i++;
						while(s.charAt(i)!='\"' && i<s.length()-1)
						{
							s1=s1+s.charAt(i);
							i++;
						}
						s1=s1+'\"';
						words.add(s1);
					}
					
					else
					{
						String s1="";
						s1=s1+s.charAt(i);
						words.add(s1);
						if(i==s.length()-2)
						{
						i++;
						String s2="";
						s2=s2+s.charAt(i);
						words.add(s2);
						}
					}
			}
			}
			br.close();
			fr.close();
			for(String z:words)
			{
				System.out.println(z);
			}
					
	}
	
	
}
