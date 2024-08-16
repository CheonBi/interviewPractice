import java.io.*;
import java.util.*;

public class Solution {

	static boolean[] burger;
	static int[][] bad;
	static int N, M, result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			burger = new boolean[N];
			result = 0;

			if(M != 0) {
				bad = new int[M][2];
				for (int i = 0; i < M; i++) {
					st = new StringTokenizer(br.readLine());
					int a = Integer.parseInt(st.nextToken()) -1;
					int b = Integer.parseInt(st.nextToken()) -1;
							
					bad[i] = new int[] {a,b};
					
				} 
			} else bad = null;

			BugerKing(0);

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		
		System.out.println(sb);

	}

	static void BugerKing(int depth) {
		if (depth == N) {
			if(bad != null) {
				for(int i = 0; i<bad.length; i++) {
					int a = bad[i][0];
					int b = bad[i][1];
					if(burger[a] && burger[b]) return;
				}
			}
			result++;
			return;
		}
		
		burger[depth] = true;
		BugerKing(depth+1);
		
		burger[depth] = false;
		BugerKing(depth+1);
	}

}
