import java.io.*;
import java.util.*;

public class Main {

	static int[][] delta = new int[][] { { 0, -1 }, { -1, 0 }, { 0, 1 }, {1, 0} };

	static int N, M, MAX;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		MAX = Integer.MIN_VALUE;

		int[][] wood = new int[N][M];
		boolean[][] isUsed = new boolean[N][M];

		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < M; x++) {
				wood[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		tracking(0, 0, wood, isUsed);

		System.out.println(MAX);

	}

	static void tracking(int depth, int power, int[][] wood, boolean[][] isUsed) {
		if (depth == N * M) {
//			for(int y = 0; y<N; y++) {
//				System.out.println(Arrays.toString(isUsed[y]));
//			}
//			System.out.println(power);
//			System.out.println();
			if (MAX < power)
				MAX = power;
			return;
		}

		int y = depth / M;
		int x = depth % M;

		if (!isUsed[y][x]) {
			

			for (int i = 0; i < 4; i++) {

				int d1 = (i % 4);
				int d2 = (i + 1) % 4;

				int ny1 = y + delta[d1][0];
				int nx1 = x + delta[d1][1];

				int ny2 = y + delta[d2][0];
				int nx2 = x + delta[d2][1];

				if (isValid(ny1, nx1) && isValid(ny2, nx2) && !isUsed[ny1][nx1] && !isUsed[ny2][nx2]) {
					isUsed[y][x] = true;
					isUsed[ny1][nx1] = true;
					isUsed[ny2][nx2] = true;

					int center = wood[y][x] * 2;
					int side1 = wood[ny1][nx1];
					int side2 = wood[ny2][nx2];

					tracking(depth + 1, power + center + side1 + side2, wood, isUsed);

					isUsed[y][x] = false;
					isUsed[ny1][nx1] = false;
					isUsed[ny2][nx2] = false;
				}
			}

			
		}

		// 부메랑 안만들고 가는경우
		tracking(depth + 1, power, wood, isUsed);
	}

	static boolean isValid(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < M;
	}
}
