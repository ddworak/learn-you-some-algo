/**
 * nuk, 08.04.15.
 */
public class BitFiddle {
    public static int add(int a, int b) {
        int inter = a;
        int temp;
        while (b != 0) {
            temp = inter;
            inter = inter ^ b; //simple sum (no carry)
            b = (temp & b) << 1; //carry
        }
        return inter;
    }

    public static void main(String[] args) {
        System.out.println(add(8, 2));
    }
}
