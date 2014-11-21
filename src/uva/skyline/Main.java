package uva.skyline;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * learning
 * Dawid Dworak
 * Date: 17/11/2014
 * Time: 21:49
 */
public class Main {
    public static void main(String[] args) {
        Reader.init(System.in);
        PriorityQueue<Event> events = new PriorityQueue<Event>();
        int s = Reader.nextInt();
        while (s != -1) {
            int h = Reader.nextInt();
            int e = Reader.nextInt();
            events.add(new Event(false, s, h));
            events.add(new Event(true, e, h));
            s = Reader.nextInt();
        }
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -Integer.compare(o1, o2);
            }
        };
        SortedMap<Integer,Integer> current = new TreeMap<Integer,Integer>(comparator);
        current.put(0,1);
        int lasth = -1;
        int mostAdded=0;
        boolean lastEnded = true;
        List<Map.Entry<Integer,Integer>> results = new LinkedList<Map.Entry<Integer, Integer>>();
        while (!events.isEmpty()) {
            Event c = events.poll();
            //System.out.println("Processing:" + c.p);
            if (c.reason) {  //ending
                if(current.containsKey(c.h) && current.get(c.h)>1){
                    current.put(c.h,current.get(c.h)-1);
                }
                else current.remove(c.h);
                //System.out.println("Ending:" + c.h + " current.first=" + current.firstKey() + " c.h=" + c.h + " mostAdded=" + mostAdded);
                if(current.firstKey()!=c.h && c.h!=mostAdded){
                    lastEnded=true;
                    //System.out.println("Last ended:" + c.p);
                }
            }
            else {                   //starting
                if(c.h>mostAdded)mostAdded=c.h;
                //lastEnded=false;
                //System.out.println("current on q:"+current.size());
                if(current.containsKey(c.h)){
                    current.put(c.h,current.get(c.h)+1);
                }
                else current.put(c.h, 1);
                //System.out.println("Starting:" + c.h + ",mostAdded=" + mostAdded + " current on q:" + current.size());
            }
            if(events.isEmpty() || events.peek().p!=c.p){ //I should decide now!
                //System.out.println("Made decision");
                //int temp = results.size();
                if(lastEnded){
                    if(mostAdded>current.firstKey() && mostAdded > lasth){
                        results.add(new AbstractMap.SimpleEntry<Integer, Integer>(c.p,mostAdded));
                        //if(results.size()>temp)System.out.println("Decided1:"+results.get(results.size()-1));
                        //mostAdded=0;
                    }
                    if(current.firstKey()!=lasth){
                        //System.out.println("Case "+c.p+ " " + current.first() + " " +lasth);
                        results.add(new AbstractMap.SimpleEntry<Integer, Integer>(c.p, current.firstKey()));
                        lasth=current.firstKey();
                        lastEnded=false;
                       //if(results.size()>temp)System.out.println("Decided2:"+results.get(results.size()-1)+" currents "+current.size());
                    }
                }
                else if(current.firstKey() > lasth){
                    results.add(new AbstractMap.SimpleEntry<Integer, Integer>(c.p,current.firstKey()));
                    //if(results.size()>temp)System.out.println("Decided3:"+results.get(results.size()-1));
                    lasth=current.firstKey();
                    lastEnded=false;
                }
                mostAdded=0;
                //System.out.println("");
            }
        }

        Iterator<Map.Entry<Integer,Integer>> i = results.iterator();
        if(i.hasNext()){
            Map.Entry<Integer,Integer> e =i.next();
            System.out.print(e.getKey()+" "+e.getValue());
        }
        while(i.hasNext()){
            Map.Entry<Integer,Integer> e =i.next();
            System.out.print(" " + e.getKey()+" "+e.getValue());
        }
        System.out.print("\n");
    }
}
class Event implements Comparable<Event>{
    boolean reason; //false start
    int p;
    int h;
    Event(boolean reason, int p, int h) {
        this.reason = reason;
        this.p = p;
        this.h = h;
    }
    @Override
    public int compareTo(Event o) {
        return Integer.compare(p,o.p);
    }
}
class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;
    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) {
        reader = new BufferedReader(
                new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }
    /** get next word */
    static String next() throws Exception {
        while (!tokenizer.hasMoreTokens()) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                    reader.readLine());
        }
        return tokenizer.nextToken();
    }
    static int nextInt() {
        try {
            return Integer.parseInt(next());
        } catch (Exception e) {
            return -1;
        }
    }
}