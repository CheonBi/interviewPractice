

import java.io.*;
import java.util.*;

public class Main {

	// x - y
	static int[][] delta = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static int[][] MAP;
	static int cnt, M, N, K;
	static List<Integer> size = new ArrayList<>();
	static boolean[][] isVisited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		MAP = new int[M][N];
		isVisited = new boolean[M][N];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());

			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			for (int y = y1; y < y2; y++) {
				for (int x = x1; x < x2; x++) {
					MAP[y][x] = 1;
				}
			}
		}


		for (int x = 0; x < M; x++) {
			for (int y = 0; y < N; y++) {
				if (MAP[x][y] == 0 && !isVisited[x][y])
					bfs(x, y);

			}
		}

		Collections.sort(size);

		System.out.println(size.size());

		for (int n : size) {
			System.out.print(n + " ");
		}
	}

	static void bfs(int x, int y) {
		int[] point = new int[] { x, y };

		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(point);
		int sum = 1;

		while (!queue.isEmpty()) {
			int[] p = queue.poll();
			int sx = p[0];
			int sy = p[1];
			isVisited[sx][sy] = true;

			for (int d = 0; d < 4; d++) {
				int nx = sx + delta[d][0];
				int ny = sy + delta[d][1];

				if (isValid(nx, ny)) {
					if (!isVisited[nx][ny] && MAP[nx][ny] == 0) {
						isVisited[nx][ny] = true;
						queue.offer(new int[] { nx, ny });
						sum += 1;
					}
				}

			}
		}

		size.add(sum);
	}

	static boolean isValid(int nx, int ny) {
		return ny >= 0 && ny < N && nx >= 0 && nx < M;
	}
}
