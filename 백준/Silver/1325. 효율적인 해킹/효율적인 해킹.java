import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer>[] computers;
	static int[] reliability;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		computers = new ArrayList[N+1];
		reliability = new int[N+1];
		ArrayList<Integer> ans = new ArrayList<>();
		
		for(int i = 1; i<=N; i++) {
			computers[i] = new ArrayList<>();
		}
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int NUM = Integer.parseInt(st.nextToken());
			int trust = Integer.parseInt(st.nextToken());
			
			computers[NUM].add(trust);
		}
		
		for(int i = 1; i<=N; i++) {
			bfs(N,i);
		}
		
		int MAX = Arrays.stream(reliability).max().getAsInt();
		

		for(int i = 1; i<=N; i++) {
			if(reliability[i] == MAX) ans.add(i);
		}
		
		Collections.sort(ans);
		
		for(int a : ans) {
			System.out.print(a + " ");
		}
	}
	
	private static void bfs(int N, int computer) {
		
		visited = new boolean[N+1];
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(computer);
		visited[computer] = true;
		
		while(!queue.isEmpty()) {
			int connect = queue.poll();
			for(int relation : computers[connect]) {
				if(!visited[relation]) {
					visited[relation] = true;
					queue.add(relation);
					reliability[relation]++;
				}
			}
		}
	}

}

