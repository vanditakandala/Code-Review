import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Stack;

public class FindUnBalancedBrackets {
	LineNumberReader file3;
	String line;

	public void checkUnBalancedBrackets(File file) throws FileNotFoundException {
		Stack <String> stackOpen = new Stack <String> ();
		Stack <String> stackClose = new Stack <String> ();

		file3 = new LineNumberReader(new FileReader(file));
		try {
			while((line = file3.readLine()) != null)
			{
				

				for (int i = 0; i < line.length(); i++){
			        char current = line.charAt(i);
			        if(current == '{'){

			        	stackOpen.push(current+","+file3.getLineNumber());
			        }
			        if(current == '}'){

			        	stackClose.push(current+","+file3.getLineNumber());
			        }
			        

				}
			}

			
			String arrayOpen[] = stackOpen.firstElement().split(",");
			String arrayClose[] = stackClose.pop().split(",");
			stackOpen.remove(stackOpen.firstElement());
			
			while(stackOpen.size()!=0 && stackClose.size()!=0 && Integer.parseInt(arrayOpen[1]) <= Integer.parseInt(arrayClose[1])){
				
				arrayOpen = stackOpen.firstElement().split(",");
				arrayClose = stackClose.pop().split(",");
				stackOpen.remove(stackOpen.firstElement());
			
			}
			
			int k=0;
			int m=0;
			if(stackOpen.size() > 0 || stackClose.size()>0){

				if(stackOpen.size() > stackClose.size() ){

					System.out.println("Missing closing bracket");
					
				}
			else if (stackOpen.size() == stackClose.size()){

				while(stackOpen.size() == 0 || stackClose.size() == 0){
					if(k<stackOpen.size()){
						arrayOpen = stackOpen.get(k).split(",");
						System.out.println("Missing Closing bracket at: "+arrayOpen[1]);
					}
					if(m<stackClose.size()){
						 arrayClose = stackClose.get(m).split(",");
						System.out.println("Missing Opening bracket at: "+arrayClose[1]);

					}
					
					k++;
					m++;
				}	
		    }
			else{
				
				System.out.println("Missing 'opening bracket' or an 'extra closing' bracket. Can be determined with a total parsing for functions in the text file.");
			}
			
		}

		
		}catch (IOException e) {
			e.printStackTrace();
	}
		
	}

}
