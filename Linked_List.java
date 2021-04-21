/**

* This class contains definitions and methods for a user defined singly linked list data structure 

* Output: Linked List object which contains two "Node" objects representing the head and the tail

* of the list. 

* @version 1.0

* @author Maihan 

*/

/* Import statements */
import java.util.*;
import java.lang.Math; // for the absolute value method 

/****************************************************************************************************************/
/* 						CLASS DEFINITION						*/
/****************************************************************************************************************/
public class Linked_List {

	/* instance members */
	private Node head = null;
	private Node tail = null;
	private int size = 0;

	/********************************************************************************/
	/* 				Constructor definitions 			*/
	/********************************************************************************/

	/**
	 * No arg constructor 
	 */
	Linked_List() {}

	/**
	 * Standard constructor which builds the list starting with a single node 
	 *
	 * @param d int value: indicates the "data" attribute of the node 
	 */
	Linked_List(int d) {

		head = new Node(d);
		tail = head;
		
		/* increment the node counter */
		++size; 
	}

	/********************************************************************************/
	/* 				Getter definitions 				*/
	/********************************************************************************/
	
	/**
	 * Method to retrieve the current count of nodes
	 * 
	 * @return int value: indicates the current count of the nodes inside the list 
	 *
	 */
	int get_size() {
		
		return size; 

	}

	/**
	 * Method to retun the reference object of the list's head
	 * 
	 * @return Node object: refers to the head
	 */
	Node get_head() {

		return this.head; 
	}

	/**
	 * Method to return the reference object of the list's tail
	 * 
	 * @return Node object: refers to the tail 
	 * 
	 */
	Node get_tail() {

		return this.tail; 
	}

	/********************************************************************************/
	/* 				Method definitions 				*/
	/********************************************************************************/

	/**
	 * Test method to check whether the current list is empty
	 * 
	 * @return boolean value 
	 *
	 * @param None
	 */
	boolean is_list_empty() {
		return (head == null);
	}

	/**
	 * Test method to check whether the current list only has single 
	 * digit values
	 * 
	 * @return boolean value 
	 *
	 * @param None
	 */
	boolean does_list_contain_only_singles() {
		
		/* Local variables */
		Node r = this.get_head(); 

		while(r != null) {

			/* Check if digit is a single digit */
			if (r.get_data() > 10) {
				return false; 
			}

			/* Advance runner */
			r = r.get_next(); 
		}

		/* At this point all the nodes contain only single digits */
		return true; 
	}


	/**
	 * Test method to check whether the current list contains a loop (i.e
	 * is there a node that refers to a nother prior one)
	 * 
	 * @return boolean value 
	 *
	 * @param None
	 */
	boolean does_list_have_loop() {
		
		/* Local variables */
		boolean has_loop = false; 
		Node runner = this.head;
		Node fast_runner = this.head; 

		/**
		 * Iterate through the list having the "fast_runner" jump two 
		 * nodes at a time. If it ever hits the "runner" then the list 
		 * contais a loop. Otherwise it will hit "null" indicating that
		 * there is no loop
		 */
		while(fast_runner != null) {

			/* Advance the nodes accordingly */
			fast_runner = fast_runner.get_next().get_next();
			runner = runner.get_next();

			/* check for equality */
			if (fast_runner == runner) {
				return true;
			}
		}

		/* Once this point has been reached then there is no loop in the list */
		return has_loop;
	}


