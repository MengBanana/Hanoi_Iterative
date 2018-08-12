/**
 * HanoiStack.java
 *
 *  @since July 23, 2018
 *  @author Xinmeng Zhang
 */

import java.util.Stack;

/**
 * This HanoiStack class implement the Tower of Hanoi in an iterative way
 * The rules:
 * Only one disk can be moved at a time
 * Each one moves the top disk from one stack on top to another stack
 * No disk may be placed on top of a smaller disk 
 */

/*For an even number of disks:
 * make the legal move between pegs A and B (in either direction)
 * make the legal move between pegs A and C (in either direction)
 * make the legal move between pegs B and C (in either direction),
 * repeat until complete.
 * For an odd number of disks:
 * make the legal move between pegs A and C (in either direction)
 * make the legal move between pegs A and B (in either direction)
 * make the legal move between pegs B and C (in either direction),
 */

public class HanoiStack {	
	
	/**
	 * The functio n uses an array of three integer stacks to represent
	 * the poles, and small integers beween 1 and ndisks to represent
	 * the disks, with the largest disk numbered 1 and smallest as ndisks
	 * 
	 * @param ndisks the number of disks to be moved from src to dest
	 * @param src the starting pole index
	 * @param dest the ending pole index
	 */
	public static void hanoi(int ndisks, int src, int dest) {
		// array of three integer stacks
		@SuppressWarnings("rawtypes")
		Stack[] poles = new Stack[3];
		//use arraydeque perform better than Stack<> because stack is multithread safe
		Stack<Integer> A = new Stack<>();
		Stack<Integer> B = new Stack<>();
		Stack<Integer> C = new Stack<>();
		poles[0] = A;
		poles[1] = B;
		poles[2] = C;
		// initialize scr stack by pushing all the integers to the stack
		// with the largest disk numbered 1 and the smallest as ndisks
		for (int i=1; i<=ndisks; i++) {
			A.push(i);
		}
		int size = poles[src].size(); //how many disks we want to move
		int other = 1; // index of the other pole
		// two condistions even number of disks or odd number of disks
		if (size % 2 == 0) {
			while (C.size() < ndisks) { 
				// while we haven't move all the disks to dest continue
				moveDisk(poles, src, other);
				moveDisk(poles, src, dest);
				// whenever after we move from src to dest check if
				// C size is ndisks means we already transfer all the disks
				// to dest so return
				if (C.size() == ndisks) {
					return;
				}
				moveDisk(poles, other, dest);
			}
		}
		else if (size % 2 != 0) {
			while (C.size() < ndisks) {
				moveDisk(poles, src, dest);
				if (C.size() == ndisks) {
					return;
				}
				moveDisk(poles, src, other);
				moveDisk(poles, other, dest);
			}
		}	
	}
	
	/**
	 * moveDisk function takes the pole array and the src and dest pole indexes
	 * The function determines the move direction by comparing the top disks
	 * on the indexed stakcs, then moves the disk and prints a message
	 * "Move disk from pole %d to pole %d\n" to standard out
	 * 
	 * @param poles the array of three integer stacks represents scr stack
	 * dest stack and other stack
	 * @param src the scr index to be moved from or to
	 * @param dest the dest index to be moved from or to
	 */
	@SuppressWarnings("unchecked")
	public static void moveDisk(@SuppressWarnings("rawtypes") Stack[] poles, int src, int dest) {
		// if src stack || dest stack is empty, move from the non-empty
		// stack to empty stack
		if (poles[src].isEmpty()) {
			poles[src].push((int) poles[dest].pop());
			System.out.printf("Move disk from pole %d to pole %d\n", dest, src);
		}
		else if (poles[dest].isEmpty()) {
			poles[dest].push((int) poles[src].pop());
			System.out.printf("Move disk from pole %d to pole %d\n", src, dest);
		}
		// if scr stack and dest stack are not empty
		// need to cheek the top integers, move the larger integer which is
		// a small disk to the other stack
		else {
			int s = (int) poles[src].peek();
			int d = (int) poles[dest].peek();
			if (s > d) { 
				// s is larger integer but the smaller disk, move src to dest
				poles[dest].push(poles[src].pop());
				System.out.printf("Move disk from pole %d to pole %d\n", src, dest);
			}
			else {
				poles[src].push(poles[dest].pop());
				System.out.printf("Move disk from pole %d to pole %d\n", dest, src);
			}
		}		
	}
	
	
	/**
	 * The main program to call hanoi function to move 1, 2, 3, and 4 disks from pole 0 to pole 2
	 * @param args the array of string objects 
	 */
	public static void main(String[] args) {
		for (int i = 4; i >= 1; i--) {
			System.out.printf("Testing: Move %d disks from 0 -> 2:\n", i);
			hanoi(i, 0, 2);
			System.out.println("");
		}
	}
}


