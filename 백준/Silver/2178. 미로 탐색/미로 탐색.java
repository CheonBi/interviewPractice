import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N,M;
	
	static int[] dy = {1, 0, -1, 0};
	static int[] dx = {0, 1 , 0, -1};
	
	static int[][] MAZE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		MAZE = new int[N][M];
		
		for(int y = 0; y<N; y++) {
			String s = br.readLine();
			for(int x = 0; x<M; x++) {
				MAZE[y][x] = s.charAt(x) - '0';
			}
		}
		
		System.out.print(bfs(0,0));
		
	}
	
	static int bfs(int sy, int sx) {
		Queue<int[]> queue = new LinkedList<>();
		
		queue.offer(new int[] {sy,sx});
		
		while(!queue.isEmpty()) {
			
			int[] maze = queue.poll();
			int y = maze[0];
			int x = maze[1];
			
			for(int d = 0; d<4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				
				if(!valid(ny,nx)) continue;
				if(MAZE[ny][nx] == 0) continue;
				
				if(MAZE[ny][nx] == 1) {
					MAZE[ny][nx] = MAZE[y][x] + 1;
					queue.offer(new int[] {ny,nx});
				}
			}
			
//			for(int i = 0; i<N; i++) {
//				for(int j = 0; j<M; j++) {
//					System.out.print(MAZE[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println();	
		}
		return MAZE[N-1][M-1];
	}

	private static boolean valid(int ny, int nx) {
		return (ny >=0 && ny<N && nx >=0 && nx<M);
	}
}