	/**
	 * Test method to check whether the current list contains is a palindrome
	 * 
	 * @return boolean value 
	 *
	 * @param None
	 */
	boolean is_list_palindrome() {

		/* Validation check */
		if(this.is_list_empty()) {

			/* An empty list is a palindrome */
			return true; 
		}

		/* Local variables */
		Node runner = this.get_head();
		Node fast_runner = runner; 	// used to find the halfway point 
		Stack<Integer> node_stack = new Stack<Integer>(); // used to keep track of values that were already seen

		/* Add the first half of the list to the stack */
		while(fast_runner != null && fast_runner.get_next() != null) {
			
			/* Add current element to the stack */
			node_stack.push((Integer)runner.get_data());

			/* Advance references */
			runner = runner.get_next();
			fast_runner = fast_runner.get_next().get_next();

		}

		/**
		 * Depending on the value of "fast_runner" at this point, the next
		 * next step is to either inspect the most recent value in the stack 
		 * or simply ignore it (as for an even numbered list the current value would 
		 * be identical to it's prior one which won't be check as only the subsequent
		 * nodes will)
		 */
		if(fast_runner == null) {
			
			/* Discard the most recent value from the stack */
			node_stack.pop(); 
		}

		/* Advance runner */
		runner = runner.get_next();
		
		/**
		 * Iterate through the remainder of the list and compare each 
		 * value from the stack with the current node in the second half
		 * of the list 
		 */
		while(runner != null) {

			/* check */
			if ((int)node_stack.peek() == runner.get_data()) {
				node_stack.pop();
				runner = runner.get_next();
			}
			else {
				/**
				 * Found a mismatch and therefore the 
				 * list is not a palindrome
				 */
				return false; 
			}
		}

		/* At this point all the nodes matched and it can be concluded that the list is a palindrome */
		return true;
	}


	/**
	 * Method to check whether two lists have an intersecting node
	 * Essentially this will be dealing with the case where two lists will merge
	 * at one particular node, and this method will find that exact node should there
	 * be one 
	 * 
	 * @return Node object : indicating the node where the two lists intersect if applicable  
	 * 
	 * NOTE: output can be "null" if there is no intersecting node. Caller should check on the 
	 * result prior to moving on
	 *
	 * @param Linked_List object : second linked list which will be used to compare the current 
	 * one against it 
	 */
	Node get_intersecting_Node(Linked_List list_two) {

		/* Validation checks */
		if (this.is_list_empty() && list_two.is_list_empty()) {

			/* Display message and return */
			System.out.println("Both lists are empty");
			return null; 
		}

		if (this.is_list_empty() || list_two.is_list_empty()) {

			/* Display message and return */
			System.out.println("One of the lists is empty");
			return null; 
		}

		/* Local variables */
		Node r1 = this.get_head();	// r = runner (for the appropriate list)
		Node r2 = list_two.get_head();
		int size_diff = Math.abs(this.get_size() - list_two.get_size());


		/**
		 * Find which of the two lists is the longer one, because to find the 
		 * intersecting node, both of them would have to hit that node 
		 * at the same point in time during their iteration.
		 * 
		 * Once the longer one has been identified, its reference can be moved 
		 * up prior to the iteration 
		 */
		if (this.get_size() < list_two.get_size()) {
			
			/* Advance list two */
			for(int i = 0; i < size_diff; ++i) {
				r2 = r2.get_next();
			}
		}
		else {
			/* Advance list one */
			for(int i = 0; i < size_diff; ++i) {
				r1 = r1.get_next();
			}
		}

		/**
		 * Move both runners together until they either find the intersecting node
		 * or one of them hits "null"
		 */
		while( r1 != null && r2 != null) {

			/* check both nodes */
			if (r1 == r2) {
				return r1; 
			}

			else {
				r1 = r1.get_next();
				r2 = r2.get_next();
			}
		}

		/* Nothing was found return null */
		return null;
	}
	
	
	/**
	 * Given an int, represending a new value create a new 
	 * node and append that to the list
	 * 
	 * THIS METHOD MODIFIES THE LINKED_LIST OBJECT
	 * 
	 * @return None
	 *
	 * @param d int variable: indicate the "data" attribute of the new node 
	 */
	void append_node(int d) {

		/* Local variables */
		Node new_node = new Node(d);
		
		/**
		 * Check whether the list is empty, as that will only  
		 * require to have the head of the list contain the value 
		 * that's requested to be added
		 */
		if(this.is_list_empty()){

			head = new_node;
			tail = head; 
		}
		else {
			/** Else have the tail node of the list 
			 * refer to the new node and make this the 
			 * new tail of the list 
			 */ 
			tail.set_next(new_node);
			tail = new_node;
		}

		/* Increment the node count */
		++size;
	}


