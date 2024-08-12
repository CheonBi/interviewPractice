import java.io.*;
import java.util.*;

public class Solution {
	
	static final int[] dy = {-1,1,0,0};
	static final int[] dx = {0,0,-1,1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			int ans = -1;
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			boolean[][] visited = new boolean[N+2*K][M+2*K];
			PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]) * -1); // 최소 힙 , 
			
			//BFS + PriorityQueue
			for(int y = K+0; y<K+N; y++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int x = K+0; x<K+M; x++) {
					int LIFE = Integer.parseInt(st.nextToken());
					if(LIFE > 0) {
						visited[y][x] = true;
						pq.offer(new int[] {y, x, LIFE, LIFE * 2}); //y, x, life, life*2
					}
				}
			}
			
			//총 K시간동안
			for(int k=1; k<=K; k++) {
				//살아있는 세포들(활성화 + 비활성화) 관리큐
				ArrayDeque<int[]> queue = new ArrayDeque<>();
				
				//배양큐에 있는 세포들 배양
				while(!pq.isEmpty()) {
					int[] cell = pq.poll();
					
					cell[3]--;
					
					if(cell[2] > cell[3]) {//번식, 활성화 비활성화 체크
						for(int d = 0; d<4; d++) { // 4방향
							int ny = cell[0] + dy[d];
							int nx = cell[1] + dx[d];
							if(!visited[ny][nx]) {
								visited[ny][nx] = true;
								queue.offer(new int[] {ny, nx, cell[2], cell[2] * 2});
							}
						}
					}
					
					//죽은상태 체크
					//죽지 않았다면 관리 Queue에 등록
					if(cell[3] != 0) queue.offer(cell);  
				}
				//관리큐에서 배양큐로 등록
				while(!queue.isEmpty()) pq.offer(queue.poll());
			}
			sb.append("#").append(tc).append(" ").append(pq.size()).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}

}
