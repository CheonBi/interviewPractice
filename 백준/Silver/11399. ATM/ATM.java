import java.util.*;
import java.io.*;

public class Main{
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int sum = 0;
        int prev = 0;
        
        int n = Integer.parseInt(br.readLine());
        
        int[] timeArr = new int[1001];
        st = new StringTokenizer(br.readLine());
        
        while(n-- > 0){
            timeArr[Integer.parseInt(st.nextToken())]++;
        }
                 
        for(int i = 0; i<1001; i++){
            while(timeArr[i]-- > 0){
                sum += (i+prev);
                prev += i;
            }
        }
        
        System.out.println(sum);
    }
}