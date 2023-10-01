package structures;
import java.util.ArrayList;

/**
 * A KeyValue object is a node that has three attributes(next, key, and values).
 * 		This structure is the foundation of the linked list structure used for
 * 		chaining in your HashTable. With a single pointer to the next KeyValue
 * 		node in the list. The key represents the data that will be used to index
 * 		your KeyValue object in the HashTable. And the values is an ArrayList of
 * 		all the subjects in which the key can be found.
 *  
 * For example: If the file contains the following lines where each line is a
 * 		"subject" followed immediately by a ":" and then followed by a sequence
 * 		of words separated by spaces.
 *  dog: my big red        dog is really   really friendly
 *  cat: my cat thinks it is    the king of the house house     house
 *  bird: my cat ate my friendly  bird
 *  fish: my cat tried to eat my fish
 *  
 * The following (Key --> value) combinations would be included, 
 *    (my->[dog, cat, bird, fish])
 *    (big->[dog])
 *    (friendly->[dog, bird])	
 *    (cat->[cat, bird, fish])
 *  along with other keyValue combinations.
 *  
 *  Where the key is a element of the data and the values are 
 *  	the topics or subject for which the key is located.
 */
public class KeyValue {
	KeyValue next;
    private String key;
    private ArrayList<String> values;
    private final int PRIME = 13;

    /**
     * Constructor for the keyValue
     * Sets the key and instantiates the ArrayList of values.
     * 	values must be added individually with the addValue method.
     */
    public KeyValue(String key){
    	next = null;
        this.key = key;
        values = new ArrayList<>();
    }
    
    /**
     * Adds a value to the ArrayList of values for this keyValue object and
     * 	returns true, or returns false if the value is already in the 
     * 	ArrayList of values.
     * @param val a value to be add to the values of this keyValue
     * @return true if val is added to values, false otherwise
     */
    public boolean addValue(String val) {
    	for(String s : values)
    		if(s.equals(val)) return false;
    	return values.add(val);
    }

    /**
     * @return the key of this keyValue object
     */
    public String getKey() {
        return key;
    }

    /**
     * @return the values ArrayList of this keyValue object
     */
    public ArrayList<String> getValues() {
        return values;
    }
    
    /**
     * The hashCode method for KeyValue object.  This value
     * 		should be used by the Indexer to hash the KeyValue
     * 		object to the HashTable
     * PRECONDITION: key is a lower case String made of only char values
     * 		'a' through 'z'.
     * WARNING: hashCode may return a negative value.
     */
    public int hashCode() {
        int hashCode = 0;

        // char1  + (char2 * PRIME) + (char2 * PRIME^2)...
        for(int i = key.length() - 1; i >= 0 ;i--){
            char curLetter = key.charAt(i);
            // mapping of each character a --> 1, b --> 2 .... z -> 26
            int charValues = (int)curLetter - (int)'a' + 1;
            hashCode = hashCode * PRIME + charValues;
        }
        return hashCode;
    }
    
    /**
     * Two KeyValue objects are declared to be the same if they have they
     * 		key, regardless of the values in the ArrayList
     */
    public boolean equals(Object other) {
    	if(other instanceof KeyValue) {
    		return this.key.equals( ((KeyValue)other).key );
    	}
    	return false;
    }
    
    /**
     * @return a String representation of this keyValue object in the 
     * 	format "(key:[value1, value2, ..., valueN])"
     */
    public String toString() {
    	return String.format("(%s->%s)", key, values);
    }
}
