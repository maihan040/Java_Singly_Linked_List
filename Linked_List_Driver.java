/**
* 
* Filename: Linked_List_Driver.java
* 
* Description: Driver class file to test the "Linked_List" class file
*
* Output: None 
*
* @version 1.0
*
* @author Maihan 
*
*/


/****************************************************************************************************************/
/* 						CLASS DEFINITION						*/
/****************************************************************************************************************/
public class Linked_List_Driver {

	/****************************************************************************************/
	/* 				Test method definitions 				*/
	/****************************************************************************************/

	/**
	 * Method to test building a list where each node is being selected by the user 
	 * 
	 * @return None
	 */
	Linked_List test_build_pre_defined_list() {
		/* Local variales */
		Linked_List my_list = new Linked_List();

		/* Build the list */
		
		my_list.append_node(1);
		my_list.append_node(2);
		my_list.append_node(3);
		my_list.append_node(4);
		my_list.append_node(4);
		my_list.append_node(3);
		my_list.append_node(2);
		my_list.append_node(1);

		System.out.println("Pre defined list is: ");
		my_list.print_list();

		/* return the list */
		return my_list; 
	}

	/**
	 * Method to test building a list where each node is being selected by the user 
	 * 
	 * @return None
	 * 
	 * @param Node object: indicates the node to be appended
	 */
	void test_append_node_to_list() {
		/* Local variales */
		Linked_List my_list = new Linked_List();

		/* Build the list */
		Node three = new Node(3);
		Node nine = new Node(9);
		Node seven = new Node(7);
		Node one =  new Node(1);
		Node seven_dup = new Node(7);
		Node two = new Node(2);
		Node three_dup = new Node(3);
		Node five = new Node(5);

		my_list.append_node(three);
		my_list.append_node(nine);
		my_list.append_node(seven);
		my_list.append_node(one);
		my_list.append_node(seven_dup);
		my_list.append_node(two);
		my_list.append_node(three_dup);
		my_list.append_node(five);

		System.out.println("Initial list is: ");
		my_list.print_list();
	}

	/**
	 * Method to test building a random list 
	 * 
	 * @return Linked_List object of the randombly built list 
	 * 
	 * @param count int value indicating how many nodes the list will have at most 
	 * @param range int value indicating the possible range of value a given node can have
	 */
	Linked_List build_random_list(int count, int range) {
		
		/* Local variables */
		Linked_List rand_list = new Linked_List();

		rand_list.build_random_list(count, range);

		return rand_list;
	}

	/**
	 * Method to test getting the kth to last node 
	 *  
	 * @param list Linked_List object of the list that's to be processed 
	 * @param k int value indicating the node position (from the end) that's to 
	 * to be looked for  
	 */
	void test_kth_to_last_node(Linked_List list, int k) {

		/* Local variables */
		String ordinal; 

		/* Utilize a switch statement to get the proper ordinal */
		if (k < 10 || k > 20) {
			switch(k % 10) {
				case 1: ordinal = "st"; break;
				case 2: ordinal = "nd"; break;
				case 3: ordinal = "rd"; break;
				default: ordinal = "th"; break;
			}
		} else {
			/* All values from 10 - 20 will have the ordinal "th" */
			ordinal = "th";
		}

		/* Try compute the kth to last node and display it if all goes right*/
		try {
			Node kth_to_last = list.get_kth_to_last(k);
			System.out.println("The list is: ");
			list.print_list();
			System.out.print("The " + k + ordinal + " to last node is: " );
			System.out.println(kth_to_last.get_data());
		}
		catch(NullPointerException npe) {
			System.out.println(npe.getMessage());
		}
	}
	
	
	/**
	 * Method to test the deep copy functionality of the linked list class
	 * 
	 * @return None
	 * 
	 * @param Linked_List object: refers to the list to be copied
	 */
	void test_deep_copy_list(Linked_List list) {

		/* Local variables */
		Linked_List copied_list = list.get_deep_copy_of_list();
		Node orig = list.get_head();
		Node copy = copied_list.get_head();

		/**
		 * Compare each sequential elements of boths lists to one another
		 * ensuring they are not equal
		 */
		System.out.println();
		System.out.println("Printing the object IDs for each node inside the ORIGINAL and COPIED list");
		System.out.println("-------------------------------------------------------------------------");
		while(orig != null && copy != null) {
			System.out.println("Orig: " + orig + " Copy: " + copy);
			orig = orig.get_next();
			copy = copy.get_next();
		}
	}
	

