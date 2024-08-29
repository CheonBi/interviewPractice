/*
 * SWEA No. 1251 하나로
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

			// 섬의 개수
			int N = Integer.parseInt(br.readLine());

			ArrayList<long[]> island = new ArrayList<>();

			// Set graph
			StringTokenizer Xpoint = new StringTokenizer(br.readLine());
			StringTokenizer Ypoint = new StringTokenizer(br.readLine());
			double E = Double.parseDouble(br.readLine());

			for (int i = 0; i < N; i++) {

				long x = Long.parseLong(Xpoint.nextToken());
				long y = Long.parseLong(Ypoint.nextToken());

				island.add(new long[] { y, x });
			}

			PriorityQueue<Edge> pq = new PriorityQueue<>();

			for (int from = 0; from < N; from++) {
				long[] A = island.get(from);

				for (int to = from + 1; to < N; to++) {
					long[] B = island.get(to);
					long L = getDistance(A, B);

					pq.offer(new Edge(from, to, L));
				}
			}

			int[] parents = new int[N];

			// makeset
			for (int i = 0; i < parents.length; i++) {
				parents[i] = -1;
			}

			long weight = 0;
			int cnt = 0;

			while (!pq.isEmpty()) {
				Edge edge = pq.poll();

				if (Union(edge.start, edge.end, parents)) {
					weight += edge.weight;
					if (++cnt == N - 1)
						break;
				}

			}

			sb.append("#").append(tc).append(" ").append(Math.round(weight * E)).append("\n");
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

		if (nx == ny) {
			return false;
		}

		parents[ny] = nx;

		return true;
	}

	static long getDistance(long[] A, long[] B) {
		long x1 = A[1];
		long y1 = A[0];

		long x2 = B[1];
		long y2 = B[0];

		return ((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2));
	}

	static class Edge implements Comparable<Edge> {
		int start;
		int end;
		long weight;

		public Edge(int start, int end, long weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight);
		}
	}
}
