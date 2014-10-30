import java.util.*;

/**
 * learning
 * Dawid Dworak
 * Date: 30/10/2014
 * Time: 00:38
 */
public class RemoveDuplicates {
    public static <E> List<E> unique(List<E> list) {
        return new ArrayList<E>(new HashSet<E>(list));
    }

    public static <E> void removeDuplicates(List<E> list) {
        HashSet<E> tmp = new HashSet<E>();
        Iterator<E> it = list.iterator();
        while (it.hasNext()) {
            E elem = it.next();
            if (tmp.contains(elem)) {
                it.remove();
            } else {
                tmp.add(elem);
            }
        }
    }

    public static <E> void removeLastDuplicates(List<E> list) {
        HashMap<E, Boolean> tmp = new HashMap<E, Boolean>();
        for (E elem : list) {
            Boolean toDelete = tmp.get(elem);
            if (toDelete == null) tmp.put(elem, false);
            else if (!toDelete) tmp.put(elem, true);
        }
        ListIterator<E> it = list.listIterator(list.size());
        while (it.hasPrevious()) {
            E elem = it.previous();
            Boolean toDelete = tmp.get(elem);
            if (toDelete != null && toDelete) {
                it.remove();
                tmp.put(elem, false);
            }
        }
    }

    public static <E> void removeNthInstances(List<E> list, int n) {
        HashMap<E, Integer> tmp = new HashMap<E, Integer>();
        Iterator<E> it = list.iterator();
        while (it.hasNext()) {
            E elem = it.next();
            Integer k = tmp.get(elem);
            if (k == null) {
                tmp.put(elem, 2); //2 for a reason
                if (n == 1) it.remove();
            } else {
                if (k == n) it.remove();
                tmp.put(elem, k + 1);
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> test1 = new LinkedList<Integer>();
        List<Integer> test2 = new LinkedList<Integer>();
        List<Integer> test3 = new LinkedList<Integer>();
        List<Integer> test4 = new LinkedList<Integer>();
        Integer ints[] = {1, 2, 3, 4, 1, 5, 1, 3, 4, 7, 3};
        test1.addAll(Arrays.asList(ints));
        System.out.println(test1.toString());
        test2.addAll(test1);
        test3.addAll(test1);
        test4.addAll(test1);
        List<Integer> testu = unique(test4);
        System.out.println("With set: " + testu.toString());
        removeDuplicates(test1);
        System.out.println("All: " + test1.toString());
        removeLastDuplicates(test2);
        System.out.println("Last: " + test2.toString());
        removeNthInstances(test3, 1);
        System.out.println("Nth: " + test3.toString());
    }
}
