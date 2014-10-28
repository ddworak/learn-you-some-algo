import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Dawid
 * Date: 28/10/2014
 * Time: 13:13
 */
public class WordOrderer {
    public static String orderby(String word, String order){
        Map<Character,Integer> count = new LinkedHashMap<Character, Integer>();
        for(int i = 0; i<order.length();i++){
            char c = order.charAt(i);
            if(!count.containsKey(c))count.put(c,0);
        }
        for(int i=0; i<word.length();i++){
            char c = word.charAt(i);
            Integer v = count.get(c);
            if(v==null)throw new IllegalArgumentException("Order source doesn't have all required characters.");
            count.put(c,v+1);
        }
        String result = "";
        for(Map.Entry<Character,Integer> e : count.entrySet()){
            for(int i=0;i<e.getValue();i++)result = result.concat(e.getKey().toString());
        }
        return result;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        String order = sc.nextLine();
        System.out.println(orderby(word,order));
    }
}