	/**
	 * Given an int, represending a new value create a new 
	 * node and append that to the list
	 * 
	 * THIS METHOD IS AN OVERLOAD OF THE ABOVE
	 * 
	 * @return None
	 *
	 * @param Node object: indicate the "data" attribute of the new node 
	 */
	void append_node(Node new_node) {

		/**
		 * Check whether the list is empty, as that will only  
		 * require to have the head of the list contain the value 
		 * that's requested to be added
		 */
		if(this.is_list_empty()){

			head = new_node;
			tail = head; 
		}
		else {
			/** Else have the tail node of the list 
			 * refer to the new node and make this the 
			 * new tail of the list
			 */ 
			tail.set_next(new_node);
			tail = new_node;
		}

		/* Increment the node count */
		++size;
	}

	
	/**
	 * Method to build a list with random nodes
	 * 
	 * THIS METHOD MODIFIES THE LINKED_LIST OBJECT
	 * 
	 * @return None
	 *
	 * @param max_num_nodes int variable: to indicate the most numbers of nodes the list can have
	 * @param max_val int variable: to indicate the highest numerical value that a node can be
	 */
	void build_random_list(int max_num_nodes, int max_val) {

		/* List should be empty for this */
		if (!this.is_list_empty()){
			 System.out.println("List already is populated");
			 return;
		 }

		/* Local variables */
		Random rand_generator = new Random(); 

		/**
		 * Loop to create "max_num_nodes" random integers which will be
		 * used to build the linked list
		 */
		for (int random_int, i = 0; i < max_num_nodes; ++i) {
			random_int = rand_generator.nextInt(max_val);
			this.append_node(random_int);
		}
	}

	
	/**
	 * Method to return the kth to last node from the 
	 * current linked list
	 * 
	 * @throws NullPointerException when "k" exceeds the size of the list 
	 * 
	 * @return Node object: contains the data element of the kth to last element
	 *
	 * @param k int variable: indicates the kth to last value that's to be searched for 
	 */
	Node get_kth_to_last(int k) throws NullPointerException {

		/****************************************************************/
		/* 		POSSIBLE EXCEPTION BEING THROWN			*/
		/*								*/
		/* Check for "k" being less than the size of the list 		*/
		/****************************************************************/
		if (k > this.get_size()) {
			throw new NullPointerException("List doesn't have " + k + " elements");
		}

		/* Validation check */
		if (this.is_list_empty()|| k == 0) {
			return null;
		}

		/* Local variable */
		Node runner = head; 
		Node current = head; 

		/**
		 * Advance "runner" k places.
		 * 
		 * Idea is to have "runner" and "current" advance
		 * together one step at a time and once "runner" 
		 * has hit "null" then "current" will be the kth 
		 * to last node
		 */
		for(int i = 0; i < k; ++i) {
			runner = runner.get_next(); 
		}

		/* Advance references */
		while(runner != null) {
			runner = runner.get_next(); 
			current = current.get_next(); 
		}

		/* return the node reference by "current" */
		return current; 
	}


	/**
	 * Method to return a deep copy of the cureent linked list
	 * none of the indiviual nodes (from the returned list) will reference 
	 * nodes from the original list
	 * 
	 * NOTE: ommited the use of the copy constructor as that will only 
	 * give a shallow copy
	 * 
	 *@return Linked_List object: refers to the new list
	 *
	 * @param None
	 */
	Linked_List get_deep_copy_of_list() {

		/* Validate the passed parameter */
		if (this.is_list_empty()) {
			return null; 
		}

		/* Local variables */
		Node runner = this.get_head(); //used to traverse the orginal list 
		Linked_List copy = new Linked_List();

		/**
		 * Iterate through the original list and for each individual node
		 * append a "new" identical one to the "copy" list 
		 */
		while(runner != null) {
			copy.append_node(runner.get_data());
			runner = runner.get_next();
		}

		/* Return the newly copied list */
		return copy; 
	}


