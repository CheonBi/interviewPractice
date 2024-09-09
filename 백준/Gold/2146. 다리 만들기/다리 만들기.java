import java.io.*;
import java.util.*;

public class Main {

	static int N, MIN;
	static int[][] MAP;
	static int[][] delta = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static boolean[][] isVisited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		MAP = new int[N][N];
		isVisited = new boolean[N][N];
		MIN = Integer.MAX_VALUE;

		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < N; x++) {
				MAP[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		int idx = 2;
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (MAP[y][x] == 1) {
					isVisited[y][x] = true;
					MAP[y][x] = idx;
					DFS(y, x, idx);
					idx++;
				}

				if (MAP[y][x] != 0) {
					BFS(y, x, MAP[y][x]);
				}
			}
		}

		System.out.println(MIN);

	}

	static void DFS(int y, int x, int idx) {

		for (int d = 0; d < 4; d++) {
			int ny = y + delta[d][0];
			int nx = x + delta[d][1];

			if (!isValid(ny, nx) || isVisited[ny][nx])
				continue;

			if (MAP[ny][nx] == 1) {
				isVisited[ny][nx] = true;
				MAP[ny][nx] = idx;
				DFS(ny, nx, idx);
			}
		}
	}

	static void BFS(int y, int x, int idx) {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] isCreated = new boolean[N][N];
		q.offer(new int[] { y, x, 0 });

		while (!q.isEmpty()) {
			int[] node = q.poll();
			int sy = node[0];
			int sx = node[1];
			int cost = node[2];

			int ny, nx;

			if (cost >= MIN)
				return;

			for (int d = 0; d < 4; d++) {
				ny = sy + delta[d][0];
				nx = sx + delta[d][1];
				if (isValid(ny, nx) && !isCreated[ny][nx]) {

					if (MAP[ny][nx] == 0) { // 바다일때
						isCreated[ny][nx] = true;
						q.offer(new int[] { ny, nx, cost + 1 });
					} else if (MAP[ny][nx] != idx) {
						MIN = Math.min(MIN, cost);
					}

				}
			}
		}
	}

	static boolean isValid(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N;
	}
}
