import java.io.*;
import java.util.*;

public class Solution{

	static int N, M, C, result, costA, costB;
	static int[][] MAP;
	static boolean[][] isUsed;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			costA = 0;
			costB = 0;
			result = 0;

			MAP = new int[N][N];
			int[] worker = new int[2];

			for (int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < N; x++) {
					MAP[y][x] = Integer.parseInt(st.nextToken());
				}
			}

			collect(0, 0, worker);
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	static void collect(int depth, int start, int[] worker) {
		// 꿀통 두개
		if (depth == 2) {

			// 겹치는지 확인
			if (worker[0] + (M - 1) >= worker[1])
				return;

			// 같은 열에 있는지 확인
			int y1 = worker[0] / N;
			int y2 = (worker[0] + (M - 1)) / N;

			int y3 = worker[1] / N;
			int y4 = (worker[1] + (M - 1)) / N;

			if (y1 != y2 || y3 != y4)
				return;

			int cost = getHoney(worker[0], worker[1]);
			result = Math.max(result, cost);
//			int[] honeyA = getHoney(worker[0]);
//			int[] honeyB = getHoney(worker[1]);

//			for (int R = 1; R <= M; R++) {
//				
//				combination(0, worker[0], R, 0, 0, honeyA, 'A');
//			}
//
//			for (int R = 1; R <= M; R++) {
//				combination(0, worker[1], R, 0, 0, honeyB, 'B');
//			}

			return;
		}

		for (int i = start; i < N * N; i++) {
			worker[depth] = i;
			collect(depth + 1, i + 1, worker);
		}
	}

	static int getHoney(int A, int B) {

		costA = 0;
		costB = 0;

		for (int R = 1; R <= M; R++) {

			combination(0, A, A + M, R, 0, 0, 'A');
		}

		for (int R = 1; R <= M; R++) {
			combination(0, B, B + M, R, 0, 0, 'B');
		}
		
		return costA + costB;

	}

	static void combination(int depth, int start, int end, int R, int sum, int cost, char type) {


		if (depth == R) {
			if (sum > C)
				return;
			if (type == 'A' && costA < cost) {
				costA = cost;
			} else if (type == 'B' && costB < cost) {
				costB = cost;
			}
			return;
		}

		for (int i = start; i < end; i++) {
			int value = MAP[i / N][i % N];
			int sale = value * value;
			combination(depth + 1, i + 1, end, R, sum + value, cost + sale, type);
		}
	}

	static boolean isValid(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N;
	}

}
