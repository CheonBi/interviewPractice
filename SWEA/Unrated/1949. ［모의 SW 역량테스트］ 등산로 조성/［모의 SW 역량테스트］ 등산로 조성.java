
import java.io.*;
import java.util.*;

public class Solution {

	static int N, K, result;
	static final int[][] delta = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			int high = Integer.MIN_VALUE;
			result = 0;

			int[][] MAP = new int[N][N];
			boolean[][] isVisited = new boolean[N][N];

			for (int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int x = 0; x < N; x++) {
					MAP[y][x] = Integer.parseInt(st.nextToken());
					if (high < MAP[y][x])
						high = MAP[y][x]; // 최대값
				}
			}

			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					if (MAP[y][x] != high)
						continue;

					isVisited[y][x] = true;
					dfs(y, x, high, 1, 1, MAP, isVisited);
					isVisited[y][x] = false;

				}
			}

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

	//
	static void dfs(int y, int x, int high, int length, int cnt, int[][] MAP, boolean[][] isVisited) {

		int curY = y;
		int curX = x;
		
		for (int d = 0; d < 4; d++) {
			int ny = y + delta[d][0];
			int nx = x + delta[d][1];

			if (isValid(ny, nx) && !isVisited[ny][nx]) {
				
				if(MAP[ny][nx] >= MAP[curY][curX]) {
					if (cnt == 0) {
						continue;
					} else {
						if(MAP[ny][nx] - K < MAP[curY][curX]) {
							int origin = MAP[ny][nx];
							MAP[ny][nx] = MAP[curY][curX] - 1;
							isVisited[ny][nx] = true;
							dfs(ny, nx, high, length + 1, cnt - 1, MAP, isVisited);
							isVisited[ny][nx] = false;
							MAP[ny][nx] = origin;
						} else {
							continue;
						}
					}
				} else {
					isVisited[ny][nx] = true;
					dfs(ny, nx, high, length + 1, cnt, MAP, isVisited);
					isVisited[ny][nx] = false;
				}

			}
		}

		result = Math.max(result, length);
	}

	static boolean isValid(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N;
	}
}
