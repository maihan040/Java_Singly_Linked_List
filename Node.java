/**

* The class contains definitions and constructors for the "Node" class
*
* This object will be the core element for the Linked List data structure (defined in a subsequent file)
*
* Output: Single "Node" instance which contains the "satelite data" in this case an int element to be stored, 
*
* as well as the reference to the next element in the list. 
*
* @version 1.0
*
* @author Maihan 
*
*/

/****************************************************************************************************************/
/* 						CLASS DEFINITION						*/
/****************************************************************************************************************/
public class Node {

	/**
	 * Class definition
	 * Simple class containing the actual satellite data,
	 * (the value that the node is supposed to store) and 
	 * additionaly a reference variable to the next node 
	 * element in the list
	 */

	/* instance members */
	private Node next = null;
	private int data;

	/**
	 * CONSTRUCTORS
	 *
	 * Following constructors are defined: 
	 * 	No arg constructor (will default the node value to "0")
	 *
	 * 	Single arg constructor which will initialize the node 
	 * 	to the value that the user supplied
	 */

	public Node() {
		data = 0;
	}

	public Node(int d) {
		data = d;
	}

	/**
	 * GETTER and SETTER methods
	 * 
	 * Following getter/setter methods are defined below 
	 * 	get_data: returns the current satelite data
	 * 	get_next: returns the next node object
	 * 	set_data: sets the data attribute to the specified value 
	 * 	set_next: sets the next node element to be referred to
	 */

	/************************************************************************/
	/* 				GETTERS 				*/
	/************************************************************************/

	/**
	 * method to retun the value of "data"
	 */
	public int get_data() {

		return this.data;
	}

	/** 
	 * method to return the object that's being 
	 * referred to by the reference variable "next"
	 */
	public Node get_next() {

		return this.next; 
	}

	/************************************************************************/
	/* 				SETTERS 				*/
	/************************************************************************/

	/** 
	 * method to set the "data" instance member
	 */
	public void set_data(int d) {
		
		this.data = d; 
	}

	/** 
	 * method to refer the next node element that 
	 * should be referred to 
	 */
	public void set_next(Node n) {

		this.next = n; 
	}	
}
