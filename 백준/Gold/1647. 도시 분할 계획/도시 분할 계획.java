import java.io.*;
import java.util.*;

public class Main {

	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		Edge[] edges = new Edge[M];
		int[] parents = new int[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(from, to, weight);
		}

		// Make set
		Arrays.fill(parents, -1);

		Arrays.sort(edges);

		int weight = 0;
		int cnt = 0;
		int maxWeight = -1;

		for (int i = 0; i < edges.length; i++) {
			int from = edges[i].from;
			int to = edges[i].to;
			int w = edges[i].weight;

			if (Union(from, to, parents)) {
				weight += w;
				
				//마을을 분할할 때는 각 분리된 마을 안에 집들이 서로 연결되도록 분할해야 한다. 
				//각 분리된 마을 안에 있는 임의의 두 집 사이에 경로가 항상 존재해야 한다는 뜻이다. 
				//마을에는 집이 하나 이상 있어야 한다.
				//따라서 마지막에 가장 큰 간선을 잇지 않으면 마을을 분할하는 것과 같다.
				maxWeight = Math.max(maxWeight, w);
				if (++cnt == N - 1) 
					break;
			}
		}

		System.out.println(weight - maxWeight);
	}

	static int Find(int N, int[] parents) {
		if (parents[N] < 0)
			return N;

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
		int weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}

	}
}
