import java.io.*;
import java.util.*;

public class Main {

	static int T, N, M, K;
	static int[][] MAP;
	static final int[][] delta = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static boolean[][] isVisited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());

			M = Integer.parseInt(st.nextToken()); // 가로
			N = Integer.parseInt(st.nextToken()); // 세로
			K = Integer.parseInt(st.nextToken()); // 배추 위치 횟수

			MAP = new int[N][M];
			isVisited = new boolean[N][M];

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());

				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				MAP[y][x] = 1;
			}

			int sum = 0;
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < M; x++) {
					if (MAP[y][x] == 1 && !isVisited[y][x]) {
						sum += bfs(y, x);
					}

				}
			}

			sb.append(sum).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	static int bfs(int y, int x) {
		
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { y, x });
		isVisited[y][x] = true;

		while (!q.isEmpty()) {
			int[] now = q.poll();

			int sy = now[0];
			int sx = now[1];

			isVisited[sy][sx] = true;

			for (int d = 0; d < 4; d++) {
				int ny = sy + delta[d][0];
				int nx = sx + delta[d][1];

				if (isValid(ny, nx) && !isVisited[ny][nx] && MAP[ny][nx] == 1) {
					isVisited[ny][nx] = true;
					q.offer(new int[] {ny,nx});
				}
			}
		}
		
		return 1;
	}

	static boolean isValid(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < M;
	}
}
