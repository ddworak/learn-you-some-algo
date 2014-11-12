package uva.coverage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * learning
 * Dawid Dworak
 * Date: 11/11/2014
 * Time: 01:23
 */
class Main {
    public static void main(String[] args){
        Reader.init(System.in);
        Comparator<Pair> startsFirstComparator = new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return Long.compare(o1.a,o2.a);
            }
        };

        try {
            int n=Reader.nextInt();
            for(int c=0;c<n;c++){
                int m = Reader.nextInt();
                ArrayList<Pair> p = new ArrayList<Pair>();
                Pair temp = new Pair(Reader.nextInt(),Reader.nextInt());
                int l=0;
                while(!(temp.a==0 && temp.b==0)) {
                    p.add(temp);
                    l++;
                    temp = new Pair(Reader.nextInt(),Reader.nextInt());
                }

                Collections.sort(p,startsFirstComparator);

                ArrayList<Integer> selected = new ArrayList<Integer>();
                int cur=0,pair_index=0,best_index;
                while(cur<m && pair_index<l && p.get(pair_index).a<=cur){
                    best_index=pair_index++;
                    for(;pair_index<l && p.get(pair_index).a<=cur;pair_index++){
                        if(p.get(pair_index).b > p.get(best_index).b)best_index=pair_index;
                    }
                    selected.add(best_index);
                    cur = p.get(best_index).b;
                }
                if(cur<m)System.out.println(0);
                else {
                    System.out.println(selected.size());
                    for(Integer i : selected){
                        System.out.println(p.get(i));
                    }
                }
                if(c!=n-1)System.out.println();
            }
        }catch(IOException ignored){}
    }
    static class Pair{
        int a,b;
        private Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
        @Override
        public String toString() {
            return a +" " + b;
        }
    }
}
/** Class for buffered reading int and double values */
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
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                    reader.readLine() );
        }
        return tokenizer.nextToken();
    }
    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
}