	/**
	* Method to reverse the linked list
	*
	* @return None
	*
	* @param None
	*/
	void reverse_linked_list() {

		/* Validation check */
		if (this.is_list_empty()) {
			return; 
		}

		/* Local variables */
		Node p = null;		// previous
		Node c = this.head; 	// current 
		Node n = null; 		// next

		/* Set the new tail */
		this.tail = this.head; 
	
		/**
		 * Iterate through the list always working at most with three nodes at a time
		 * 
		 * For each node, change references as follows: 
		 * 	c's next attribute refers to "p", and n's 
		 * 	next attribue refers to "c"
		 * 	c will always refer to "runner"
		 */
		while (c != null) {

			/* Retrive the next node in the list */
			n = c.get_next();

			/* Reverse current node's next attribute to point to the previous node */
			c.set_next(p);

			/* Advance the previous node to refer to the current one */
			p = c; 

			/* Advance the current node to the next in the list */
			c = n;
		}

		/**
		 * Set the new head 
		 * as "c" will be refering to null at this point, 
		 * "p" will have the last element which will now 
		 * be the new head 
		 */
		this.head = p;
	}


	/**
	* Method to delete any middle node (except the head/tail)
	* without having to traverse the list in order to find it
	*
	* THIS METHOD MODIFIES THE LINKED_LIST OBJECT
	*
	* @return None
	*
	* @param C Node object: Node object to be deleted
	*/
	void delete_middle_node(Node c) {

		/* Validation check */
		if (this.is_list_empty()) {
			return; 
		}

		/* Check to ensure node to be deleted is not head/tail */
		if (c == head || c == tail) {
			return; 
		}

		/**
		 * Since we only know the location of the very node that needs to 
		 * be deleted we can use the fact that this reference variable 
		 * refers to the actual object
		 *
		 * All that needs to be done is to copy the value of the next node
		 * to the current data attribute and refer to the node after the 
		 * subsequent one
		 */
		c.set_data(c.get_next().get_data());
		c.set_next(c.get_next().get_next());

		/* Decrement the node count */
		--size; 
	}

	
	/**
	 * Given an int, representing the satelite data of the node to be removed from the list,
	 * find that node inside the list and remove it
	 * 
	 * Removal will simply be done by having the previous node (of the one containing "d")
	 * refer to the node following it (essentially skipping node "d")
	 *
	 * NOTE: this method will delete the first occurance where there is a match
	 * 
	 * THIS METHOD MODIFIES THE LINKED_LIST OBJECT
	 * 
	 * @return None
	 *
	 * @param d int variable: indicates the value of the node that's to be deleted
	 */
	void delete_node(int d) {

		/* Validation check */
		if(this.is_list_empty()) {
			return;
		}

		/**
		 * Check whether the head of the list needs to be deleted
		 */
		if(head.get_data() == d) {
			head = head.get_next();
			return;
		}

		/* Local variables */
		Node runner = head; 
		 
		/**
		 * Find the node just right before the one that's 
		 * supposed to be deleted 
		 */
		while(runner.get_next() != null && runner.get_next().get_data() != d) {
			runner = runner.get_next(); 
		}

		/**
		 * Check whether the node to be deleted is even in the list  
		 */
		if(runner == tail) {
		       System.out.println("The item is not int the list");	
			return;
		}
		else {
			/**
			 * Runner's node "next" attribute should be pointing two 
			 * nodes further down the list, consequently
			 * deleting the very same neighboor
			 * runner.next = runner.next.next; 
			 */

			runner.set_next(runner.get_next().get_next());
		}

		/* Decrement the node count */
		--size; 
	}

	
	/**
	 * Iterate through the list and keep track of each value that has been encountered
	 * keep these values inside a "set" data structure as this way it can determined 
	 * whether a duplicate has occurded or not
	 * 
	 * THIS METHOD MODIFIES THE LINKED_LIST OBJECT
	 * 
	 * @return None
	 *
	 * @param None
	 */
	void remove_duplicates() {

		/* Validation check */
		if(this.is_list_empty()) {
			return;
		}

		/* Local variables */
		Node runner = this.head;
		int next_data_val; 
		HashSet <Integer> values_seen = new HashSet<Integer>();

		/**
		 * Put the first element inside the set, as that one can't be a duplicate.
		 * For each node in the list, check whether it's "next" element has already 
		 * been seen if so, then simply refer to next's next element which effectively 
		 * deletes the duplicate node.
		 */

		/* Add first element */
		values_seen.add(head.get_data());

		/* Traverse through the list */
		while(runner.get_next() != null) {

			/* Retrieve the value of the next node */
			next_data_val = runner.get_next().get_data();

			/**
			 * Check for duplicates, point to it's "next" element if one is found
			 * 
			 * In this case, runner should not advance to the next element in the 
			 * list since there is a possibility that this "next" element would be 
			 * the same value that has just been removed
			 * 
			 * The logic is to look forward and not at the node it's currently at
			 */

			/* Has the data of the next node already been encounterd? */
			if (values_seen.contains(next_data_val)) {
				this.delete_middle_node(runner.get_next());

				/* Decrement node count */
				--size; 

				/* move on */
				continue; 
			}
			/**
			 * If no duplicate  has been encountered, then this value 
			 * should be added to the set and "runner" can advance to the 
			 * next item in the list
			 */
			else {
				values_seen.add(next_data_val);
				runner = runner.get_next();
			}
		}
	}


