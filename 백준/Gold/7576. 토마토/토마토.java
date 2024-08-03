import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N,M;
	static int[][] warehouse;
	static boolean[][] visited;
	
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
				
		warehouse = new int[N][M];
		visited = new boolean[N][M];
		
		int cnt = 0;
		for(int y = 0; y<N; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x<M; x++) {
				warehouse[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(dfs());
		
	}
	
	static int dfs() {
		Queue<int[]> queue = new LinkedList<>();
		
		int days = 0;
		int noSweet = 0;
		
		for(int y = 0; y<N; y++) {
			for(int x = 0; x<M; x++) {
				if(warehouse[y][x] == 1) {
					queue.offer(new int[] {y,x});
				} else if(warehouse[y][x] == 0) {
					noSweet++;
				}
			}
		}
		
		while(!queue.isEmpty()) {
			
			//성장할 토마토 체크
			//더이상 없으면 break
			if(noSweet == 0) break;
						
			//하루 성장
			int size = queue.size();
			for(int i = 0; i<size; i++) {
				
				int[] tomato = queue.poll();
				
				int y = tomato[0];
				int x = tomato[1];
				
				for(int d = 0; d<4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					
					if(!valid(ny, nx)) continue;
					if(warehouse[ny][nx] == -1) continue;
					
					if(warehouse[ny][nx] == 0 && !visited[ny][nx]) {
						warehouse[ny][nx] = 1;
						noSweet -= 1;
						visited[y][x] = true;
						queue.offer(new int[] {ny, nx});
					}
				}
			}			
			//성장후 최소 날짜 더하기
			days++;
		}
		
		if(noSweet >= 1) return -1;
		
		return days;
	}
	
	static boolean valid(int ny, int nx) {
		return (ny>=0 && ny<N && nx>=0 && nx<M);  
	}
}