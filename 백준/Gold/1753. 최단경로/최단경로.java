import java.io.*;
import java.util.*;

public class Main {

	static class Node implements Comparable<Node> {
		int next;
		int w;

		public Node(int next, int w) {
			this.next = next;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}

	}

	static final int INF = Integer.MAX_VALUE;
	static int[][] delta = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static int V, E;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		// 입력
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		// 방향 그래프
		ArrayList<Node>[] adjList = new ArrayList[V + 1];
		int[] minPath= new int[V + 1];

		Arrays.fill(minPath, INF);
		
		for (int i = 0; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		int start = Integer.parseInt(br.readLine());

		// 간선 그리기
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjList[from].add(new Node(to, w));

		}

		// 모든 정점에 대한 최단거리를 구해야됨
		Dijkstra(adjList, minPath, start);

		for(int i = 1; i<=V; i++) {
			if(minPath[i] == INF) sb.append("INF");
			else sb.append(minPath[i]);
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();

	}

	static void Dijkstra(ArrayList<Node>[] adjList, int[] minPath, int start) {
		boolean[] isVisited = new boolean[V + 1];
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		minPath[start] = 0;
		
		// 시작점
		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			int next = cur.next;
			int w = cur.w;

			if (isVisited[next])
				continue;

			isVisited[next] = true;
			
			for(Node node : adjList[next]) {
				if(minPath[node.next] > node.w + minPath[next]) {
					minPath[node.next] = minPath[next] + node.w;
					pq.offer(new Node(node.next, minPath[node.next]));
				}
			}
		}
	}

	static boolean isValid(int nextFrom, int nextTo) {
		return nextFrom >= 0 && nextFrom < V && nextTo >= 0 && nextTo < V;
	}
}