	/**
	 * Method to extend the current list 
	 *
	 * This method will extend the current list by 
	 * appending another that's passed to this method
	 * 
	 * THIS METHOD MODIFIES THE LINKED_LIST OBJECT
	 *
	 * @return None
	 *
	 * @param Linked_List object: indicates the list that's to be added
	 * to the current one
	 */
	void extend_list(Linked_List ext) {

		/* Validation check */
		if (this.is_list_empty()) {

			/* Since the current list is empty, use the extension as the list */
			this.head = ext.get_head();
			this.tail = ext.get_tail();
			this.size = ext.get_size();
			return;
		}

		/* Extend the list by using the current tail and referring to the head of the */
		this.tail.set_next(ext.get_head());

		/* modify the size accordingly */
		this.size += ext.get_size();
	}


	/**
	 * Method to partition the current linked list as determined
	 * by the passed parameter
	 *
	 * This method will create two linked list objects (one for items
	 * smaller than the partition cutoff value and the other for items
	 * that are equal or greater than the cutoff) followed by joining 
	 * those two lists
	 * 
	 * THIS METHOD MODIFIES THE LINKED_LIST OBJECT
	 *
	 * @return None
	 *
	 * @param x int variable: indicates the partition point
	 */
	void partition_list(int x) {

		/* Validation check */
		if (this.is_list_empty()) {
			return;
		}

		/* Local variable */
		Linked_List less_than = new Linked_List(); 
		Linked_List greater_than = new Linked_List(); //this will be for values greater than or equal to "x"
		Node runner = head; //used to traverse the list 

		/**
		 * Traverse through the list and inspect each 
		 * node value to determine in which of the two 
		 * above linked lists it should belong 
		 */
		while(runner != null) {
			if(runner.get_data() < x) {
				less_than.append_node(runner.get_data());
			}
			else {
				greater_than.append_node(runner.get_data());
			}
			runner = runner.get_next(); 
		}

		/**
		 * Join the two linked lists and assign the consolidated 
		 * list to the "head" of the original lis
		 */
		less_than.tail.set_next(greater_than.head); //consolidating the two lists
		this.head = less_than.head; //reassignment of the current list 
	}


