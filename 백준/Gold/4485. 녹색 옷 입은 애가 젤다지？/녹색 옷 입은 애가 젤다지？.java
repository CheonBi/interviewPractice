import java.io.*;
import java.util.*;

class Main {

	static int N;
	static int[][] MAP;
	static final int[][] delta = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	static final int INF = Integer.MAX_VALUE;

	static class Node implements Comparable<Node>{
		int y;
		int x;
		int rupee;
		
		public Node(int y, int x, int rupee) {
			this.y = y;
			this.x = x;
			this.rupee = rupee;
		}

		@Override
		public int compareTo(Node o) {
			return this.rupee - o.rupee;
		}
	}

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
		boolean[][] isVisited = new boolean[N][N];
		int[][] minLoseRupee = new int[N][N];
		PriorityQueue<Node> pq = new PriorityQueue<>();

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				minLoseRupee[y][x] = INF;
			}
		}

		// 시작점 (0,0)
		minLoseRupee[0][0] = MAP[0][0];
		pq.offer(new Node(0, 0, MAP[0][0]));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			int curY = cur.y;
			int curX = cur.x;
			int curRupee = cur.rupee;

			if (isVisited[curY][curX])
				continue;

			isVisited[curY][curX] = true;

			if (curY == N - 1 && curX == N - 1) {
				return curRupee;
			}

			for (int d = 0; d < 4; d++) {
				int ny = curY + delta[d][0];
				int nx = curX + delta[d][1];

				if (isValid(ny, nx) && !isVisited[ny][nx] && minLoseRupee[ny][nx] > curRupee + MAP[ny][nx]) {
					minLoseRupee[ny][nx] = curRupee + MAP[ny][nx];
					pq.offer(new Node(ny, nx, minLoseRupee[ny][nx]));
				}

			}
		}
		return -1;
	}

	static boolean isValid(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N;
	}
}
