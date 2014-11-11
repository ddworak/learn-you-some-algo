package uva.vito;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args){
        Reader.init(System.in);
        int n;
        try {
            n = Reader.nextInt();
            for(int c = 0; c<n;c++){
                int i = Reader.nextInt();
                int[] relatives = new int[i];
                for(int k=0;k<i;k++){
                    relatives[k]=Reader.nextInt();
                }
                Arrays.sort(relatives);
                int sum=0;
                int index = (i-1)/2;
                for(int k=0;k<index;k++)sum+=relatives[index]-relatives[k];
                for(int k=index+1;k<i;k++)sum+=relatives[k]-relatives[index];
                System.out.println(sum);
            }
        } catch (IOException ignored) {}
    }
}
/** Class for buffered value reading */
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
