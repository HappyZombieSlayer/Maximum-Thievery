import java.util.*;

/*  Test Case:
    7 2 1 0 2 8 2 4 --> Actual: 20 ~ Result: 20

    2 7 0 1 4 2 8 2 --> Actual: 19 ~ Result: 19

    9 10 8 3 7 3 5 2 3 1 0 4 5 --> Actual: 37 ~ Result: 37
*/

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an array of integers:\n\t");
        System.out.println("Result: "+maxThievery(parseIntList(input.nextLine())));
        //System.out.println("Result: "+maxThievery(new int[] {7, 2, 1, 0, 2, 8, 2, 4}));
    }

    public static int[] parseIntList(String s) {
        String[] l = s.split(" ");
        int[] list = new int[l.length];
        for (int i=0; i<l.length; i++) {
            list[i] = Integer.parseInt(l[i]);
        }
        return(list);
    }

    public static int maxThievery(ArrayList<Integer> list, int idx) {
        if (list.size() == 0) { // No more houses on the block
            return(0); // Return 0, since nobody was robbed
        }
        else { // There's still someone to rob
            int i = getLargestIndex(list, idx); // Next-highest index from 'idx'
            int primary = list.get(i) + maxThievery(killNeighbors(list, i), getLargestIndex(killNeighbors(list, i))); // Add the total with the recursive value of the following highest-value houses
            int secondary = 0; // Spare value to hold the alternate value, if checked
            if (idx < list.size()-1) { // If the current house isn't the last house
                secondary = 0 + maxThievery(list, idx+1); // Check the house values if current house skipped
            }
            return((primary > secondary) ? primary:secondary); // Return the greater value
        }
    }

    public static int maxThievery(int[] list) {
        ArrayList<Integer> l = new ArrayList<Integer>(); // ArrayList
        for (int n : list) l.add(n); // Add items to the list
        return(maxThievery(l, getLargestIndex(l)));
    }

    public static ArrayList<Integer> killNeighbors(ArrayList<Integer> old, int index) {
        ArrayList<Integer> list = new ArrayList<Integer>(old); // ArrayList Copy
        if (index < list.size()-1) list.remove(index+1); // Remove the right neighbor, if possible
        list.remove(index); // Remove the house that was just robbed
        if (index > 0) list.remove(index-1); // Remove the left neighbor, if possible
        return(list);
    }

    public static int getLargestIndex(ArrayList<Integer> list) {
        return(getLargestIndex(list, -1)); // Run getLargestIndex() to get the highest index in the list
    }

    public static int getLargestIndex(ArrayList<Integer> list, int idx) {
        int max = -1; // Set the maximum to the default value
        for (int i=0; i<list.size(); i++) { // Loop through each element
            max = ((i != idx && (max < 0 || list.get(i) > list.get(max))) ? i:max); // Set the maximum to the next-highest index, if applicable
        }
        return((max >= 0) ? max:0); // Return the maximum index
    }
}
