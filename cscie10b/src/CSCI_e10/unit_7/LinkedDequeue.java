package CSCI_e10.unit_7;

/**
 * Modified by M Bret Blackford
 * ID: 20849347
 * CSCI e-10b  Spring 2016
 * 
 * The LinkedDequeue.class is a modification of the LinkedQueue class.  It makes
 * the LinkedQueue a double-ended queue.
 * ref chapter 16 in Reges textbook re linked-list data structures
 * /////////////////////////////////////////////////////////////////////////
 */

/**
 * This class represents a Dequeue (double-ended queue) Structure allows user
 * to add new elements to either end (head or tail), as well as to delete an
 * element from either end.
 *
 *
 * @author: Henry Leitner
 * @version: Last modified on April 11, 2013 Implements a Queue as a linked-list
 */

/**
 * EXTRA CREDIT - this exception gets thrown when a user attempts to remove or peek
 * at an element from an already empty queue.
 *
 */
class DequeueUnderFlowException extends Exception {}

public class LinkedDequeue
{
    private QueueNode rear;
    private QueueNode front;
    private int count;
    
    /**
     *  The QueueNode class is an inner class implemented to model a queue node;
     *  it can contain an Object type of data, and also holds the link to the
     *  next node in the queue.  If there are no other nodes, the link will be null.
     */
     class QueueNode        // an inner class
     {
	   private Object item;		//data
	   private QueueNode link;	//link to next node (or head/tail)
     }

     /**
      *  This constructor creates an empty Dequeue.
      */
    public LinkedDequeue ()
    {
	   rear = front = null;
	   count = 0; //used to maintain count of nodes in the dequeue
    }

    /**
     *  This method will construct a new QueueNode and add it onto the HEAD
     *  of the queue. If it is the first node added into
     *  the queue, both front and rear will reference it, otherwise it is added
     *  using the rear variable.  The node counter is also updated.
     *
     *  @param  x    The Object to be added as part of a new QueueNode
     */
    public void headAdd (Object x)
    {
    	QueueNode temp = new QueueNode();
    	temp.item = x;
    	temp.link = null;
    	
    	if (front == null) front = rear = temp;
    	else
	   {
    	    temp.link = front;
    	    front = temp;
	   }
	   count++ ;
    }

    /**
     *  This method will return the data at the head of the queue. Allows
     *  the usaer to peek at the node data.
     *
     *  @return     The value of the head node
     */
    public Object headPeek() throws DequeueUnderFlowException
    {
        if (isEmpty()) {
        	throw new DequeueUnderFlowException();
        }
        return front.item;
    }

	/**
	 * This method will remove an item from the front of the queue (the HEAD).
	 *
	 * @return The Object which was just removed from the queue.
	 */
	public Object headRemove() throws DequeueUnderFlowException {
		if (isEmpty()) {
			// System.out.println("ERROR: cannot remove node from empty queue");
			throw new DequeueUnderFlowException();
		} else {
			Object tempItem = front.item;
			front = front.link;
			if (front == null) {
				rear = null;
			}
			count--; // decrement counter of nodes
			return tempItem;
		}
	}

    /**
     *  This method returns true if Dequeue contains no elements; else returns false
     *
     *  @return     true for an empty list; false if the queue contains QueueNodes.
     */
    public boolean isEmpty()
    {
        if (front == null) {
        	return true;
        }
        else {
        	return false;
        }
    }
   
    /**
     * ORIGINAL
     *  This method will return the number if elements currently in the dequeue.
     *
     *  @return     An int describing the current number of nodes in the queue
     */
    public int size()
    {
	   return count;
    }
    
	/**
	 * This method will construct a new QueueNode and add it onto the rear of
	 * the queue (the TAIL). 
	 *
	 * @param x
	 *            The Object to be added as part of a new QueueNode
	 */
	public void tailAdd(Object x) {
		QueueNode temp = new QueueNode();
		temp.item = x;
		temp.link = null;

		if (rear == null) {
			front = temp;
			rear = temp;
		}
		else {
			rear.link = temp;
			rear = temp;
		}
		count++; // increment counter of nodes
	}

	/**
	 * Peek at the TAIL (the last node) of the Dequeue. Returns the data value at 
	 * the tail without removing it
	 * @return
	 * @throws DequeueUnderFlowException
	 */
    public Object tailPeek () throws DequeueUnderFlowException
    {
        if (isEmpty()) throw new DequeueUnderFlowException();
        return rear.item;
    }

