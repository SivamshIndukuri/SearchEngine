package structures;
import java.io.*;
import java.util.Scanner;

/**
 * Indexer class to create a HashTable of KeyValues
 * 
 * @name Sivamsh Indukuri
 * @date 5/25/2023
 * @period 3
 */
public class Indexer {
	private HashTable table;
	
	public Indexer(String filename) throws IOException, NoMoreTokensException {
		table = new HashTable();
		buildInvertedIndex(filename);
	}
	
	/**
	 * Fills a HashTable of KeyValue objects to act as the storage unit for
	 * 		this InvertedIndex.  The HashTable and KeyValues are built using
	 * 		Strings from the File filename passed by parameter.
	 * @param filename the name of a txt file to be parsed into KeyValue objects
	 * 		and indexed into a HashTable
	 * 
	 * 6 points
	 */
	private void buildInvertedIndex(String filename) 
			throws IOException, NoMoreTokensException{
		/* Complete this method */
		File myObject = new File(filename);
		Scanner myReader = new Scanner(myObject);
		
		//does it until no more lines 
	    while (myReader.hasNextLine()) {
	    	//gets the whole line
	         String data = myReader.nextLine();
	         
	         Tokenizer tokenizer = new Tokenizer(": ", data);
	         
	         //The first word is always the value 
	         String values = tokenizer.nextToken();
	         
	         while(tokenizer.hasToken())
	         {
	        	 //each word is a key 
	        	 String key = tokenizer.nextToken();
	        	 
	        	 //creates the the KeyValue object that holds the key
	        	 KeyValue keys = new KeyValue(key);
	        	
	        	 keys.addValue(values);
	        	 //adds to the table;
	        	 table.insertKeyValue(index(key), keys);
	         }
	         
	         
	     }
	    myReader.close();
	    
	}
	
	/**
	 * Returns the KeyValue object in the HashTable of this Indexer
	 * 		with the key of key. If no such object exists, return null.
	 * @param key the key of a KeyValue object
	 * @return the KeyValue object for which key exist, null if no such
	 * 		object exists in the HashTable of this Indexer.
	 * 
	 * 2 points
	 */
	public KeyValue getKeyValue(String key) {
		/* Complete this method */
	
		return table.lookUpKey(index(key), key); 
	}
    
	/**
	 * Determines the index at which String key should be found/located 
	 * 		in HashTable for this Indexer as calculated with the hashCode 
	 * 		method of the KeyValue class and the current size of the 
	 * 		HashTable attribute of this Indexer object.
	 * 
	 * @param key - A String of the key for a KeyValue object
	 * @return a valid index in the HashTable attribute of this Indexer
	 * 		object as calculated with the KeyValue hashCode and the size of
	 * 		the HashTable.
	 * 
	 * 2 points
	 */
	public int index(String key){
		/* Complete this method */
		//ask!!!!!
	    return Math.abs(key.hashCode()%table.size()); //quiets the compiler
	}

    /**
     * @return a String representation of this InvertedIndex...which is being
     * 		stored using a HashTable
     */
    public String toString() {
    	return table.toString();
    }
}
