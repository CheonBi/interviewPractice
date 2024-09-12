import java.io.*;
import java.util.*;

public class Main {

	static class Edge implements Comparable<Edge> {
		int idx;
		int weight;

		public Edge(int idx, int weight) {
			this.idx = idx;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	static int N, M, X, result;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static ArrayList<Edge>[][] edges;
	static int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 마을수, 도로수, 도착지점
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		result = 0;

		edges = new ArrayList[2][N + 1];

		// 리스트 초기화
		for (int i = 1; i <= N; i++) {
			edges[0][i] = new ArrayList<>();
			edges[1][i] = new ArrayList<>();
		}

		// 큐 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			edges[0][from].add(new Edge(to, weight));
			edges[1][to].add(new Edge(from, weight));
		}

		int[] homeToparty = Djikstra(X, 0).clone();
		int[] partyTohome = Djikstra(X, 1).clone();
		
		int result = 0;
		
		for(int i = 1; i<=N; i++) {
			 if(result < homeToparty[i] + partyTohome[i])
				 result = homeToparty[i] + partyTohome[i];
		}

		System.out.println(result);

	}

	static int[] Djikstra(int start, int type) {
		pq.clear();

		int[] distance = new int[N + 1]; // 거리 저장 배열
		boolean[] isVisited = new boolean[N + 1]; // 방문 체크 배열

		Arrays.fill(distance, INF);

		distance[start] = 0;
		pq.offer(new Edge(start, 0));

		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if (isVisited[now.idx])
				continue;
			isVisited[now.idx] = true;

			for (Edge next : edges[type][now.idx]) {
				if (distance[next.idx] > now.weight + next.weight) {
					distance[next.idx] = now.weight + next.weight;
					pq.offer(new Edge(next.idx, distance[next.idx]));
				}
			}
		}

//		System.out.println(Arrays.toString(distance));
		return distance;
	}
}
