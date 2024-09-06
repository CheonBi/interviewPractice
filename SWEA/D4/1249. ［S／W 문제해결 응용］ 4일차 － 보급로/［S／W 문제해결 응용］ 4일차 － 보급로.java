
import java.io.*;
import java.util.*;

public class Solution {

	static int T, N;
	static final int INF = Integer.MAX_VALUE;
	static int[][] MAP;
	static int[][] delta = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static PriorityQueue<Node> pq = new PriorityQueue<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			MAP = new int[N][N];
			pq.clear();

			for (int y = 0; y < N; y++) {
				String input = br.readLine();
				for (int x = 0; x < N; x++) {
					MAP[y][x] = input.charAt(x) - '0';
				}
			}
			
			int result = Dijkstra();
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();


	}

	static int Dijkstra() {

		boolean[][] isVisited = new boolean[N][N];
		int[][] weightMAP = new int[N][N];

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				weightMAP[y][x] = INF;
			}
		}

		// 시작
		weightMAP[0][0] = MAP[0][0];
		pq.offer(new Node(0, 0, MAP[0][0]));

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int y = node.y;
			int x = node.x;
			int w = node.weight;

			int ny, nx;
			
			if (isVisited[y][x])
				continue;

			if (y == N - 1 && x == N - 1)
				return w;

			isVisited[y][x] = true;

			for (int d = 0; d < 4; d++) {
				ny = y + delta[d][0];
				nx = x + delta[d][1];
				
				if(isValid(ny,nx)) {
					if(!isVisited[ny][nx]) {
						if(w + MAP[ny][nx] < weightMAP[ny][nx]) {
							weightMAP[ny][nx] = w + MAP[ny][nx];
							pq.offer(new Node(ny, nx, w + MAP[ny][nx]));
						}
					}
				}
			}
		}

		return -1;
	}

	static boolean isValid(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N;
	}
	

	static class Node implements Comparable<Node> {
		int y;
		int x;
		int weight;

		public Node(int y, int x, int weight) {
			this.y = y;
			this.x = x;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
}
