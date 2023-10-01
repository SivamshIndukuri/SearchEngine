package structures;

import java.util.ArrayList;
/**
 * SimpleSearch class to search an inverted index
 * 
 * @name Sivamsh Indukuri
 * @date 5/25/2023
 * @period 3
 */
public class SimpleSearch {
	
	/**
	 * Searches the InvertedIndex indexer for any and all keywords found in 
	 * 		the String query and returns an ArrayList of all subjects 
	 * 		related to the keywords in the String query. 
	 * If the query results in a failed search return null.
	 * @param query a String that includes one or more key words.
	 * @param indexer an Inverted Indexer object to be searched
	 * @return an ArrayList of values or "subjects" that relate to any and all 
	 * 		of the "key" words in the String query. Or null if the key words in
	 * 		query result in a failed search. ie. key NOT in the InvertedIndex
	 * PRECONDTION: query may include upper and lower case letter, period, 
	 * 		comma, semicolon, colon, apostrophe, question mark, exclamation, 
	 * 		and spaces. But no other characters.
	 * POSTCONDITION: the ArrayList returned by the method query does NOT
	 * 		contain any duplicate values and may be in any order.
	 * 
	 * 5 points
	 */
    public static ArrayList<String> query(String query, Indexer indexer) 
    		throws NoMoreTokensException{
    	//creates the array that returns the values
    	ArrayList<String> subject = new ArrayList<String>();
    	
    	//Breaks the query into word to search 
    	Tokenizer words = new Tokenizer(".,::'!? ",query);
    	
    	while(words.hasToken())
    	{
    		String key = words.nextToken();
    		
    		//gets the KeyValue at the index
    		KeyValue i = indexer.getKeyValue(key);
    		
    		
    		if(i!=null)
    		{
    			//all the values for the KeyValue
    			ArrayList<String> values = i.getValues();
    			
    			
    			for(int k=0; k<values.size();k++)
    			{
    				//adds all the new values for the key word
    				if(!subject.contains(values.get(k)))
    				{
    					subject.add(values.get(k));
    				}
    			}
    		}
    	
    	}
    	
    	//if array list is not empty 
    	if (subject.size() !=0)
    	{return subject;}
    	
    	//if subject array is empty
        return null;
    }

}
