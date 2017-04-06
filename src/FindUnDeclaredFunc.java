import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;

public class FindUnDeclaredFunc extends KingsmenCodingChallenge {
	 LineNumberReader file2;
	public void checkFunctionDeclarations(ArrayList<String> alist, File file) throws FileNotFoundException, ArrayIndexOutOfBoundsException{
		 file2 = new LineNumberReader(new FileReader(file));
		if(alist!=null && alist.isEmpty() == false){
			
        	for(String a:alist){
        	
        		int count = 0;
        		String[] tempFunctionStore = null;
        		if(a.contains(",")){
        			tempFunctionStore = a.split("\\,");
            		for(String d:tempFunctionStore){
	            		count++;
            		}
            			
	            		}
        		else {
        			tempFunctionStore = a.split("\\(");
            		for(String d:tempFunctionStore){
	            		count++;
            		}
        		}
//    			IMPLEMENT INBUILT FUNCS LATER!!!!!!!!

  	    	 
        		try {
					while((line = file2.readLine()) != null)
					{
						if(!line.startsWith("//")){
						if(line.contains("//")){
							line = line.substring(0, line.indexOf("//"));
						}

				          String t[] = a.split("[\\(\\s]");	
						if(((line.contains(t[0]) && (line.contains("function"))) && ((line.contains("{")) || file2.readLine().contains("{") ))){
							
						          StringBuffer stringbuffer = new StringBuffer("");  
						          
						          stringbuffer.append(line);
						          String newString = stringbuffer.toString().substring(9);
						          String newString2[] = newString.split("[\\(\\s]");
						          
						          if(t[0].matches(newString2[0])){
						        	  String convertToString = stringbuffer.toString();

										if(convertToString.contains(",")){
											String storeFunctionValues[] = convertToString.split("\\,");
											
											
											int length = storeFunctionValues.length;
											if(length == count)
												flag=true;
										}
										else {
											String storeFunctionValues[] = convertToString.split("\\(");
											
											int length = storeFunctionValues.length;
											if(length == count)
												flag=true;
										}
						          }
						          //Could use one more split using commas if necessary
						}
						
					}
							
					}
            	}catch (IOException e) {
						e.printStackTrace();
				}
        		if(flag==false){
        			System.out.println("Function "+a+"is never declared.");
					
        		}
        		flag=false;
        	}            	
        }		
	}
}

