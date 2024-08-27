import java.io.*;
import java.util.*;

public class Solution{
	static int N, result, dessert;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			result = -1;

			int[][] MAP = new int[N][N];

			for (int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < N; x++) {
					MAP[y][x] = Integer.parseInt(st.nextToken());
				}
			}

			// DFS
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					boolean[][] isVisited = new boolean[N][N];
					boolean[] dessert = new boolean[101];
					
					//시작점은 미리 처리해줌 
					isVisited[y][x] = true;
					dessert[MAP[y][x]] = true;
					
					dessertTour(MAP, new int[] { y, x }, new int[] { y, x }, 0, 1, isVisited, dessert);
				}
			}
            
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	static void dessertTour(int[][] MAP, int[] start, int[] cafe, int cur, int cnt, boolean[][] isVisited,
			boolean[] dessert) {
		
		int[][] delta = { { -1, 1 }, { -1, -1 }, { 1, -1 }, { 1, 1 } };

		//한 방향으로만 돌게 설정
		for (int d = cur; d < 4; d++) {
			int ny = cafe[0] + delta[d][0];
			int nx = cafe[1] + delta[d][1];
			
			//다음 진행방향이 시작점이라면 종료조건 시작
			if (start[0] == ny && start[1] == nx && cnt > 2) {
				if (result < cnt)
					result = cnt;
				return;
			}

			//범위 안이거나 먹었던 디저트 종류이거나 방문한 곳이 아닐때 
			if (isValid(ny, nx) && !dessert[MAP[ny][nx]] && !isVisited[ny][nx]) {
				isVisited[ny][nx] = true;
				dessert[MAP[ny][nx]] = true;

				dessertTour(MAP, start, new int[] { ny, nx }, d, cnt + 1, isVisited, dessert);

				isVisited[ny][nx] = false;
				dessert[MAP[ny][nx]] = false;
			}
		}
	}

	static boolean isValid(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N;
	}

}
