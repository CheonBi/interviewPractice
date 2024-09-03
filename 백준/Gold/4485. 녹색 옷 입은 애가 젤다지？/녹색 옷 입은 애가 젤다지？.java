import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[][] MAP;
	static final int[][] delta = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int tc = 1;
		
		while (true) {
			N = Integer.parseInt(br.readLine());
			

			if (N == 0) {
				break;
			}

			MAP = new int[N][N];

			for (int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < N; x++) {
					MAP[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			int result = greenIsZelda();

			sb.append("Problem").append(" ").append(tc).append(":").append(" ").append(result).append("\n");
			tc++;
		}

		System.out.println(sb.toString());
		br.close();
	}

	static int greenIsZelda() {
		int[][] isVisited = new int[N][N];
		int[][] minLoseRupee = new int[N][N];
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				minLoseRupee[y][x] = INF;
			}
		}

		// 시작점 (0,0)
		minLoseRupee[0][0] = MAP[0][0];
		pq.offer(new int[] { 0, 0, minLoseRupee[0][0] });

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();

			int curY = cur[0];
			int curX = cur[1];
			int curRupee = cur[2];

			if (isVisited[curY][curX] == 1)
				continue;

			isVisited[curY][curX] = 1;

			if (curY == N - 1 && curX == N - 1) {
				return curRupee;
			}

			for (int d = 0; d < 4; d++) {
				int ny = curY + delta[d][0];
				int nx = curX + delta[d][1];

				if (isValid(ny, nx) && isVisited[ny][nx] == 0 && minLoseRupee[ny][nx] > curRupee + MAP[ny][nx]) {
					minLoseRupee[ny][nx] = curRupee + MAP[ny][nx];
					pq.offer(new int[] { ny, nx, minLoseRupee[ny][nx] });
				}

			}
		}
		return -1;
	}

	static boolean isValid(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N;
	}
}
