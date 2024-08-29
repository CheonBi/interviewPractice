import java.io.*;
import java.util.*;

public class Main {

	static class Edge implements Comparable<Edge>{
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
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		//N -> 정점수  M-> 간선수
		
		int[] parents = new int[N+1];
		
		//Make set
		Arrays.fill(parents, -1);
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			pq.offer(new Edge(from, to, weight));
		}
		
		int weight = 0;
		int cnt = 0;
		
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			
			if(Union(edge.from, edge.to, parents)) {
				weight += edge.weight;
				if(++cnt == N - 1) break;
			}
		}
		
		System.out.println(weight);
	}
	
	static int Find(int N, int[] parents) {
		if(parents[N] < 0) return N;
		return parents[N] = Find(parents[N], parents);
	}
	
	static boolean Union(int x, int y, int[] parents) {
		int nx = Find(x, parents);
		int ny = Find(y, parents);
		
		if(nx == ny) return false;
		
		parents[nx] += parents[ny];
		parents[ny] = nx;
		
		return true;
	}
}
