package uva.prepost;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * learning
 * Dawid Dworak
 * Date: 17/11/2014
 * Time: 21:49
 */
public class Main {
    public static void main(String[] args){
        Reader.init(System.in);
        int next;
        int max = Integer.MAX_VALUE;
        Stack<Pair> s = new Stack<Pair>();
        int cur=0;
        try {
            cur = Reader.nextInt();
            next = Reader.nextInt();
            while(true){
                if(next < cur){ //LEFT
                    Pair p = new Pair(cur,max);
                    s.push(p);
                    max = cur;
                    cur = next;
                    next = Reader.nextInt();
                }
                else {
                    if(next < max){ //RIGHT
                        Pair p = new Pair(cur,max);
                        s.push(p);
                        cur = next;
                        next = Reader.nextInt();
                    }
                    else { //PRINT & pop
                        System.out.println(cur);
                        Pair g = s.pop();
                        cur = g.cur;
                        max = g.max;
                    }
                }
            }
        } catch (Exception ignored) {
            System.out.println(cur);
            while(!s.empty())System.out.println(s.pop().cur);
        }
    }
}
class Pair{
    int cur,max;
    public Pair(int cur, int max) {
        this.cur = cur;
        this.max = max;
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
    static String next() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                    reader.readLine());
        }
        return tokenizer.nextToken();
    }
    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
}