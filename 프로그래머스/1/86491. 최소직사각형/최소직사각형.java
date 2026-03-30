import java.io.*;
import java.util.*;

public class Solution {
    public static int solution(int[][] sizes) {
        
        int result = 0;
        
        int w = Integer.MIN_VALUE;
        int h = Integer.MIN_VALUE;
        
        for(int [] size : sizes) {
            w = Math.max(w, Math.max(size[0], size[1]));
            h = Math.max(h, Math.min(size[0], size[1]));
        }
        
        
        return w * h;
    }
}