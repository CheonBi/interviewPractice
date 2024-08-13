import java.io.*;
import java.util.*;

public class Solution {
	/**
	 * SWEA 1954. 달팽이 숫자
	 */
	// 우 -> 하 -> 좌 -> 상
	static int[][] derivative = { { 0, 1 }, { 1, 0 }, { 0, -1}, { -1, 0 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] snail = new int[N][N];
			boolean[][] visited = new boolean[N][N];

			int y = 0, x = 0, d = 0;

			for (int i = 1; i <= N * N; i++) {
				if (!visited[y][x]) {
					snail[y][x] = i;
					visited[y][x] = true;
				}

				int ny = y + derivative[d % 4][0];
				int nx = x + derivative[d % 4][1];

				if (!valid(ny, nx, N) || visited[ny][nx]) {
					d++;
					ny = y + derivative[d % 4][0];
					nx = x + derivative[d % 4][1];
				}
				
				y = ny;
				x = nx;
			}
			
			sb.append("#").append(tc).append("\n");
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					sb.append(snail[r][c]).append(" ");
				}
				sb.append("\n");
			}
		}
		System.out.print(sb);
	}

	static boolean valid(int ny, int nx, int N) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N;
	}
}
