/*
 * SWEA No.3124 최소 스패닝 트리
 */
import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			// V : 정점의 개수, E: 간선의 개수
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			int[] parents = new int[100001];

			for (int i = 1; i <= V; i++) {
				parents[i] = -1;
			}

			Edge[] edges = new Edge[E];

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());

				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				long weight = Long.parseLong(st.nextToken());

				edges[i] = new Edge(from, to, weight);
			}

			Arrays.sort(edges);

			long weight = 0;
			int cnt = 0;

			for (Edge edge : edges) {
				if (Union(edge.from, edge.to, parents)) {
					weight += edge.weight;
					if (++cnt == V - 1)
						break;
				}
			}

			sb.append("#").append(tc).append(" ").append(weight).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	static int Find(int N, int[] parents) {
		if (parents[N] < 0) {
			return N;
		}
		return parents[N] = Find(parents[N], parents); // 경로 압축
	}

	static boolean Union(int x, int y, int[] parents) {

		int nx = Find(x, parents);
		int ny = Find(y, parents);

		if (nx == ny)
			return false;

		parents[nx] += parents[ny]; // 집합 크기 -> 절대값이 크기
		parents[ny] = nx;

		return true;
	}

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		long weight;

		public Edge(int from, int to, long weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight);
		}
	}
}
