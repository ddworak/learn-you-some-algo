package uva.dna;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * learning
 * Dawid Dworak
 * Date: 11/11/2014
 * Time: 13:01
 */
class Main {
     public static void main(String[] args) {
         Reader.init(System.in);
         long datasets;
         try {
             datasets = Reader.nextLong();
             for (long i = 0; i < datasets; i++) {
                 PriorityQueue<Pair> order = new PriorityQueue<Pair>();
                 Reader.nextInt();
                 int m = Reader.nextInt();
                 ArrayList<String> strings = new ArrayList<String>(m);
                 for (int k = 0; k < m; k++) {
                     strings.add(Reader.next());
                     char[] t = strings.get(k).toCharArray();
                     int v = inversions(t, 0, t.length - 1);
                     Pair entry = new Pair(v,k);
                     order.add(entry);
                 }

                 for (int k=0;k<m;k++){
                     Pair p=order.poll();
                     System.out.println(strings.get(p.b));
                 }
                 if(i!=datasets-1)System.out.println();
             }
         } catch (IOException ignored) {
         }
     }
    private static int inversions(char[]arr,int l,int r){
        if(l==r)return 0;
        int k = (l+r)/2;
        return inversions(arr,l,k)+inversions(arr,k+1,r)+merge(arr,l,k,r);
    }
    private static int merge(char[] arr,int l,int k,int r){
        char[] t1 = new char[k-l+2];
        char[] t2 = new char[r-k+1];
        int t=l;
        for(int i=0;t!=k+1;t++){
            t1[i++]=arr[t];
        }
        for(int i=0;t!=r+1;t++){
            t2[i++]=arr[t];
        }
        t1[k-l+1]=t2[r-k]='Z';
        int i1=0,i2=0,inv=0;
        for (t = l; t <= r; t++) {
            if (t2[i2] < t1[i1]) {
                arr[t] = t2[i2++];
                if (i1 != k - l + 1) inv+=k-l-i1+1;
            } else {
                arr[t] = t1[i1++];
            }
        }
        return inv;
    }
    static class Pair implements Comparable<Pair>{
        protected int a, b;
        Pair(int a,int b){
            this.a=a;
            this.b=b;
        }
        @Override
        public int compareTo(Pair o) {
            int res= Integer.compare(this.a,o.a);
            return res==0?Integer.compare(this.b,o.b) : res;
        }
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
    static long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
}
