
/*
 * BOJ No.14621 나만 안되는 연애
 */

import java.io.*;
import java.util.*;

public class Main {

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
			return Integer.compare(this.weight, o.weight);
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		// 학교 및 도로개수
		// (2 ≤ N ≤ 1,000) (1 ≤ M ≤ 10,000)
		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] parents = new int[N + 1]; // vertex수 만큼 대표자 배열생성

		char[] university = new char[N + 1]; // 대학 성별

		Edge[] edges = new Edge[M]; // 간선수

		st = new StringTokenizer(br.readLine(), " ");

		for (int i = 1; i < N + 1; i++) {
			university[i] = st.nextToken().charAt(0);
		}

		
		for (int i = 0; i < M; i++) {
			// u v d
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			edges[i] = new Edge(from, to, weight);
		}

		Arrays.sort(edges); //간선 정렬
		Arrays.fill(parents, -1); //Make Set

		int cnt = 0;
		int weight = 0;

		for (int i = 0; i < edges.length; i++) {
			int from = edges[i].from;
			int to = edges[i].to;
			int w = edges[i].weight;

			// 대표자가 다르고 (사이클이 안되고) 성별이 다른 대학교일때 합집합 연산
			if (Find(from, parents) != Find(to, parents) && university[from] != university[to]) {
				weight += w;
				++cnt;
				Union(from, to, parents);
			}
		}

		System.out.println(cnt == N - 1 ? weight : -1);
	}

	static int Find(int N, int[] parents) {
		if (parents[N] < 0) {
			return N;
		}
		return parents[N] = Find(parents[N], parents);
	}

	static void Union(int x, int y, int[] parents) {
		x = Find(x, parents);
		y = Find(y, parents);

		if (x == y)
			return;

		parents[x] += parents[y];
		parents[y] = x;
	}
}
