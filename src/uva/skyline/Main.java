package uva.skyline;
import java.io.BufferedReader;
import java.io.IOException;
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
    public static void main(String[] args){
        Reader.init(System.in);
        int n = Reader.nextInt();
        PriorityQueue<Event> events = new PriorityQueue<Event>();
        for(int i=0;i<n;i++){
            int s=Reader.nextInt();
            int e=Reader.nextInt();
            int h=Reader.nextInt();
            events.add(new Event(false,s,h));
            events.add(new Event(true,e,h));
        }
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -Integer.compare(o1,o2);
            }
        };
        SortedSet<Integer> current = new TreeSet<Integer>(comparator);
        int last = 0;
        List<Building> results = new LinkedList<Building>();
        while(!events.isEmpty()){
            Event c = events.poll();
            //System.out.println("Processing:" + c.p);
            if(!c.reason){ //starts
                if(!current.isEmpty() && c.h > current.first()){ //higher than mine, damn
                    results.add(new Building(last,c.p,current.first()));
                    last = c.p;
                }
                else if(current.isEmpty())last=c.p;
                //System.out.println("Added:" + c.h);
                current.add(c.h); //always add to the queue - you never know
            }
            else {                //ends
                if(c.h == current.first()){ //it was my highest
                    results.add(new Building(last,c.p,current.first()));
                    if(!current.isEmpty())last=c.p;
                }
                //System.out.println("Removed:" + c.h);
                current.remove(c.h);
            }
        }
        for(Building b : results)System.out.println(b);
    }
}

class Building {
    int s,e,h;
    @Override
    public String toString() {
        return s +" " + e +" " + h;
    }
    public Building(int s, int e, int h) {
        this.s = s;
        this.e = e;
        this.h = h;
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
    static String next()  {
        while (!tokenizer.hasMoreTokens()) {
            //TODO add check for eof if necessary
            try {
                tokenizer = new StringTokenizer(
                        reader.readLine());
            } catch (IOException e) {
                return "";
            }
        }
        return tokenizer.nextToken();
    }
    static int nextInt() {
        return Integer.parseInt(next());
    }
}