import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	/**
	 * BOJ No. 4179 - 불!
	 */
	static int R, C;
	static char[][] map;
	static boolean[][] inferno;
	static boolean[][] jihoon;
	
	static Queue<int[]> jh = new ArrayDeque<>();
	static Queue<int[]> fire = new ArrayDeque<>();

	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];

		inferno = new boolean[R][C];
		jihoon = new boolean[R][C];

		for (int y = 0; y < R; y++) {
			String row = br.readLine();
			for(int x = 0; x < C; x++) {
				char ch = row.charAt(x);
				map[y][x] = ch;
				
				if(ch == 'J') {
					map[y][x] = '.';
					jh.offer(new int[] {y,x});
					jihoon[y][x] = true;
				} else if(ch == 'F') {
					fire.offer(new int[] {y,x});
					inferno[y][x] = true;
				}				
			}
		}		

		bfs();
        System.out.println("IMPOSSIBLE");
	}

	private static void bfs() {
		int time = 0;
		while(!jh.isEmpty()) {
			int jhSize = jh.size();
			int fireSize = fire.size();
			
			
			for(int i = 0; i<fireSize; i++) {
				int y = fire.peek()[0];
				int x = fire.peek()[1];
				fire.poll();
				
				for(int d = 0; d<4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					
					if(!valid(ny, nx) || map[ny][nx] == '#' || inferno[ny][nx]) continue;
					inferno[ny][nx] = true;
					map[ny][nx] = 'F';
					fire.offer(new int[] {ny,nx});
				}
			}
			
			for(int i = 0; i<jhSize; i++) {
				int y = jh.peek()[0];
				int x = jh.peek()[1];
				jh.poll();
				
				for(int d = 0; d<4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					
					if(!valid(ny,nx)) {
                        System.out.println(++time);
						System.exit(0);
					}
					
					if(map[ny][nx] != '.' || jihoon[ny][nx]) continue;
					jihoon[ny][nx] = true;
					jh.offer(new int[] {ny,nx});                  
				}
			}
			time++;
		}
	}


	private static boolean valid(int ny, int nx) {
		return ny >= 0 && ny < R && nx >= 0 && nx < C;
	}
}
