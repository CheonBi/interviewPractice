import java.io.*;
import java.util.*;

/**
 * BOJ No. 15685
 * 
 * @author OGS
 *
 */
public class Main {
	static int[][] map = new int[101][101];
	static int result;

	// 방향 1, 2, 3, 4
	static int[] dy = { 0, -1, 0, 1 };
	static int[] dx = { 1, 0, -1, 0 };
	static int[] R = { 0, 1, 1 };
	static int[] C = { 1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < N; tc++) {
			st = new StringTokenizer(br.readLine());

			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			int G = Integer.parseInt(st.nextToken());

			dragon(X, Y, D, G);
		}
		
		result = 0;
			
		isSquare();

		System.out.println(result);

	}

	static void isSquare() {
		for (int y = 0; y < 100; y++) {
			for (int x = 0; x < 100; x++) {
				if (map[y][x] == 0)
					continue;

				int cnt = 0;

				for (int d = 0; d < 3; d++) {
					int ny = y + R[d];
					int nx = x + C[d];
					if (map[ny][nx] == 1 && valid(ny, nx))
						cnt++;
					else
						break;
				}

				if (cnt == 3)
					result++;
			}
		}
	}

	static void dragon(int X, int Y, int D, int G) {
		ArrayList<Integer> directions = new ArrayList<>();

		// 0세대
		// 시작점
		directions.add(D);

		// 방향설정
		while (G-- > 0) {
			for (int i = directions.size() - 1; i >= 0; i--) {
				int direction = (directions.get(i) + 1) % 4;
				directions.add(direction);
			}
		}
		
		int y = Y;
		int x = X;
		
		for (int i = 0; i < directions.size(); i++) {
			
			if(valid(y,x)) map[y][x] = 1;
			
			int delta = directions.get(i);
			y = y + dy[delta];
			x = x + dx[delta];
		}
		
		if (valid(y, x))
			map[y][x] = 1;

	}

	static boolean valid(int ny, int nx) {
		return ny >= 0 && ny <= 100 && nx >= 0 && nx <= 100;
	}
}