	/**
	 * Method to rotate the current linked list to the left/right (as specified via the 
	 * parameter) by a specified amount
	 *
	 * Same as the above method only this rotating the list in a different direction
	 * 
	 * THIS METHOD MODIFIES THE LINKED_LIST OBJECT
	 *
	 * @return None
	 *
	 * @param int variable: indicating how many rotations to be performed 
	 * @param String variable: indicates in which direction the list should be 
	 * rotated to 
	 */
	void rotate(int k, String direction) {

		/* Validation checks */
		if (this.is_list_empty()) {
			return;
		}

		/* Local variable */
		k = k % this.get_size();  // used to account for a possible rollover

		/* Check if we even need to rotate the list */
		if (k == 0) {

			/**
			 * If this condition is true, than rotating the list 
			 * by the specified amount would result in the exact 
			 * same placement of nodes. Therefore, save the work 
			 * and just return
			 */
			return; 
		}

		/* Local variables */
		Node runner = this.get_head();

		/**
		 * Advance the runner to determine where to break up the list 
		 * use the "rotation" variable to prevent getting a null pointer
		 * exception in case of a roll over
		 * 
		 * As the intention is to move the first half "k" spots to the right, 
		 * we need to know the last "k" nodes in order to remove them. 
		 * Deducting "k" from the size of the list will get that value 
		 * 
		 * NOTE: Potential for a "off by one error"
		 * 
		 * 	For a LEFT rotation:
		 * 		Make sure to stop one spot before where the break needs to happen
		 * 
		 * 	For a RIGHT rotation: 
		 * 		Make sure to subtract an additional 1 as otherwise the "runner" will 
		 * 		be pointing one past the node where we the break needs to happen
		 * 
		 */

		/* convert the "direction" variable to lowercase for the following swtich statement */
		direction = direction.toLowerCase();

		switch(direction) {

			/* allow some tolerance in what users type via fallthrough */
			/* possible entries to rotate the list left */
			case "l":
			case "lt":
			case "left" : for(int i = 0; i < k - 1; ++i) { 
				runner = runner.get_next(); 
			}
			break;
			/* possible entries to rotate the list right */
			case "r":
			case "rt":
			case "right" : for(int i = 0; i < (this.get_size() - k - 1); ++i) { 
				runner = runner.get_next(); 
			}
			break;
			default : System.out.println("Incorrect direction was passed. Can't rotate the list");
		}

		/* Change references based on "head", "tail" and "runner" */
		this.tail.set_next(this.head);
		this.head = runner.get_next();
		this.tail = runner; 
		this.tail.set_next(null); // otherewise there will be an infinite loop
	}


	/**
	 * Helper method for the "get_sum_left_to_right" method
	 *
	 * Since the sum method can end up with one list that's longer than the other, 
	 * the remainder needs to be processed as the carry can potentially carry 
	 * all the way to the end (requiring an additional node). 
	 * 
	 * To reduce code duplication (as it's unknown until runtime which list is the longer
	 * one) this method will handle the remainder of the addition
	 * 
	 * NOTE: this method will only be called if the "carry" is a "1" by the time 
	 * the shorter list has reached "null"
	 * 
	 * @return Node object : representing the extension to be added to the sum (r = runner)
	 *
	 * @param Linked_List object: List representing the partial sum 
	 */
	Linked_List sum_remainder(Node r, int carry) {

		/* Local variables */
		int temp = 0; 
		Linked_List remainder = new Linked_List();

		/**
		 * Iterate until the end of the list has been reached
		 */
		while(r != null) {
			temp = r.get_data() + carry; 
			remainder.append_node(temp % 10);
			carry = temp / 10;

			/* Advance the references */
			r = r.get_next();
		}

		/**
		 * There is a possiblity that a new node needs to be 
		 * generated as there has been an overflow on the prior
		 * addition
		 */
		if (carry == 1) {
			remainder.append_node(carry);
		}

		/* Return the remainder */
		return remainder;
	}


