import java.io.*;
import java.util.*;

public class Solution {

	static int N, T;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			int[] arrY = new int[N];
			int[] arrX = new int[N];
			int[] parents = new int[N];
			pq.clear();

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arrX[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arrY[i] = Integer.parseInt(st.nextToken());
			}

			double E = Double.parseDouble(br.readLine());
			

			for (int i = 0; i < N; i++) {
				for (int j = i+1; j < N; j++) {
					int y1 = arrY[i];
					int x1 = arrX[i];

					int y2 = arrY[j];
					int x2 = arrX[j];

					double weight = (Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2)) * E;
					pq.offer(new Edge(i, j, weight));
				}
			}

			Arrays.fill(parents, -1);

			double weight = 0;
			int cnt = 0;

			while(!pq.isEmpty()) {
				Edge edge = pq.poll();
				
				if (Union(edge.from, edge.to, parents)) {
					weight += edge.weight;
					if (++cnt == N - 1) {
						break;
					}
				}
			}
	
			sb.append("#").append(tc).append(" ").append(Math.round(weight)).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

	static int Find(int N, int[] parents) {
		if (parents[N] < 0) {
			return N;
		}

		return parents[N] = Find(parents[N], parents);
	}

	static boolean Union(int A, int B, int[] parents) {
		A = Find(A, parents);
		B = Find(B, parents);

		if (A == B)
			return false;

		parents[A] += parents[B];
		parents[B] = A;

		return true;

	}

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		double weight;

		public Edge(int from, int to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
	}
}
