import java.io.*;
import java.util.*;

public class Solution {

	static int N, B, MIN;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			MIN = Integer.MAX_VALUE;
			
			int[] members = new int[N];

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				members[i] = Integer.parseInt(st.nextToken());
			}
			
			top(0,0, members);

			sb.append("#").append(tc).append(" ").append(MIN).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}

	static void top(int depth, int sum, int[] members) {
		if (depth == N) {
		
			int n = Math.abs(B-sum);
			
			if(sum >= B && MIN > n) {
				MIN = n;
			}
			
			return;
		}
		
		top(depth + 1, sum + members[depth], members);
		top(depth + 1, sum, members);
	}
}
