import java.io.*;
import java.util.*;

public class Solution {
    static int answer;
    
    public static int solution(int[] numbers, int target) throws IOException {
        
        answer = 0;
        
        dfs(0, target, numbers);
        
        return answer;
    }
    
    static void dfs(int idx, int target, int[] arr) {
        if(idx >= arr.length) {
            int tmp = 0;
            for(int i = 0; i < arr.length; i++) {
                tmp += arr[i];
            }
            
            if(tmp == target) {
                
                answer += 1;
                return;
            }
            return;
        }
        
        
        dfs(idx + 1, target, arr);
        
        arr[idx] *= -1;
        
        dfs(idx + 1, target, arr);
    }
}