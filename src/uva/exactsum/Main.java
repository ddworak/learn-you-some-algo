package uva.exactsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
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
        int n;
        try {
        while(true){
            n = Reader.nextInt();
            long[] prices = new long[n];
            for(int i=0; i<n;i++)prices[i]=Reader.nextLong();
            Arrays.sort(prices);
            int l=-1,r=n-1,foundsuml=0,foundsumr=n-1;
            long m = Reader.nextLong();
            while(l!=r) {
                l++;
                while (prices[l]+prices[r] < m && l<r) l++;
                while (prices[l]+prices[r] > m && r>l) r--;
                if(prices[l]+prices[r]==m){
                    foundsuml=l;
                    foundsumr=r;
                }
            }
            System.out.println("Peter should buy books whose prices are "+prices[foundsuml]+" and "+prices[foundsumr]+".\n");
        }
        } catch (Exception ignored) {

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
    static long nextLong() throws IOException {
        return Long.parseLong(next());
    }
    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
}
