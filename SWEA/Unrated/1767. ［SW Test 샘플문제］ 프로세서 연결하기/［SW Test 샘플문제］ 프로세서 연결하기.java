import java.io.*;
import java.util.*;

public class Solution {

	static int N, min, FullCore;
	static int[][] mexynos;
	static final int[][] delta = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			mexynos = new int[N][N];
			min = Integer.MAX_VALUE;
			FullCore = Integer.MIN_VALUE;

			int core = 0;

			for (int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < N; x++) {
					mexynos[y][x] = Integer.parseInt(st.nextToken());

					// 가장자리 제외 Core
					if ((y > 0 && y < N - 1 && x > 0 && x < N - 1) && mexynos[y][x] == 1) {
						core += 1;
					}
				}
			}

			// 방향 선택위한 core
			int[][] cores = new int[core][];
			int idx = 0;

			for (int y = 1; y < N - 1; y++) {
				for (int x = 1; x < N - 1; x++) {

					// 가장자리 제외 Core
					if (mexynos[y][x] == 1) {
						cores[idx++] = new int[] { y, x };
					}
				}
			}

			connect(0, 0, 0, cores);

			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

	static void connect(int depth, int sum, int coreCnt, int[][] cores) {


		if (depth == cores.length) {
			
			if(FullCore < coreCnt) {
				FullCore = coreCnt;
				min = sum;
			} else if(FullCore == coreCnt){
				min = Math.min(sum, min); 
			}
			
			return;
		}

		// 4방향 점검
		int y = cores[depth][0];
		int x = cores[depth][1];

		for (int d = 0; d < 4; d++) {
			int cableLength = 0;
			int ny = y;
			int nx = x;
			int sy = y;
			int sx = x;

			while (true) {
//				int ny = y + delta[d][0];
//				int nx = x + delta[d][1];
				ny += delta[d][0];
				nx += delta[d][1];
				
				if (!isValid(ny, nx))
					break;

				if (mexynos[ny][nx] == 1) {
					cableLength = 0;
					break;
				}

				cableLength += 1;
//				y = ny;
//				x = nx;

			}

			// 전선 연결
//			connectPower(cores[depth][0], cores[depth][1], d, cableLength);
			connectPower(sy, sx, d, cableLength);
			// 전원 연결 불가능
			if (cableLength == 0) {
				
				// 코어 연결 X
				connect(depth + 1, sum, coreCnt, cores);
			}

			else if (cableLength > 0) {
				connect(depth + 1, sum + cableLength, coreCnt + 1, cores);

				// 전선 해제
//				disconnectPower(cores[depth][0], cores[depth][1], d, cableLength);
				disconnectPower(sy, sx, d, cableLength);

			}
		}

		
	}

	static boolean isValid(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N;
	}

	static void connectPower(int y, int x, int d, int cableLength) {
		for (int i = 0; i < cableLength; i++) {
			y += delta[d][0];
			x += delta[d][1];
			
			mexynos[y][x] = 1;
		}
	}

	static void disconnectPower(int y, int x, int d, int cableLength) {
		for (int i = 0; i < cableLength; i++) {
			y += delta[d][0];
			x += delta[d][1];
			mexynos[y][x] = 0;
		}
	}

}
