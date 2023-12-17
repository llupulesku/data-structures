package algs13;
import java.text.DecimalFormat;
import stdlib.*;

//Leona Lupulesku

/* 
 * Complete the methods below.
 * None of the methods should modify the list, unless that is the purpose of the method.
 * 
 * You may not add any fields to the node or list classes.
 * You may not add any methods to the node class.
 * 
 * You MAY add private methods to the list class (helper functions for the recursion). 
 */
public class hw3 
{
	public static class MyLinked 
	{
		static class Node {
			public Node (double item, Node next) { this.item = item; this.next = next; }
			public double item;
			public Node next;

		}
		int N;
		Node first;


		// write a function to compute the size of the list, using a loop
		// empty list has size 0
		public int sizeLoop () {
			N=0;
			Node c = first;
			while( c!=null)
			{
				N++;
				c=c.next;	
			}
			return N;
		}

		// write a function to compute the size of the list, using an optimistic, forward recursion
		// empty list has size 0
		public int sizeForward () {
			return sizeForwardHelper(first);
		}
		
		public int sizeForwardHelper(Node n) {
			if(n==null) return 0;
			return sizeForwardHelper(n.next)+ 1;
		}

		// write a function to compute the size of the list, using an optimistic, backward recursion
		// empty list has size 0
		public int sizeBackward () {
			return sizeBackwardHelper(first,0 );
		}
		public int sizeBackwardHelper(Node n, int len) {
			if(n==null) return len;
			return sizeBackwardHelper(n.next,len+1);
		}

		// compute the position of the first 5.0 in the list, counting as an offset from the beginning.  
		// if 5.0 is the FIRST element, the position is 0
		// if 5.0 does not appear, return a negative number
		// you can write this using a loop or recursion, in any style, but you should only have one loop or recursive helper
		// I would expect
		// [0,1,2,5,5,5,5,5,8,9].positionOfFirstFiveFromBeginning() == 3
		// [0,1,2,5,5,5,5,5,8,9].positionOfLastFiveFromEnd() == 2
		public int positionOfFirstFiveFromBeginning() {
			int x = 0;
			int i = -1;
			if (first==null) 
				return i;
			Node n = first;
			while(n!=null)
				if(n.item ==5) {
					return x;
				}
				else {
					x++;
					n = n.next;
				}
		return i;
	} 


		// compute the position of the last 5.0 in the list, counting as an offset from the end.  
		// if 5.0 is the LAST element, the position is 0
		// if 5.0 does not appear, return a negative number
		// you can write this using a loop or recursion, in any style, but you should only have one loop or recursive helper
		// Hint: Use a backward recursion.  
		// Hint: If the number does not appear, return the distance to the END of the list as a NEGATIVE number.
		public int positionOfLastFiveFromEnd () {
			int x = 0;
			int i = -1;
			Node n = first;
			while(n != null) {
				if(n.item==5) {
					i = x;
			}
			x++;
			n = n.next;
		}
			
		   if(i==-1) 
			   return -(x+1);
		   else {
			   return (x-i)-1;
		   }
	}
		
		// delete the first element
		public void deleteFirst () {
			if(first ==null)
				return;
			else {
			Node current = first.next;
			first = null;
			first = current;
			
		}
	}
		// delete the kth element (where k is between 0 and N-1 inclusive)
		public void delete (int k) {
			if (k < 0 || k >= N) throw new IllegalArgumentException ();
		
			if(k==0) {
				first = first.next;
				N--;
			}
			else {
				Node n =first;
				int i =0;
				while(k-1>i) {
					n = n.next;
					i++;
				}
				Node next = n.next.next;
				n.next = next;
				N--;
			
			}
		}
			
		
		// reverse the list "in place"... without creating any new nodes
		public void reverse () {
			Node prev = null, curr = first, next = null;
			while(curr != null) {
				next = curr.next;
				curr.next = prev;
				prev = curr;
				curr = next;
			}
			first = prev;
		}

