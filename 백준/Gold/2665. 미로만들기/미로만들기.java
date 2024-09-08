import java.io.*;
import java.util.*;

public class Main{

	static int[][] delta = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static int[][] MAP;
	static int[][] PATH;
	static int N, result;
	static final int INF = Integer.MAX_VALUE;

	static class Node implements Comparable<Node> {
		int y;
		int x;
		int moveCnt;
		int black;

		public Node(int y, int x, int moveCnt, int black) {
			this.y = y;
			this.x = x;
			this.moveCnt = moveCnt;
			this.black = black;
		}

		@Override
		public int compareTo(Node o) {
			return this.black - o.black;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		result = 0;

		MAP = new int[N][N];
		PATH = new int[N][N];

		// 0 : 검은방 1: 흰방
		for (int y = 0; y < N; y++) {
			String line = br.readLine();
			for (int x = 0; x < N; x++) {
				MAP[y][x] = line.charAt(x) - '0';
				PATH[y][x] = INF;
			}
		}
		
		bfs();		
		System.out.println(result);
	}

	static void bfs() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		PATH[0][0] = 0;
		boolean[][] isVisited = new boolean[N][N];

		pq.offer(new Node(0, 0, 0, 0));
		isVisited[0][0] = true;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			int curY = cur.y;
			int curX = cur.x;
			int curMoveCnt = cur.moveCnt;
			int curBlack = cur.black;
			
			if(curY == N -1 && curX == N -1) {
				result = curBlack;
			}

			for (int d = 0; d < 4; d++) {
				int ny = curY + delta[d][0];
				int nx = curX + delta[d][1];

				if (!isValid(ny, nx) || isVisited[ny][nx])
					continue;

				isVisited[ny][nx] = true;
				if (MAP[ny][nx] == 1)
					pq.offer(new Node(ny, nx, curMoveCnt + 1, curBlack));
				else
					pq.offer(new Node(ny, nx, curMoveCnt + 1, curBlack + 1));

			}
		}
	}

	static boolean isValid(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N;
	}
}
