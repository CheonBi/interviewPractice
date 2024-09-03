import java.io.*;
import java.util.*;

public class Solution {

	static int N, M, result;
	static List<int[]> homes = new ArrayList<>();
	static int[][] MAP;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			result = 0;

			MAP = new int[N][N];
			homes.clear();

			for (int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < N; x++) {
					MAP[y][x] = Integer.parseInt(st.nextToken());
					if (MAP[y][x] == 1)
						homes.add(new int[] { y, x });
				}
			}

			for (int K = 1; K <= N + 1; K++) {
				for (int y = 0; y < N; y++) {
					for (int x = 0; x < N; x++) {
						Manhattan(y, x, K);
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	/*
	 * a->y b->x
	 */
	static void Manhattan(int y, int x, int K) {
		int cnt = 0;
		for (int[] h : homes) {
			int hy = h[0];
			int hx = h[1];

			int ManhattanY = Math.abs(y - hy);
			int ManhattanX = Math.abs(x - hx);

			if (ManhattanX + ManhattanY < K)
				cnt++;
		}

		int cost = (K * K) + ((K - 1) * (K - 1));

		if (cost <= cnt * M && result < cnt) {
			result = cnt;
		}
	}

}