	/**
	 * This method will remove an item from the tail of the queue. 
	 * (Removes the last node)
	 *
	 * @return The Object which was just removed from the queue.
	 */
	public Object tailRemove() throws DequeueUnderFlowException {
		if ( isEmpty() || count < 1 ) {
			//System.out.println("ERROR: cannot remove node from empty queue");
			throw new DequeueUnderFlowException();
		}
		
		if (count == 1) { 	// if last element empty the queue
			QueueNode temp = new QueueNode();
			temp = front;
			front = null;

			count--; 		// decrement counter of nodes

			return temp;
		} else {
			QueueNode current = front;
			while (current.link.link != null) {
				current = current.link;
			}

			QueueNode temp = new QueueNode();
			temp = rear;

			rear = current;
			rear.link = null;

			count--;		// decrement counter of nodes
			return temp;
		}
	}

	/**
	 * Method traverses the queue and prints data content
	 * of each node
	 *
	 * @return a string 
	 */
	public String toString() {
		String outString = "";
		if (front == null) {
			outString = "[DEQUEUE is EMPTY]";
		} else {
			outString = (String) front.item;
			QueueNode currentNode = front.link;
			
			while (currentNode != null) {
				outString += "\n" + currentNode.item;
				currentNode = currentNode.link;
			}
		}//end of else
		return outString;
	}

    /**
     * ORIGINAL
     *  This method will test for an empty queue and return a boolean result.
     *  Made private as not called for in the Dequeue homework requirements
     *
     *  @return     true for an empty list; false if the queue contains QueueNodes.
     */
    private boolean empty() 
    {
	   return ( count == 0 );
    }




	/**
	 * Main below is to test LinkedDequeue class.  It is bulky and I would have 
	 * preferred putting this test code in a a separate class/file but kept code
	 * here for ease in grading/review.
	 * @param args
	 */
    public static void main(String[] args) {
		try {
			LinkedDequeue dequeue = new LinkedDequeue();

			System.out.println();
			System.out.println(dequeue.toString());
			System.out.println("is the queue now empty? [" + dequeue.isEmpty() + "]");
			System.out.println("Lets add the some words to the queue:\n");

			dequeue.headAdd("  And in the end");
			dequeue.tailAdd("  The Love you take");
			dequeue.tailAdd("  Is Equal to the Love you make");
			dequeue.headAdd("The Beatles:");

			System.out.println();
			System.out.println("is the queue now empty? [" + dequeue.isEmpty() + "]");
			System.out.println("contents of the queue: ");
			System.out.println(" ----------");
			System.out.println(dequeue.toString());
			System.out.println(" ---------- \n");

			System.out.println("The queue head : [" + dequeue.headPeek() + "]");
			System.out.println("The queue tail : [" + dequeue.tailPeek() + "]");
			System.out.println("Number of elements in queue: [" + dequeue.size() + "]");
			System.out.println("is the queue now empty? [" + dequeue.isEmpty() + "]");

			System.out.println("Let's remove the head.\n");
			dequeue.headRemove();

			System.out.println("The queue head : [" + dequeue.headPeek() + "]");
			System.out.println("The queue tail : [" + dequeue.tailPeek() + "]");
			System.out.println("Number of elements in queue: [" + dequeue.size() + "]");
			System.out.println("is the queue now empty? [" + dequeue.isEmpty() + "]");
			System.out.println("contents of the queue: ");
			System.out.println(" ----------");
			System.out.println(dequeue.toString());
			System.out.println(" ---------- \n");

			System.out.println("peek at the tail: [" + dequeue.tailPeek() + "]");
			System.out.println("peek at the head: [" + dequeue.headPeek() + "] \n");

			System.out.println("Let's remove the tail...");
			dequeue.tailRemove();

			System.out.println("Done! Now the queue contains the following: ");
			System.out.println(" ----------");
			System.out.println(dequeue.toString());
			System.out.println(" ---------- \n");

			System.out.println("peek at the tail: [" + dequeue.tailPeek() + "]");
			System.out.println("peek at the head: [" + dequeue.headPeek() + "] \n");

			System.out.println("Let's remove the tail...");
			dequeue.tailRemove();

			System.out.println("Done! Now the queue contains the following: ");
			System.out.println(" ----------");
			System.out.println(dequeue.toString());
			System.out.println(" ---------- \n");

			System.out.println(
					"is the queue now empty? [" + dequeue.isEmpty() + "] it contains [" + dequeue.count + "] elements");
			System.out.println("peek at the tail: [" + dequeue.tailPeek() + "]");
			System.out.println("peek at the head: [" + dequeue.headPeek() + "] \n");

			System.out.println("Let's remove the tail...");
			dequeue.tailRemove();

			System.out.println("Done! Now the queue contains the following: ");
			System.out.println(" ----------");
			System.out.println(dequeue.toString());
			System.out.println(" ---------- \n");

			System.out.println("Number of elements in queue: [" + dequeue.size() + "]");
			System.out.println("Let's TRY to remove the tail...");
			dequeue.tailRemove();
			System.out.println(dequeue.toString());
		} catch (DequeueUnderFlowException e) { //EXTRA CREDIT
			System.out.println("** ERROR: Attempt to peek at or remove item from an already empty dequeue ***");
		}
	}
}
