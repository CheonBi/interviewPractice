import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		for (int tc = 1; tc <= 10; tc++) {
			int[][] graph = new int[101][101]; //연락망인원 최대 100명
			Queue<Integer> queue = new ArrayDeque<>();
			
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			
			//연락망 그래프 입력
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i<N/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				graph[from][to] = 1;
			}
			
			sb.append("#").append(tc).append(" ").append(contact(start, queue, graph)).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
	
	static int contact(int start, Queue<Integer> queue, int[][] graph) {
		boolean[] isVisited = new boolean[101]; //방문처리 배열
		ArrayList<Integer> maxList = new ArrayList<>();
		int max = 0;
		
		//시작점 시작 및 방문처리
		queue.offer(start);
		isVisited[start] = true;
		
		while(!queue.isEmpty()) {
			int size = queue.size(); //같은 깊이씩 파악
			max = 0; //가장 큰 번호
			
			for(int b = 0; b<size; b++) { //같은 깊이에서 최대값 파악
				int cur = queue.poll();
				for(int i = 1; i<graph.length; i++) {
					
					//연락가능한 상태
					if(graph[cur][i] == 1 && !isVisited[i]) {
						queue.offer(i);
						isVisited[i] = true;
						max = Math.max(max, i);
						maxList.add(max);
					}
				}
			}
		}
		
		return maxList.get(maxList.size() -1);
		
	}

}
