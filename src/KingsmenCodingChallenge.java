import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KingsmenCodingChallenge {
	 String line = null;
	 int lnum =0;
	 boolean flag = false;
	 static Map<String, Integer> map = new HashMap<String, Integer>();

	public static void main(String args[]) throws FileNotFoundException{
		
		KingsmenCodingChallenge obj = new KingsmenCodingChallenge();
		obj.parseData(args);
		
	}
	
	public String getClassNames(){
		
		String classname = this.getClass().getName();
		return classname;
	}

	private void parseData(String[] args) throws FileNotFoundException {
		 
		 String[] array = null;
		 ArrayList<String> alist = new ArrayList<>();
		 ArrayList<String> varlist = new ArrayList<>();
		 
	      if (0 < args.length) {
	    	  File file = new File(args[0]);
	    	  LineNumberReader file1 = new LineNumberReader(new FileReader(file));

	            try {
					while((line = file1.readLine()) != null)
					{
						
						if(!line.startsWith("//")){
							if(line.contains("//")){
								line = line.substring(0, line.indexOf("//"));
								
							}
							
							while((line.contains("if(") && !line.contains("{")) || (line.contains("if (") && !line.contains("{"))){

								String tempLine = line;
								int temp = file1.getLineNumber();
								line = file1.readLine();
								if(!line.trim().matches("\\{"))
						    	  System.out.println("Single line if loop present on Line Number: "+temp);
						    		  
							}
				
							if(line.contains("else") && (!line.contains("{"))){ 
								int temp = file1.getLineNumber();
								if(!file1.readLine().contains("{"))
						    	  System.out.println("Single line else loop present on Line Number: "+temp);
							}
							if(line.contains("();") && !line.contains(getClassNames()) && !line.contains("write") && !line.contains(";\"")){
								
						          StringBuffer stringbuffer = new StringBuffer("");  

								if(line.contains(".")){
									stringbuffer.append(line);
									array = stringbuffer.toString().split("[\\.\\;]");
									alist.add(array[array.length-1]);
									
								}
								else{
									stringbuffer.append(line);
									String tempString = stringbuffer.toString().replaceAll("new"," ").replaceAll("\n\\;"," ");
									array = tempString.replaceAll(" +", "").split("\\s");
									alist.add(array[0]);
								}
							}
							if(line.contains("var") && line.contains(";") && !line.contains(",")){
								String temp = line.replaceAll("\\.", " ").replaceAll("[\\(\\;\\)\\!]"," ").replaceAll("[\\=\\++\\--\\?\\:]", " ");
								 String varArray[] = temp.replaceAll(" +", " ").trim().split("[\\s]");
								
								 int lnum = file1.getLineNumber();
								FindUnDeclaredVariables findvar = new FindUnDeclaredVariables();
					            map = findvar.getUndeclaredVariables(line, file, lnum);
					            func(map);
					          							
							}
							else if(!line.contains("var")){

								String temp = line.replaceAll("\\.", " ").replaceAll("[\\(\\;\\)\\!]"," ").replaceAll("[\\=\\++\\--\\?\\:]", " ").replaceAll("\\.", " ");

								String varArray[] = temp.replaceAll(" +", " ").trim().split("[\\s]");
								
								 for(int i=0; i<varArray.length; i++){
									 if(map.containsKey(varArray[i])){
										 map.remove(varArray[i]);
									 }
								 }
								
							}							
							
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
	            for(String key: map.keySet()){
					System.out.println("Variable "+key+" is never used. Line Number: "+map.get(key));
				}
				FindUnDeclaredFunc func = new FindUnDeclaredFunc();
	            func.checkFunctionDeclarations(alist, file);
				FindUnBalancedBrackets brackets = new FindUnBalancedBrackets();
	            brackets.checkUnBalancedBrackets(file);

	      }
	      else
	      {
	    	  System.out.println("No Filename/Argument Provided");
	      }
	}

	private void func(Map<String, Integer> map2) {
		// TODO Auto-generated method stub
		
	}

	
}
