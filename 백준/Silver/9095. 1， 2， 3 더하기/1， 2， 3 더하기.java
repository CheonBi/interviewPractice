import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] dp = new int[10];
        
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 4;
        
        for(int i = 0; i<T; i++){
            int N = Integer.parseInt(br.readLine());
            for(int j = 3; j<N; j++){
                dp[j] = dp[j-3] + dp[j-2] + dp[j-1];
            }
            System.out.println(dp[N-1]);
        }
    }
}