		// remove all occurrences of item from the list 
		public void remove (double item) {
			
			while(first != null && first.item == item) {
				first = first.next;
				N = N-1;
			}
			if(first ==null) {
				return;
			}
			Node n = first;
			while(n.next !=null) {
				if(n.next.item == item) {
					n.next = n.next.next;
					N=N-1;
				}
				else {
					if(n.next.item !=item) {
						n = n.next;
					}
				}
			}
		}
	
			
		
				
		
			
			
		
	
		
		public static void main (String args[]) {
			//mainDebug ();
			mainRunTests ();
			
		}
		private static void mainDebug () {
			// Use this for debugging!
			// Add the names of helper functions if you use them.
			Trace.drawStepsOfMethod ("sizeLoop");
			Trace.drawStepsOfMethod ("sizeForward");
			Trace.drawStepsOfMethod ("sizeBackward");
			Trace.drawStepsOfMethod ("positionOfFirstFiveFromBeginning");
			Trace.drawStepsOfMethod ("positionOfLastFiveFromEnd");
			Trace.drawStepsOfMethod ("deleteFirst");
			Trace.drawStepsOfMethod ("delete");
			Trace.drawStepsOfMethod ("reverse");
			Trace.drawStepsOfMethod ("remove");
			Trace.run (); 
			
			// TODO:  Put the test here you want to debug:
			testSizeLoop (4, "11 -21.2 31 41");
			testRemove(5, "11 21 5 5 5 5 31 41", "[ 11 21 31 41 ]");

			// TODO:  Put the test here you want to debug:
			testDelete (2, "11 21 31 41", "[ 11 21 41 ]");
		}
		private static void mainRunTests () {
			testSizeLoop (0, "");
			testSizeLoop (1, "11");
			testSizeLoop (2, "11 21");	
			testSizeLoop (4, "11 -21.2 31 41");

			testSizeForward (0, "");
			testSizeForward (1, "11");
			testSizeForward (2, "11 21");	
			testSizeForward (4, "11 -21.2 31 41");

			testSizeBackward (0, "");
			testSizeBackward (1, "11");
			testSizeBackward (2, "11 21");	
			testSizeBackward (4, "11 -21.2 31 41");

			testPositionOfFirstFiveFromBeginning (-1, "");
			testPositionOfFirstFiveFromBeginning (-1, "11");
			testPositionOfFirstFiveFromBeginning (-1, "11 21 31 41");
			testPositionOfFirstFiveFromBeginning (0, "5 11 21 31 41");
			testPositionOfFirstFiveFromBeginning (1, "11 5 21 31 41");
			testPositionOfFirstFiveFromBeginning (2, "11 21 5 31 41");
			testPositionOfFirstFiveFromBeginning (3, "11 21 31 5 41");
			testPositionOfFirstFiveFromBeginning (4, "11 21 31 41 5");
			testPositionOfFirstFiveFromBeginning (3, "0 1 2 5 5 5 5 5 8 9");

			testPositionOfLastFiveFromEnd (-1, "");
			testPositionOfLastFiveFromEnd (-2, "11");
			testPositionOfLastFiveFromEnd (-5, "11 21 31 41");
			testPositionOfLastFiveFromEnd (4, "5 11 21 31 41");
			testPositionOfLastFiveFromEnd (3, "11 5 21 31 41");
			testPositionOfLastFiveFromEnd (2, "11 21 5 31 41");
			testPositionOfLastFiveFromEnd (1, "11 21 31 5 41");
			testPositionOfLastFiveFromEnd (0, "11 21 31 41 5");
			testPositionOfLastFiveFromEnd (2, "0 1 2 5 5 5 5 5 8 9");
			testPositionOfLastFiveFromEnd (2, "0 1 2 5 5 5 5 5 5 8 9");

			// Trace.run (); // uncomment this to get drawings in showError
			testDelete (0, "", "java.lang.IllegalArgumentException");
			testDelete(-1, "11 21 31", "java.lang.IllegalArgumentException");
			testDelete (3, "11 21 31", "java.lang.IllegalArgumentException");
			testDelete (0, "11", "[ ]");
			testDelete (0, "11 21 31 41", "[ 21 31 41 ]");
			testDelete (1, "11 21 31 41", "[ 11 31 41 ]");
			testDelete (2, "11 21 31 41", "[ 11 21 41 ]");
			testDelete (3, "11 21 31 41", "[ 11 21 31 ]");
			testDelete (0, "11 21 31 41 51", "[ 21 31 41 51 ]");
			testDelete (1, "11 21 31 41 51", "[ 11 31 41 51 ]");
			testDelete (2, "11 21 31 41 51", "[ 11 21 41 51 ]");
			testDelete (3, "11 21 31 41 51", "[ 11 21 31 51 ]");
			testDelete (4, "11 21 31 41 51", "[ 11 21 31 41 ]");

			testReverse ("", "[ ]");
			testReverse ("11", "[ 11 ]");
			testReverse ("11 21", "[ 21 11 ]");
			testReverse ("11 21 31", "[ 31 21 11 ]");
			testReverse ("11 21 31 41", "[ 41 31 21 11 ]");
			testReverse ("11 21 31 41 51", "[ 51 41 31 21 11 ]");
			
			testRemove (5, "", "[ ]");
			testRemove (5, "5", "[ ]");
			testRemove (5, "5 5", "[ ]");
			testRemove (5, "5 5 5", "[ ]");
			testRemove (5, "11", "[ 11 ]");
			testRemove (5, "11 21", "[ 11 21 ]");
			testRemove (5, "11 21 31", "[ 11 21 31 ]");
			testRemove (5, "5 11 21 31 41", "[ 11 21 31 41 ]");
			testRemove (5, "5 5 11 21 31 41", "[ 11 21 31 41 ]");
			testRemove (5, "5 5 5 11 21 31 41", "[ 11 21 31 41 ]");
			testRemove (5, "11 21 31 41 5", "[ 11 21 31 41 ]");
			testRemove (5, "11 21 31 41 5 5", "[ 11 21 31 41 ]");
			testRemove (5, "11 21 31 41 5 5 5", "[ 11 21 31 41 ]");
			testRemove (5, "11 21 5 31 41", "[ 11 21 31 41 ]");
			testRemove (5, "11 21 5 5 31 41", "[ 11 21 31 41 ]");
			testRemove (5, "11 21 5 5 5 31 41", "[ 11 21 31 41 ]");
			testRemove (5, "5 11 21 31 41", "[ 11 21 31 41 ]");
			testRemove (5, "5 5 11 21 31 41", "[ 11 21 31 41 ]");
			testRemove (5, "5 5 5 11 21 31 41", "[ 11 21 31 41 ]");
			testRemove (5, "11 21 31 41 5", "[ 11 21 31 41 ]");
			testRemove (5, "11 21 31 41 5 5", "[ 11 21 31 41 ]");
			testRemove (5, "11 21 31 41 5 5 5", "[ 11 21 31 41 ]");
			testRemove (5, "11 21 5 31 41", "[ 11 21 31 41 ]");
			testRemove (5, "11 21 5 5 31 41", "[ 11 21 31 41 ]");
			testRemove (5, "11 21 5 5 5 31 41", "[ 11 21 31 41 ]");
			testRemove (5, "5 5 5 11 5 5 21 5 31 5 5 41 5 5 5", "[ 11 21 31 41 ]");
			testRemove (5.1, "5.1 5.1 5.1 5 11 5.1 5.1 21 5.1 31 5.1 5.1 41 5.1 5.1 5.1", "[ 5 11 21 31 41 ]");
					
			StdOut.println ("Finished tests");
		}
		