	/**
	 * Test method for the partition functionality of the linked list class 
	 * 
	 * @return None
	 * 
	 * @param Linked_List object: refers to the list to be partitioned
	 * @param int variable: indicates the partition value
	 */
	void test_partition_linked_list(Linked_List list, int k) {

		/* Print the list before partitioning it */
		System.out.println("List before partitioning");
		list.print_list();

		/* Call the partition method */
		list.partition_list(k);

		/* Print the list after partitioning it */
		System.out.println("List after partitioning");
		list.print_list();
	}

	/**
	 * Test method to remove all duplicates from a list 
	 * 
	 * @return None
	 * 
	 * @param Linked_List object: refers to the list to be modified
	 */
	void test_remove_duplicates(Linked_List list) {

		/* Print the list before removing any duplicates */
		System.out.println("List before removing any duplicates");
		list.print_list();

		/* Remove duplicates from the list */
		list.remove_duplicates();

		/* Print the list after having removed the duplicates */
		System.out.println("List after removing any duplicates");
		list.print_list();
	}

	/**
	 * Test method to delete a node based on the value specified by the 
	 * user 
	 * 
	 * @return None
	 * 
	 * @param Linked_List object: refers to the list to be modified
	 * @param int variable: indicates the value of the node to be deleted
	 */
	void test_delete_node(Linked_List list, int d) {

		/* Print the list before removing any duplicates */
		System.out.println("List before removing node");
		list.print_list();

		/* delete node  */
		list.delete_node(d);

		/* Print the list after having removed the duplicates */
		System.out.println("List after removing node");
		list.print_list();
	}

	/**
	 * Test method to reverse a linked list object
	 * 
	 * @return None
	 * 
	 * @param None
	 */
	void test_reverse_linked_list(Linked_List list) {

		/* Print the list before refersal */
		System.out.println("List before reversal ");
		list.print_list();

		/* Reverse the list */
		list.reverse_linked_list();

		/* Printthe list after refersal */
		System.out.println("List after reversal ");
		list.print_list();
	}

	/**
	 * Test method rotate the linked list 
	 * 
	 * @return None
	 * 
	 * @param Linked_List object : representing the list to be worked on 
	 * @param int variable: indicates the number of rotations to be done 
	 */
	void test_rotate_list(Linked_List list, int rotate_count, String direction) {

		/* Print the list before rotation */
		System.out.println("List before rotation ");
		list.print_list();

		/* Rotate the list */
		list.rotate(rotate_count, direction);

		/* Printthe list after rotation */
		System.out.println("List after rotation ");
		list.print_list();
	}

	/**
	 * Test method extend the current list 
	 * 
	 * @return None
	 * 
	 * @param Linked_List object : representing the extension list 
	 */
	void test_extend_list(Linked_List list, Linked_List ext) {

		/* Print the list before extension */
		System.out.println("List before extension ");
		System.out.println("Size of list : " + list.get_size());
		list.print_list();

		/* Extend the list */
		list.extend_list(ext);

		/* Printthe list after refersal */
		System.out.println("List after rotation ");
		System.out.println("Size of list : " + list.get_size());
		list.print_list();
	}

	/**
	 * Test method to check whether the list contais a loop 
	 * 
	 * @return None
	 * 
	 * @param Linked_List object : representing the extension list 
	 */
	void test_does_list_have_loop(Linked_List list) {

		/* Local variables */
		boolean has_loop = list.does_list_have_loop();
		StringBuilder output = new StringBuilder("The list ");

		/* Display the result */
		if (has_loop == true) {
			output.append("does " );
		}
		else {
			output.append("does not ");
		}

		/* Add the rest of the string and display it*/
		output.append("contain a loop!\n");
		System.out.println(output);

	}

	/**
	 * Test method to find the intersecting node of two linked lists
	 * 
	 * @return None
	 * 
	 * @param Linked_List object : representing the first list 
	 * @param Linked_List object : representing the second list 
	 */
	void test_find_intersecting_node(Linked_List list1, Linked_List list2) {

		/* Local variables */
		Node intersecting_node; 

		/* Display both lists */
		System.out.println("List 1: " );
		list1.print_list();
		System.out.println();

		System.out.println("List 2: " );
		list2.print_list();

		/* Find the intersecting node if applicable */
		intersecting_node = list1.get_intersecting_Node(list2);

		/* Print result */
		if (intersecting_node == null) {
			System.out.println("No intersecting node was present");
		}
		else {
			System.out.print("The intersecting node is: " );
			System.out.println(intersecting_node.get_data());
		}
	}