	/**
	 * Method to sum two linked lists from left to right
	 *
	 * Same as the above method only this rotating the list in a different direction
	 * 
	 * NOTE: this method is intended to work only on lists where each node contains 
	 * single digit values
	 *
	 * @return LInked List object : representing the sum of the two individual ones 
	 *
	 * @param Linked_List object: List representing one of the operands  
	 * @param Linked_List object: Second list representing one of the linked list operands
	 */
	Linked_List get_sum_left_to_right(Linked_List l1, Linked_List l2) {

		/* Validation checks */

		/* Check whether any of the lists are empty */
		if (l1.is_list_empty() && l2.is_list_empty()) {
			return new Linked_List(0); 
		}

		if (l1.is_list_empty()) {
			return l2; 
		}

		if (l2.is_list_empty()) {
			return l1; 
		}

		/* Ensure that both lists only contain single digits */
		if (!l1.does_list_contain_only_singles() || !l2.does_list_contain_only_singles()) {
			System.out.println("Can only add single digit values");
			return null; 
		}

		/* Local variables */
		Linked_List sum = new Linked_List();
		Node r1 = l1.get_head(); // r = runner
		Node r2 = l2.get_head();
		int carry = 0;
		int temp_sum = 0; 

		/**
		 * Iterate through both lists until one of them has hit 
		 * "null" and stop the iteration. 
		 * 
		 * Since individual node values can overflow, the carry will be
		 * used to propagate the overflow to the next addition of nodes 
		 */
		while(r1 != null && r2 != null ) {
			temp_sum = (r1.get_data() + r2.get_data() + carry);
			sum.append_node(temp_sum % 10);
			carry = (int)Math.floor(temp_sum / 10);

			/* Advance the references */
			r1 = r1.get_next();
			r2 = r2.get_next();
		}

		/**
		 * Determine which list still has nodes left in it (if any) and 
		 * call the helper method to process those and extend the 
		 * result via the "extend_list" method 
		 */

		/* No remainder in any list */
		if(r1 == null && r2 == null) {
			return sum;
		}

		/* r2 still has nodes left */
		if(r1 == null) {
			sum.extend_list(sum_remainder(r2, carry));
		}
		/* r1 still has nodes left */
		else {
			sum.extend_list(sum_remainder(r1, carry));
		}

		/* Return the sum */
		return sum; 
	}


	/**
	 * Method to sum two linked lists from right to left
	 *
	 * In order to reduce code duplication, this method will simply reverse 
	 * both lists and call the "get_sum_left_to_right" method
	 *
	 * @return Linked List object : representing the sum of the two individual ones 
	 *
	 * @param Linked_List object: List representing one of the operands  
	 * @param Linked_List object: Second list representing one of the linked list operands
	 */
	Linked_List get_sum_right_to_left(Linked_List l1, Linked_List l2) {

		/* Reverse both lists and call the main method to do the addition */
		l1.reverse_linked_list();
		l2.reverse_linked_list();
		return this.get_sum_left_to_right(l1, l2);
	}
	
	
	/**
	 * Simple method to print the individual nodes of the 
	 * linked list one by one
	 *
	 * @return None
	 *
	 * @param None
	 */
	void print_list() {

		/**
		 *  Validation check
		 *  check whether the head is empty, as in that case
		 *  no further processing is needed 
		 */
		if (this.is_list_empty()) {
			System.out.println("Current list is empty....");
			return;
		}

		/* Local variables */
		Node runner = head; 

		/**
		 * Iterate through each of the individual nodes and 
		 * print out their data 
		 */
		while(runner != null) {
			System.out.print(runner.get_data() + " -> ");
			runner = runner.get_next(); 
		}

		/* Print "null" to show the list is done followed by a new line */
		System.out.println(" null \n");
	}
}
