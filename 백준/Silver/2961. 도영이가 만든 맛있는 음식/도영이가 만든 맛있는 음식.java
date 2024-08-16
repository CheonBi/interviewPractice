import java.io.*;
import java.util.*;

public class Main {
	
	static int[][] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		
		arr = new int[N][2];
		int MIN = Integer.MAX_VALUE;
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int SOUR = Integer.parseInt(st.nextToken());
			int BITTER = Integer.parseInt(st.nextToken());
			arr[i] = new int[] {SOUR, BITTER};
		}
		
		
		for(int i = 1; i<(1<<N); i++) {
			int score_SOUR = 1;
			int score_BITTER = 0;
			
			for(int j = 0; j<N; j++) {
				if((i & (1 << j)) != 0) {
					score_SOUR *= arr[j][0];
					score_BITTER += arr[j][1];
				
//					System.out.print(Arrays.toString(arr[j]));
				}
			}
			
			MIN = Math.min(MIN, Math.abs(score_SOUR - score_BITTER));
//			System.out.println();
		}
		
		System.out.println(MIN);
	}
}
