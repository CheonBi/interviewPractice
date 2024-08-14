import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[][] raw = new int[N][N];
			int[][] prev = new int[N + 1][N + 1];

			int max = 0;

			for (int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < N; x++) {
					raw[y][x] = Integer.parseInt(st.nextToken());
				}
			}

			// 누적합 부분
			for (int y = 1; y < N + 1; y++) {
				for (int x = 1; x < N + 1; x++) {
					prev[y][x] = raw[y - 1][x - 1] + prev[y][x - 1] + prev[y - 1][x] - prev[y - 1][x - 1];
				}
			}

			
			//M의 크기에 따라 반복횟수가 달라진다
			//M == 2 일때 N은 N-M의 값까지 반복 (N-M 좌표 포함)
			for (int y = 0; y <= N - M; y++) {
				for (int x = 0; x <= N - M; x++) {
					int sum = prev[M + y][M + x] - prev[M + y][x] - prev[y][M + x] + prev[y][x];
					max = Math.max(sum, max);
				}
			}

			sb.append("#").append(tc).append(" ").append(max).append("\n");
		}

		System.out.println(sb);
	}
}
