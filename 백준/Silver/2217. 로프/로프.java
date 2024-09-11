import java.util.*;
import java.io.*;
import java.math.*;

public class Main{
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        Integer[] lope = new Integer[N];
        
        for(int i = 0; i<N; i++){
            lope[i] = Integer.parseInt(br.readLine());
        }
        
        Arrays.sort(lope, Collections.reverseOrder());  //내림차순으로 정렬하기
        
        int w = 0;
        
        for(int i = 0; i<N; i++){
            w = Math.max(w, lope[i] * (i+1));
        }
        System.out.println(w);
    }
}