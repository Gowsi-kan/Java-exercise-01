import java.util.ArrayList;
import java.util.Scanner;

class HashNode<K, V> {
    K key;
    V value;
    final int hashCode;
    HashNode<K, V> next;

    HashNode(K k, V v, int h) {
        key = k;
        value = v;
        hashCode = h;
    }
}

class CustomHashTable<K, V> {
    private ArrayList<HashNode<K, V>> array;
    private int numArray;
    private int size;

    CustomHashTable() {
        array = new ArrayList<>();
        numArray = 10;
        size = 0;

        for (int i = 0; i < numArray; i++)
            array.add(null);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private int hashCode(K key) {
        return key.hashCode();
    }

    public int getArrayIndex(K key) {
        int h = hashCode(key);
        return h % numArray;
    }

    public V get(K key) {
        int arrayIndex = getArrayIndex(key);
        int h = hashCode(key);

        HashNode<K, V> head = array.get(arrayIndex);

        while (head != null) {
            if (head.key.equals(key) && h == head.hashCode)
                return head.value;
            head = head.next;
        }
        return null;
    }

    public void add(K key, V value) {
        int arrayIndex = getArrayIndex(key);
        int h = hashCode(key);

        HashNode<K, V> head = array.get(arrayIndex);

        while (head != null) {
            if (head.key.equals(key) && h == head.hashCode) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        size++;
        head = array.get(arrayIndex);
        HashNode<K, V> newNode = new HashNode<>(key, value, h);
        newNode.next = head;
        array.set(arrayIndex, newNode);
    }
}

public class MaxOccurrence {
    public static void main(String[] args) {
        CustomHashTable<Character, Integer> table = new CustomHashTable<>();
        Scanner reader = new Scanner(System.in);

        int n ;
        String sen;
        do {
            System.out.print("Enter the sentence you want to check: ");
            sen = reader.nextLine();
            n = sen.length();
        } while (n > 1000);

        char[] c = sen.toCharArray();
        for (int i = 0; i < n; i++)
            table.add(c[i],(int) c[i]);

        int maxCount;
        int max = 0;
        int value = 0;

        for (int i = 0 ; i<n ; i++)
        {
            maxCount = 0;
            for (int j = 0 ; j<n ; j++) {
                if (table.get(c[i]).equals(table.get(c[j])))
                    maxCount++;
            }

            if ( maxCount > max )
            {
                value = table.get(c[i]);
                max = maxCount;
            }
            if ( maxCount == max ) {
                if (table.get(c[i]) < value)
                    value = table.get(c[i]);
                max = maxCount;
            }
        }

        char m = '0' ;
        for (int i = 0 ; i<n ; i++)
        {
            if ( value == table.get(c[i]))
            {
                m = c[i];
                break;
            }
        }

        System.out.println("Input : " + sen);
        if ( m != ' ')
            System.out.println("Output :  " + m + " " + max );
        else
            System.out.println("Output : space " + max );
		
		System.out.println("Thanks You);
        //It is Edited
    }
}
