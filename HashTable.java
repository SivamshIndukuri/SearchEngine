package structures;
lass HashTable {

	private KeyValue[] table;
	private double loadFactor;
	private int numNodes;

	/**
	 * By default the size of the HashTable is 5
	 * 		with a load factor of 0.8 (80%)
	 * The load factor should be checked AFTER a new item is added.
	 * When the load of the HashTable exceeds the load factor, 
	 * 		the size of the HashTable should use the 6n-1 formula to 
	 * 		expand to another prime number and the
	 * 		load factor should increase by 50%.
	 */
	public HashTable(){
		table = new KeyValue[5];
		loadFactor = 0.8;
		numNodes = 0;
	}

	/**
	 * Given the index and the KeyValue, the KeyValue will be inserted
	 * 		into this HashTable at the correct index.  If the KeyValue
	 * 		already exists in the HashTable, the values of the KeyValue 
	 * 		passed by parameter will be appended to the values list of 
	 * 		the KeyValue object already stored in this HashTable.  If 
	 * 		the insert results in the HashTable exceeding the loadFactor, 
	 * 		the HashTable will expand using the 6n-1 formula, the loadFactor
	 * 		will increase by 50%, and all the data in the HashTable will be 
	 * 		rehashed accordingly. 
	 * 
	 * @param index is the HashTable index as calculated by the Indexer
	 * @param data a KeyValue object
	 * 
	 * POSTCONDITION: The load factor <= loadFactor, numNodes reflects the 
	 * 		number of KeyValue nodes that are in the HashTable
	 * 
	 * 3 points
	 */
	public void insertKeyValue(int index, KeyValue data){
		if(table[index] != null) { //if spot filled
			KeyValue duplicate = lookUpKey(index, data.getKey());
			if(duplicate != null) { //if duplicate
				for(String value : data.getValues()) {
					// if(!duplicate.getValues().contains(value)) duplicate.addValue(value);
					duplicate.addValue(value);
				}
			} else { //if no duplicate
				data.next = table[index];
				table[index] = data;
				numNodes++;
			}
		} else { //if spot not filled
			table[index] = data;
			numNodes++;
		}
		if((double)(numNodes/table.length) > loadFactor) rehash();
	}

	/**
	 * Given the index and a key return the KeyValue object from this
	 * 		HashTable. If there is no KeyValue object for the key passed
	 * 		by parameter, return null.
	 * @param index the index for key as determined by the Indexer
	 * @param key a String keyword that is being searched for
	 * @return the KeyValue object that holds the key passed by parameter,
	 * 		or null if this key is not stored as part of a KeyValue object
	 * 		in this HashTable.
	 * 
	 * 2 points
	 */
	public KeyValue lookUpKey(int index, String key){
		if(table[index] == null) return null;
		else {
			KeyValue pointer = table[index];
			while(pointer != null) {
				if(pointer.getKey().equals(key)) return pointer;
				pointer = pointer.next;
			}
			return null;
		}
	}
	
	/**
	 * A private helper method to resize the table and rehash all the values
	 * 		in the table.  When called, the HashTable will expand using 
	 *      the 6n-1 formula, the loadFactor will increase by 50% and all the data 
	 *      in the HashTable will be rehashed.
	 *      
	 * 5 points
	 */
	private void rehash() {
		int n = table.length;
		while(!isPrime((6*n)-1)) n++;
		KeyValue[] tempTable = table;
		table = new KeyValue[(6*n)-1];

		numNodes = 0;
		loadFactor *= 1.5;

		for(int i = 0; i < tempTable.length; i++) {
			KeyValue pointer  = tempTable[i];
			while(pointer != null) {
				insertKeyValue(Math.abs(pointer.hashCode()%table.length), copyNode(pointer));
				pointer = pointer.next;
			}
		}
		
	}
		
	private boolean isPrime(int num) {
		for(int i = 2; i < num/2; i++) {
			if (num % i == 0) return false;
		}
		return true;
	}

	private KeyValue copyNode(KeyValue node) {
		KeyValue newNode = new KeyValue(node.getKey());
		for(String value : node.getValues()) {
			newNode.addValue(value);
		}
		return newNode;
	}

	/**
	 * @return the current length of the HashTable to be used
	 * 		by the Indexer.
	 */
	public int size() {
		return table.length;
	}

	/**
	 * @return a String representation of this HashTable
	 */
	public String toString() {
		String tableString = new String();
		for(int index = 0; index < table.length; index++) {
			if(table[index] != null) {
				tableString += String.format("[%03d] -> ", index);
				KeyValue ptr = table[index];
				for(; ptr.next != null; ptr = ptr.next) {
					tableString += ptr.toString() + ", ";
				}
				tableString += ptr.toString() + "\n";
			}
		}
		tableString += String.format("\nLoad Factor: %.2f%c NumNodes: %d\n", loadFactor, '%', numNodes);
		return tableString;
	}
}