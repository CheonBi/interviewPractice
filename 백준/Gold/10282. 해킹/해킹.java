import java.io.*;
import java.util.*;

public class Main {

	static int T, N, D, C;
	static final int INF = Integer.MAX_VALUE;
	static ArrayList<Edge>[] edges;

	public static void main(String[] args) throws Exception  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for(int tc=0; tc<T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			int[] dist = new int[N+1];
			boolean[] isVisited = new boolean[N+1];
			edges = new ArrayList[N+1];
			
			for(int i = 1; i<=N; i++) {
				edges[i] = new ArrayList<>();
			}
			
			for(int i = 0; i<D; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				edges[b].add(new Edge(a, s));
			}
			
			Arrays.fill(dist, INF);
			dist[C] = 0;
			
			Dijkstra(dist, isVisited);
			
			int cnt = 0;
			int weight = 0;
			
			for(int d : dist) {
				if(d != INF) {
					cnt+=1;
					weight = Math.max(weight, d);
				}
			}
			
			sb.append(cnt).append(" ").append(weight).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void Dijkstra(int[] dist, boolean[] isVisited) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(C, 0));
		
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			int cur = edge.idx;
			int w = edge.weight;
			
			if(isVisited[cur]) continue;
			isVisited[cur] = true;
			
			for(Edge next : edges[cur]) {
				if(dist[next.idx] > w + next.weight) {
					dist[next.idx] = w + next.weight;
					pq.offer(new Edge(next.idx , dist[next.idx]));
				}
			}
			
		}
	}
	
	static class Edge implements Comparable<Edge>{
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
}
