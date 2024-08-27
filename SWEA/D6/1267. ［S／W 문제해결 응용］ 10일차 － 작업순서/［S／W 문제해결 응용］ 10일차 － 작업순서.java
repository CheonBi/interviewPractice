import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		for(int tc = 1; tc<=10; tc++) {
			
			st = new StringTokenizer(br.readLine());
			
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
			
			for(int i = 0; i<V+1; i++) {
				graph.add(new ArrayList<Integer>());
			}
			
			int[] NodeTable = new int[V+1];
			
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i<E; i++) {
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				
				graph.get(A).add(B);
				NodeTable[B]++;
			}
			
			sb.append("#").append(tc).append(" ").append(BFS(NodeTable, graph)).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	static String BFS(int[] NodeTable, ArrayList<ArrayList<Integer>> graph) {
		Queue<Integer> queue = new ArrayDeque<>();
		StringBuilder order = new StringBuilder();
		
		
		for(int i = 1; i<NodeTable.length; i++) {
			if(NodeTable[i] == 0) {
				queue.offer(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int Node = queue.poll();
			
			order.append(Node).append(" ");
			
			ArrayList<Integer> neighbor = graph.get(Node);
			
			for(int i = 0; i<neighbor.size(); i++) {
				int next = neighbor.get(i);
				
				NodeTable[next]--;
				
				if(NodeTable[next] == 0) {
					queue.offer(next);
				}
			}
		}
		
		return order.toString();
	}
}
