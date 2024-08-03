import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	static int M, N, H;
	static int[][][] warehouse;

	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dz = { 1, -1 };
    
	static ArrayDeque<int[]> deque = new ArrayDeque<>();
	static int noSweetTomato = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		warehouse = new int[H][N][M];

		for (int z = 0; z < H; z++) {
			for (int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < M; x++) {
					warehouse[z][y][x] = Integer.parseInt(st.nextToken());
					if (warehouse[z][y][x] == 1) {
						deque.offer(new int[] { z, y, x });
					} else if (warehouse[z][y][x] == 0) {
						noSweetTomato++;
					}
				}
			}
		}

		System.out.println(dfs());

	}

	static int dfs() {
		int maxDay = 0;
		
		while (!deque.isEmpty()) {
			if (noSweetTomato == 0)
				break;

			for (int i = 0, size = deque.size(); i < size; i++) {
				int[] tomato = deque.poll();

				int z = tomato[0];
				int y = tomato[1];
				int x = tomato[2];

				// 위, 아래
				for (int d = 0; d < 2; d++) {
					int nz = z + dz[d];
					if (valid(nz, y, x) && warehouse[nz][y][x] == 0) {
						warehouse[nz][y][x] = 1;
						noSweetTomato -= 1;
						deque.offer(new int[] { nz, y, x });
					}
				}
                
                //4방향
				for (int d = 0; d < 4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					if (valid(z, ny, nx) && warehouse[z][ny][nx] == 0) {
						warehouse[z][ny][nx] = 1;
						noSweetTomato -= 1;
						deque.offer(new int[] { z, ny, nx });
					}
				}
			}
			maxDay++;
		}
		
		if(noSweetTomato >= 1) return -1;

		return maxDay;
	}

	static boolean valid(int nz, int ny, int nx) {
		return (nz >= 0 && nz < H && ny >= 0 && ny < N && nx >= 0 && nx < M);
	}
}
