import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * BaekJoon No.1926
	 */
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int cnt;
	
	static int[] dy = {0,1,0,-1};
	static int[] dx = {1,0,-1,0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		int big = 0;
		
		for(int y = 0; y<N; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x<M; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int y = 0; y<N; y++) {
			for(int x = 0; x<M; x++) {
				if(map[y][x] == 0 || visited[y][x]) continue;
				visited[y][x] = true;
				int size = bfs(y,x);
				big = Math.max(size, big);
			}
		}
		
		sb.append(cnt).append("\n").append(big);
		System.out.println(sb.toString());
		
	}
	
	static int bfs(int y, int x) {
		Queue<Integer[]> queue = new LinkedList<>();
		queue.offer(new Integer[] {y,x});
		
		int size = 1;
		
		while(!queue.isEmpty()) {
			
			Integer[] art = queue.poll();
			
			for(int d = 0; d<4; d++) {
				int ny = art[0] + dy[d];
				int nx = art[1] + dx[d];
				if(!valid(ny, nx)) continue;
				if(map[ny][nx] == 0) continue;
				if(visited[ny][nx]) continue;
			
				visited[ny][nx] = true;
				queue.offer(new Integer[] {ny,nx});
				size++;
			}
		}
		
		cnt++;
		return size;
		
	}
	
	static boolean valid(int dy, int dx) {
		return (dy>=0 && dy<N && dx>=0 && dx<M);
	}
}
