import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
	/**
	 * BaekJoon No.10026
	 */
	static int N;
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	
	static char[][] ART;
	static char[][] BLINDART;
	static boolean[][] visited;
	static boolean[][] visitedBlind;
	
	static int blind = 0;
	static int noblind = 0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		
		ART = new char[N][N];
		BLINDART = new char[N][N];
		
		visited = new boolean[N][N];
		visitedBlind = new boolean[N][N]; 
		
		//clone으로 깊은 복사
		for(int y = 0; y<N; y++) {
			char[] rgb = br.readLine().toCharArray().clone();
			ART[y] = rgb.clone();
			BLINDART[y] = rgb.clone();
		}
		
		for(int y = 0; y<N; y++) {
			for(int x = 0; x<N; x++) {
				if(BLINDART[y][x] == 'G') {
					BLINDART[y][x] = 'R';
				}
			}
		}
		
//		for(int y = 0; y<N; y++) {
//			for(int x = 0; x<N; x++) {
//				System.out.print(ART[y][x]);
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		
		for(int y = 0; y<N; y++) {
			for(int x = 0; x<N; x++) {
				if(!visited[y][x]) {
					
					visited[y][x] = true;
					bfs(y,x);
				}
			}
		}
		
		for(int y = 0; y<N; y++) {
			for(int x = 0; x<N; x++) {
				if(!visitedBlind[y][x]) {
					visitedBlind[y][x] = true;
					bfsBlind(y, x);
				}
			}
		}
		
		sb.append(noblind).append(' ').append(blind);
		
		System.out.println(sb.toString());
	}
	
	static void bfs(int y, int x) {
		ArrayDeque<int[]> deque = new ArrayDeque<>();
		deque.offer(new int[] {y, x});
		
		while(!deque.isEmpty()) {
			int[] rgb = deque.poll();
			
			int colorY = rgb[0];
			int colorX = rgb[1];
			
			for(int d = 0; d<4; d++) {
				int ny = colorY + dy[d];
				int nx = colorX + dx[d];
				
				if(!valid(ny, nx)) continue;
				if(!visited[ny][nx] && ART[ny][nx] == ART[y][x]) {
					
					visited[ny][nx] = true;
					deque.offer(new int[] {ny,nx});
				}
			}
		}
		noblind++;

	}
	
	static void bfsBlind(int y, int x) {
		//색맹
		ArrayDeque<int[]> dequeblind = new ArrayDeque<>();
		dequeblind.offer(new int[] {y, x});
		
		visitedBlind[y][x] = true;
		
		while(!dequeblind.isEmpty()) {
			int[] rgb = dequeblind.poll();
			
			int colorY = rgb[0];
			int colorX = rgb[1];
			
			for(int d = 0; d<4; d++) {
				int ny = colorY + dy[d];
				int nx = colorX + dx[d];
				
				if(!valid(ny, nx)) continue;
				if(!visitedBlind[ny][nx] && BLINDART[ny][nx] == BLINDART[y][x]) {
					visitedBlind[ny][nx] = true;
					dequeblind.offer(new int[] {ny,nx});
				}
			}
		}
		blind++;
	}
	

	
	static boolean valid(int ny, int nx) {
		return (ny>=0 && ny<N && nx>=0 && nx<N);
	}
	
}
