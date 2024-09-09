import java.io.*;
import java.util.*;

public class Main {

	static int N, M, R, T, result;
	static final int INF = Integer.MAX_VALUE;
	static ArrayList<Edge>[] edges;
	static int[] items;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		result = 0;
		
		edges = new ArrayList[N+1];
		items = new int[N+1];
		
		//아이템 개수
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=N; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}
		
		
		//간선리스트 
		for(int i = 1; i<=N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for(int i = 0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			
			edges[a].add(new Edge(b, l));
			edges[b].add(new Edge(a, l));
		}
		
		//각 노드마다 실행
		for(int i = 1; i<=N; i++) {
			int sum = dijkstra(i);
			result = Math.max(result, sum);
		}
		
		System.out.println(result);

	}		
	
	static int dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int[] distance = new int[N+1];
		boolean[] isVisited = new boolean[N+1];
		int sum = 0;
		
		Arrays.fill(distance, INF);
		
		distance[start] = 0;
		pq.offer(new Edge(start, 0));
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			int curNodeIdx = cur.idx;
			int curWeight = cur.cost;
			
			if(isVisited[curNodeIdx]) continue;
			isVisited[curNodeIdx] = true;
			
			for(Edge nextNode : edges[curNodeIdx]) {
				int nextNodeIdx = nextNode.idx;
				int nextWeight = nextNode.cost;
				
				if(distance[nextNodeIdx] > curWeight + nextWeight) {
					distance[nextNodeIdx] = curWeight + nextWeight;
					pq.offer(new Edge(nextNodeIdx, distance[nextNodeIdx]));
				}
			}
		}
		
		for(int i = 1; i<=N; i++) {
			if(distance[i] <= M) {
				sum += items[i];
			}
		}
		
		
		return sum;
	}
	


	static class Edge implements Comparable<Edge>{
		int idx;
		int cost;
		
		public Edge(int next, int cost) {
			this.idx = next;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
		
	}
}
