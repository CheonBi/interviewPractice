import java.io.*;
import java.util.*;

public class Main{
    
    private static long A,B,C;
    
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        
        A = Long.parseLong(tokenizer.nextToken());
        B = Long.parseLong(tokenizer.nextToken());
        C = Long.parseLong(tokenizer.nextToken());

        System.out.print(pow(A,B,C));
    }
    
    //재귀
    public static long pow(long a, long b, long c){
        if(b == 1){
            return a % c;
        }
        
        long val = pow(a, b/2, c);
        
        if(b % 2 == 0){
            return (val * val) % c;
        }
        
        return (val * val % c) * a % c;
    }
}