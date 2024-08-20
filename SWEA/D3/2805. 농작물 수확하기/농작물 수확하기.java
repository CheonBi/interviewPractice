import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];

			for (int y = 0; y < N; y++) {
				String str = br.readLine();
				for (int x = 0; x < N; x++) {
					map[y][x] = str.charAt(x) - '0';
				}
			}

			int result = 0;
			
			int start = N/2;
			int end = N/2;
			
			for(int y = 0; y<N; y++) {
				if(y < N/2) {
					for(int x = start; x<=end; x++) {
						result += map[y][x];
					}
					start--;
					end++;
				} else {
					for(int x = start; x<=end; x++) {
						result += map[y][x]; 
					}
					start++;
					end--;
				}
				
			}

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}