		/* ToString method to print */
		public String toString () { 
			// Use DecimalFormat #.### rather than String.format 0.3f to leave off trailing zeroes
			DecimalFormat format = new DecimalFormat ("#.###");
			StringBuilder result = new StringBuilder ("[ ");
			for (Node x = first; x != null; x = x.next) {
				result.append (format.format (x.item));
				result.append (" ");
			}
			result.append ("]");
			return result.toString ();
		}

		/* Method to create lists */
		public static MyLinked of(String s) {
			int N = 0;
			Node first = null;
			String[] nums = s.split (" ");
			for (int i = nums.length-1; i >= 0; i--) {
				try { 
					double num = Double.parseDouble (nums[i]); 
					first = new Node (num, first);  
					N++;
				} catch (NumberFormatException e) {
					// ignore anything that is not a double
				}
			}
			MyLinked result = new MyLinked ();
			result.first = first;
			result.N = N;
			return result;
		}
		
		static void showError (String message) {
			Trace.draw ();
			StdOut.println (message);
			//throw new Error (); // stops execution
		}
		
		// lots of copy and paste in these test!
		private static void testSizeLoop (int expected, String sList) {
			MyLinked list = MyLinked.of (sList);
			String sStart = list.toString ();
			int actual = list.sizeLoop();
			if (expected != actual) {
				StdOut.format ("Failed %s.sizeLoop(): Expecting [%d] Actual [%d]\n", sStart, expected, actual);
			}
			String sEnd = list.toString ();
			if (! sStart.equals (sEnd)) {
				StdOut.format ("Failed %s.sizeLoop(): List changed to %s\n", sStart, sEnd);
			}
		}	
		private static void testSizeForward (int expected, String sList) {
			MyLinked list = MyLinked.of (sList);
			String sStart = list.toString ();
			int actual = list.sizeForward ();
			if (expected != actual) {
				StdOut.format ("Failed %s.sizeForward(): Expecting [%d] Actual [%d]\n", sStart, expected, actual);
			}
			String sEnd = list.toString ();
			if (! sStart.equals (sEnd)) {
				StdOut.format ("Failed %s.sizeForward(): List changed to %s\n", sStart, sEnd);
			}
		}	
		private static void testSizeBackward (int expected, String sList) {
			MyLinked list = MyLinked.of (sList);
			String sStart = list.toString ();
			int actual = list.sizeBackward ();
			if (expected != actual) {
				StdOut.format ("Failed %s.sizeBackward(): Expecting [%d] Actual [%d]\n", sStart, expected, actual);
			}
			String sEnd = list.toString ();
			if (! sStart.equals (sEnd)) {
				StdOut.format ("Failed %s.sizeBackward(): List changed to %s\n", sStart, sEnd);
			}
		}
		private static void testPositionOfFirstFiveFromBeginning (int expected, String sList) {
			MyLinked list = MyLinked.of (sList);
			String sStart = list.toString ();
			int actual = list.positionOfFirstFiveFromBeginning ();
			//if ((expected >= 0 && expected != actual) || (expected < 0 && actual > 0)) {
			if (expected != actual) {
				StdOut.format ("Failed %s.positionOfFirstFiveFromBeginning(): Expecting [%d] Actual [%d]\n", sStart, expected, actual);
			}
			String sEnd = list.toString ();
			if (! sStart.equals (sEnd)) {
				StdOut.format ("Failed %s.positionOfFirstFiveFromBeginning(): List changed to %s\n", sStart, sEnd);
			}
		}
		private static void testPositionOfLastFiveFromEnd (int expected, String sList) {
			MyLinked list = MyLinked.of (sList);
			String sStart = list.toString ();
			int actual = list.positionOfLastFiveFromEnd ();
			//if ((expected >= 0 && expected != actual) || (expected < 0 && actual > 0)) {
			if (expected != actual) {
				StdOut.format ("Failed %s.testPositionOfLastFiveFromEnd(): Expecting [%d] Actual [%d]\n", sStart, expected, actual);
			}
			String sEnd = list.toString ();
			if (! sStart.equals (sEnd)) {
				StdOut.format ("Failed %s.testPositionOfLastFiveFromEnd(): List changed to %s\n", sStart, sEnd);
			}
		}
		private static void checkInvariants (String message, MyLinked list) {
			MyLinked.Node x = list.first;
			int N = list.N;
			for (int i = 0; i < N; i++) {
				if (x == null) {
					showError (String.format ("%s: Expected %d nodes, but got less.", message, N));
					return;
				}
				x = x.next;
			}
			if (x != null) {
				showError (String.format ("%s: Expected %d nodes, but got more.", message, N));
			}
		}
		private static void check (String message, MyLinked actual, String expected) {
			checkInvariants (message, actual);
			if (!expected.equals (actual.toString ())) {
				showError (String.format ("%s: expected=%s, actual=%s", message, expected, actual.toString ()));
			}
		}
		private static void testDelete (int k, String list, String expected) {
			MyLinked actual = MyLinked.of (list);
			String message = String.format ("[ %s ].delete( %d )", list, k);
			try {
				actual.delete (k);
			} catch (Throwable e) {
				String exception = e.getClass ().getName ();
				if (! exception.equals (expected)) {
					e.printStackTrace (); // for debugging
					showError (String.format ("%s: expected=%s, actual=%s", message, expected, exception));
				}
				return;
			}
			check (message, actual, expected);
		}
		private static void testReverse (String list, String expected) {
			MyLinked actual = MyLinked.of (list);
			actual.reverse ();
			String message = String.format ("[ %s ].reverse( )", list);
			check (message, actual, expected);
		}
		private static void testRemove (double item, String list, String expected) {
			MyLinked actual = MyLinked.of (list);
			actual.remove (item);
			String message = String.format ("[ %s ].remove( %.2f )", list, item);
			check (message, actual, expected);
		}	
	}
}