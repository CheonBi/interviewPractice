import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();

			int[] mountains = new int[N];


			for (int i = 0; i < N; i++) {
				mountains[i] = sc.nextInt();
			}

			int up = 0, down = 0, result = 0;

			for (int i = 1; i < N; i++) {
				if (mountains[i] > mountains[i - 1] ) {
					if(down > 0) {
						result += up * down;
						up = 0;
						down = 0;
					}
					up++;
				} else if (mountains[i] < mountains[i - 1]) {
					down++;
				} 
			}
			result += up * down;

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		
		System.out.println(sb.toString());

	}
}
