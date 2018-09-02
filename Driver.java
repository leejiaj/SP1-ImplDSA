package txp150530;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Terrence Park
 * @author Leejia James
 *
 * Driver class to test DoublyLinkedList.
 *
 * Ver 1.0: 2018/08/24
 */
public class Driver
{
    public static void main(String[] args) throws NoSuchElementException
    {
        int n = 10;
        if(args.length > 0) {
            n = Integer.parseInt(args[0]);
        }

        DoublyLinkedList<Integer> lst = new DoublyLinkedList<>();
        for(int i=1; i<=n; i++) {
            lst.add(Integer.valueOf(i));
        }
        lst.printList();

        DoublyLinkedList<Integer>.DLLIterator it = lst.dllIterator();
        Scanner in = new Scanner(System.in);
        whileloop:
        while(in.hasNext()) {
            int com = in.nextInt();
            switch(com) {
            case 1:  // Move to next element and print it
                if (it.hasNext()) {
                    System.out.println(it.next());
                } else {
                    break whileloop;
                }
                break;
            case 2:  // Remove element
                it.remove();
                lst.printList();
                break;
            case 3:  // Move to previous element and print it
                if (it.hasPrev()) {
                    System.out.println(it.prev());
                } else {
                    break whileloop;
                }
                break;
            case 4:  // Add element
                int elemAdd = in.nextInt();
                it.add(elemAdd);
                lst.printList();
                break;
            case 5:  // Unzip the list
                lst.unzip();
                lst.printList();
                break;
            default:  // Exit loop
                break whileloop;
            }
        }
        lst.printList();
        lst.unzip();
        lst.printList();
    }
}


/* Sample input:
   1 2 1 2 1 1 1 2 1 1 2 0
   Sample output:
10: 1 2 3 4 5 6 7 8 9 10
1
9: 2 3 4 5 6 7 8 9 10
2
8: 3 4 5 6 7 8 9 10
3
4
5
7: 3 4 6 7 8 9 10
6
7
6: 3 4 6 8 9 10
6: 3 4 6 8 9 10
6: 3 6 9 4 8 10
*/
