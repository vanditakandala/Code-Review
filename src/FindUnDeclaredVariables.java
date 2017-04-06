import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Map;

public class FindUnDeclaredVariables extends KingsmenCodingChallenge {
	public Map<String, Integer> getUndeclaredVariables(String line, File file, int lnum) throws FileNotFoundException {

		 String temp = line.replaceAll("\\.", " ").replaceAll("[\\(\\;\\)\\!]"," ").replaceAll("[\\=\\++\\--\\?\\:]", " ").replaceAll("\\.", " ");
		 String varArray[] = temp.replaceAll(" +", " ").trim().split("[\\s]");
		 
			for(int i=0; i<varArray.length; i++){

				if(varArray[0].equals("var")){
					map.put(varArray[1], lnum);
				}

				if(i>1 && i < varArray.length && map.size()>0){

					    if( map.containsKey(varArray[i])){
							map.remove(varArray[i]);						
						
					}
					}
			}
			return map;	
	}
	
}