	/**
	 * Test method to determine whether the list is a palindrome or not
	 * 
	 * @return None
	 * 
	 * @param Linked_List object : representing the first list 
	 */
	void test_is_list_palindrome(Linked_List list) {

		/* Local variables */
		boolean is_palindrome = list.is_list_palindrome();

		/* Print the list */
		System.out.println("List is: " );
		list.print_list();

		/* Print result */
		if( is_palindrome ) {
			System.out.println("List is palindrome");
		}
		else {
			System.out.println("List is not a palindrome");
		}
	}

	/**
	 * Test method to two linked lists 
	 * 
	 * @return None
	 * 
	 * @param Linked_List object : representing the first list 
	 * @param Linked_List object : representing the second list 
	 */
	void test_sum_linked_lists(Linked_List l1, Linked_List l2) {

		/* Local variables */
		Linked_List sum = new Linked_List(); 

		/* Display both lists */
		System.out.println("First list: " );
		l1.print_list();
		System.out.println();

		System.out.println("Second list: " );
		l2.print_list();
		System.out.println();

		/* Get the sum of both */
		sum = sum.get_sum_left_to_right(l1, l2);

		/* Diplay the sum */
		if (sum != null) {
			System.out.println("Sum is: " );
			sum.print_list();
			System.out.println();
		}
		else {
			return; 
		}
	}

	/**
	 * Test method to two linked lists right to left 
	 * 
	 * @return None
	 * 
	 * @param Linked_List object : representing the first list 
	 * @param Linked_List object : representing the second list 
	 */
	void test_sum_linked_lists_right_to_left(Linked_List l1, Linked_List l2) {

		/* Local variables */
		Linked_List sum = new Linked_List(); 

		/* Display both lists */
		System.out.println("First list: " );
		l1.print_list();
		System.out.println();

		System.out.println("Second list: " );
		l2.print_list();
		System.out.println();

		/* Get the sum of both */
		sum = sum.get_sum_right_to_left(l1, l2);

		/* Diplay the sum */
		if (sum != null) {
			System.out.println("Sum is: " );
			sum.print_list();
			System.out.println();
		}
		else {
			return; 
		}

	}


	/****************************************************************************************/
	/* 					MAIN						*/
	/****************************************************************************************/

	public static void main(String[] args) {

		/* Create an instance of the driver */
		Linked_List_Driver driver = new Linked_List_Driver();
		Linked_List rand_list = driver.build_random_list(10, 20);
		Linked_List intersect1 = driver.build_random_list(15, 10);
		Linked_List intersect2 = driver.build_random_list(10, 10);

		/************************************************************************/
		/*			Tests that succeeded				*/
		/************************************************************************/

		/* Test building a pre defined list */
		//Linked_List my_list = driver.test_build_pre_defined_list();

		/* Test deleting node from list */
		//driver.test_delete_node(my_list, 7);

		/* Random linked list operations */
		//System.out.println("Random list is : " );
		//rand_list.print_list();

		/* Test getting the kth from last node method */
		//driver.test_kth_to_last_node(rand_list, 21);

		/* Test to deep copy the list  */
		//driver.test_deep_copy_list(rand_list);

		/* Test to partition the list */
		//driver.test_partition_linked_list(rand_list, 8);

		/* Test building list via appending nodes */
		//driver.test_append_node_to_list();

		/* Test to remove duplicates from a list */
		//driver.test_remove_duplicates(rand_list);

		/* Test to reverse the linked list */
		//driver.test_reverse_linked_list(rand_list);

		/* Test rotating the list to the right */
		//driver.test_rotate_list_right(rand_list, 15);

		/* Test rotating the list to the right */
		//driver.test_rotate_list(rand_list, 1, "RIGHT");

		/* Test the extending the list by another */
		//driver.test_extend_list(rand_list, my_list);

		/* Test the method of finding if a list has a loop */
		//driver.test_does_list_have_loop(my_list);

		/* Test method to find the intersecting node of two linked lists */
		
		/* extend both intersecting lists */
		//intersect1.extend_list(my_list);
		//intersect2.extend_list(my_list);
		//driver.test_find_intersecting_node(intersect1, intersect2);

		/* Test method to determine whether the list is a palindrome */
		//driver.test_is_list_palindrome(my_list);


		/* Test method to add two linked lists left to right */
		//driver.test_sum_linked_lists(intersect1, intersect2);

		/* Test method to add two linked lists left to right */
		//driver.test_sum_linked_lists_right_to_left(intersect1, intersect2);


		/************************************************************************/
		/*			Tests in progress				*/
		/************************************************************************/
	}
}