import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.*;
public class Selection <E extends Comparable<E>> {
    int k;
    ArrayList<E> input;

    public Selection()
    {
        super();
    }
    public static long miliTime()
    {
        return System.currentTimeMillis();
    }
    public void addInput(ArrayList<E> newInput)
    {
        input = newInput;
    }

    public E algorithm1(int kth) {
        k = kth;
       ArrayList<E> kValues = new ArrayList<>();
       long before = miliTime();
       for (int x = 0; x < k; x++) {
           kValues.add(input.get(x));
       }
        Collections.sort(kValues, Collections.reverseOrder());
       for(int x = k; x < input.size();x++)
       {
           E kInput = input.get(x);
           E kthlargest = kValues.get(kValues.size()-1);
           if(kInput.compareTo(kthlargest) > 0)
           {
               kValues.set(kValues.size()-1,kInput);
               Collections.sort(kValues, Collections.reverseOrder());

               }
           }
       long after =miliTime();
       long time = after - before;
       System.out.println("Algorithm 1 time(miliseconds): " + time);
        return kValues.get(kValues.size()-1);
    }

    public E algorithm2(int kth) {
        k = kth;
        MaxHeap<E> inputs = new MaxHeap<>();
        long before = miliTime();
        inputs.buildHeap(input);
        for(int x = 1; x < k; x++)
        {
            inputs.removeHeap();
        }
        long after =miliTime();
        long time = after - before;
        System.out.println("Algorithm 2 time(miliseconds): " + time);
        return inputs.get(0);

    }
public E algorithm3(int kth) {
    k = kth;
    MaxHeap<E> largestElements = new MaxHeap<>();
    long before = miliTime();
    for (int x = 0; x < k; x++) {
        largestElements.addHeap(input.get(x));
    }
    for(int x = k; x < input.size();x++)
    {
        E min = largestElements.getMin();
        E kInput = input.get(x);
        if(kInput.compareTo(min) > 0)
        {
            largestElements.addHeap(kInput);
            largestElements.remove(min);

        }

    }
    long after =miliTime();
    long time = after - before;
    System.out.println("Algorithm 3 time(miliseconds): " + time);
    return largestElements.getMin();

}
    public static void  main(String[] args)
    {

        ArrayList<Integer> collection = new ArrayList<>();
         /*
        collection.add(1);
        collection.add(5);
        collection.add(10);
        collection.add(7);
        collection.add(3);
        collection.add(2);
        collection.add(17);
        collection.add(18);
        */
        System.out.println("Making List");
        for (int i=1; i<10000001; i++) {
            int value =(int)(Math.random() * 10000) + 1;
            collection.add(value); // adding all the numbers between 1-1m to a list.
        }
        Collections.sort(collection);
        Selection<Integer> theCollection = new Selection<>();
        theCollection.addInput(collection);
        System.out.println("Starting Algorithms");
       System.out.println(theCollection.algorithm1(1000000));
        System.out.println(theCollection.algorithm2(1000000));
        System.out.println(theCollection.algorithm3(1000000));




    }
}
