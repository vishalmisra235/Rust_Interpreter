import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.*;
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
			//Inputs file
			FileReader fr=new FileReader(code);
			BufferedReader br=new BufferedReader(fr);
			
			//Temporary string to read line by line
			String s;
			while((s=br.readLine())!=null)
			{	s=s+"$";
				//Removes whitespaces
				s=s.trim();
				
				//Accepts singleton braces
				if(s.length()==1)
				{
					words.add(s);
					continue;
				}
				
				
				for(int i=0;i<s.length()-1;i++)
				{
					//Skips over whitespaces
					if(s.charAt(i)==' ')
					{
						continue;
					}
					
					// between A->Z or a->z : Start of a lexime
					else if(((int)s.charAt(i)>=65 && (int)s.charAt(i)<=90)||(int)s.charAt(i)>=97 && (int)s.charAt(i)<=122)
					{
						//Temporary String to store a single lexime
						String s1="";
						
						//between A->Z or a->z or 0-9 : Middle of lexime 
						while(((int)s.charAt(i+1)>=65 && (int)s.charAt(i+1)<=90)||((int)s.charAt(i+1)>=97 && (int)s.charAt(i+1)<=122)||((int)s.charAt(i+1)>=48 && (int)s.charAt(i+1)<=57))
						{
							//Error handling for array index out of bound excpetion
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
						
						//Storing datatypes, identifiers and keywords in lexime list
						s1=s1+s.charAt(i);
						words.add(s1);
					}
					
					//Checking digit in temp line string and storing it as number
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
					
					//Checking for string inputs and storing as " <STRING> " in lexime list
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
					
					//Handling any other charachters/operators
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
			//Closing buffer reader and file reader object
			//Garbage collection
			words.add("@");
			br.close();
			fr.close();
			
			//Printing tokens
			for(String z:words)
			{
				System.out.println(z);
			}
					
	}
	
	
}
