import java.io.*;
import java.util.*;

/**
 * 	BOJ No.6603 로또
 */

public class Main {
	
	static int K;
	static int[] nums;
	static int[] lotto;
	static boolean[] visited;
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			K = Integer.parseInt(st.nextToken());
			
			if(K == 0) break;
			
			nums = new int[K];
			lotto = new int[6];
			visited = new boolean[K];
			
			for(int i = 0; i<K; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			search(0, 0);
			sb.append("\n");
		}
		
		System.out.println(sb);

	}
	
	static void search(int depth, int start) {
		if(depth == 6) {
			for(int N : lotto) {
				sb.append(N).append(' ');
			}
			sb.append("\n");
			return;
		}
		
		if((K - start < 6 - depth)) {
			return;
		}
		
		for(int i = start; i<K; i++) {
			if(!visited[i]) {
				visited[i] = true; 
				lotto[depth] = nums[i];
				search(depth + 1, i+1);
				visited[i] = false;
			}
		}
	}
}
