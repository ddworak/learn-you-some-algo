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
                long sum =0;
                int i = Reader.nextInt();
                int[] relatives = new int[i];
                for(int k=0;k<i;k++){
                    relatives[k]=Reader.nextInt();
                    sum+=relatives[k];
                }
                //now the real shit
                Arrays.sort(relatives);
                //System.out.println("Sum = " +sum);
                long distance = sum - (relatives[0])*(i),bestdistance=distance;
                //System.out.println("Distance at: "+relatives[0]+ " = " +bestdistance);
                for(int k=1; k<i-1;k++){
                    distance+=(relatives[k]-relatives[k-1])*(2*k-i);
                    //System.out.println("Distance at: "+relatives[k]+ " = "+distance);
                    if(distance < bestdistance){
                        bestdistance = distance;
                    }
                }
                System.out.println(bestdistance);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/** Class for buffered value reading */
class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream */
    public static void init(InputStream input) {
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
