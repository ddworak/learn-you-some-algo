package uva.brackets;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by nuk on 01.12.14.
 */
public class Main {
    private static int n;

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        for (int test = 1; test != 11; test++) {
            int length = Reader.nextInt();
            n = (int) Math.pow(2, Math.ceil(Math.log10(length) / Math.log10(2)));
            Node[] t = new Node[2 * n];
            buildTree(t, 1, 1, n);
            String start = Reader.next();
            for (int i = 0; i < length; i++) {
                if (start.charAt(i) == '(') {
                    t[exact(i + 1)].onR = 1;
                } else {
                    t[exact(i + 1)].onL = 1;
                }
            }
            updateTree(t, 1);
            int m = Reader.nextInt();
            int op;
            System.out.println("Test " + test + ":");
            for (int i = 0; i < m; i++) {
                op = Reader.nextInt();
                if (op == 0) {
                    if (t[1].onL == 0 && t[1].onR == 0) System.out.println("YES");
                    else System.out.println("NO");
                } else changePoint(t, op);
            }
        }
    }

    private static void buildTree(Node[] t, int i, int l, int p) {
        t[i] = new Node();
        if (p - l > 1) { //not leaves yet
            int mid = (l + p) / 2;
            buildTree(t, lchild(i), l, mid);
            buildTree(t, rchild(i), mid + 1, p);
        } else { //leaves
            t[lchild(i)] = new Node();
            t[rchild(i)] = new Node();
        }
    }

    private static void updateTree(Node[] t, int i) {
        if (i > n - 1) return;
        updateTree(t, lchild(i));
        updateTree(t, rchild(i));
        int l = lchild(i), r = rchild(i);
        int closed = Math.min(t[l].onR, t[r].onL);
        t[i].onL = t[l].onL + t[r].onL - closed;
        t[i].onR = t[r].onR + t[l].onR - closed;
        //System.out.println("Updated node "+ i + " "+t[i]);
    }

    private static void changePoint(Node[] t, int i) {
        int e = exact(i);
        t[e].rev();
        e = p(e);
        while (e != 0) {
            int l = lchild(e), r = rchild(e);
            int closed = Math.min(t[l].onR, t[r].onL);
            t[e].onL = t[l].onL + t[r].onL - closed;
            t[e].onR = t[r].onR + t[l].onR - closed;
            //System.out.println("Updated node "+ e + " "+t[e]);
            e = p(e);
        }
    }

    private static class Node {
        int onL, onR;

        public Node() {
            onL = onR = 0;
        }

        public void rev() {
            if (onL == 1) {
                onR = 1;
                onL = 0;
            } else {
                onL = 1;
                onR = 0;
            }
        }

        @Override
        public String toString() {
            return "Node{" +
                    "onL=" + onL +
                    ", onR=" + onR +
                    '}';
        }
    }

    private static int p(int i) {
        return i / 2;
    }

    private static int lchild(int i) {
        return 2 * i;
    }

    private static int rchild(int i) {
        return 2 * i + 1;
    }

    private static int exact(int i) {
        return n + i - 1;
    }
}

class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /**
     * call this method to initialize reader for InputStream
     */
    static void init(InputStream input) {
        reader = new BufferedReader(
                new InputStreamReader(input));
        tokenizer = new StringTokenizer("");
    }

    /**
     * get next word
     */
    static String next() throws Exception {
        while (!tokenizer.hasMoreTokens()) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                    reader.readLine());
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws Exception {
        return Integer.parseInt(next());
    }
}


