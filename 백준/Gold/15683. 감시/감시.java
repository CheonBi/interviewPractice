/*
 * BOJ No.15683 감시
 */

import java.io.*;
import java.util.*;

public class Main {

	static int N, M, MIN;
	static int[][] ROOM;
	static int[][] delta = new int[][] { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } }; 
    // 우 - 상 - 좌 - 하
	// 1번 우 - 상 - 좌 - 하 : 0 1 2 3
	// 2번 우-좌, 상-하: 0-2 1-3
	// 3번 우-상, 좌-상, 우-하, 좌-하: 0-1 2-1 0-3 2-3
	// 4번 우 - 상 - 좌 , 좌 상 하, 우 좌 하, 우 상 하 : 0 1 2 , 1 2 3, 0 2 3, 0 1 3

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		MIN = Integer.MAX_VALUE;

		List<int[]> CCTV = new ArrayList<>(); // CCTV의 최대 개수는 8개를 넘지 않는다.
		ROOM = new int[N][M];

		// ROOM
		// 0 빈칸 , 6 벽, 1~5 CCTV이면서 종류도 표현
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < M; x++) {

				ROOM[y][x] = Integer.parseInt(st.nextToken());

				// CCTV 담기
				if (ROOM[y][x] >= 1 && ROOM[y][x] <= 5) {

					// y - x - 타입
					CCTV.add(new int[] { y, x, ROOM[y][x] });
				}
			}
		}

		oversight(0, CCTV);

		System.out.println(MIN);
	}

	static void oversight(int depth, List<int[]> CCTV) {

		if (depth == CCTV.size()) {
			int result = 0;
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < M; x++) {
					if (ROOM[y][x] == 0)
						result += 1;
				}
			}

//			for (int y = 0; y < N; y++) {
//				System.out.println(Arrays.toString(ROOM[y]));
//			}
//			System.out.println();

			MIN = Math.min(MIN, result);
			return;
		}

		int y = CCTV.get(depth)[0];
		int x = CCTV.get(depth)[1];
		int type = CCTV.get(depth)[2];

		int[][] copy = new int[N][M];

		for (int d = 0; d < 4; d++) {

			for (int i = 0; i < N; i++) {
				copy[i] = ROOM[i].clone();
			}

			// type에 따라 다르게 진행
			// 감시 하고
			if (type == 1) {
				accept(y, x, d);
			} else if (type == 2) {
				// 감시하고
				accept(y, x, d);
				accept(y, x, d + 2);

			} else if (type == 3) {
				accept(y, x, d);
				accept(y, x, d + 1);
		

			} else if (type == 4) {
				accept(y, x, d);
				accept(y, x, d + 1);
				accept(y, x, d + 2);

			} else if (type == 5) {
				accept(y, x, d);
				accept(y, x, d + 1);
				accept(y, x, d + 2);
				accept(y, x, d + 3);
			}
			
			oversight(depth+1, CCTV);
			
			for (int i = 0; i < N; i++) {
				ROOM[i] = copy[i].clone();
			}
		}

	}

	static void accept(int y, int x, int d) {

		d %= 4;

		int ny = y;
		int nx = x;

		while (true) {
			ny += delta[d][0];
			nx += delta[d][1];

			if (!isValid(ny, nx))
				return;
			if (ROOM[ny][nx] > 0 && ROOM[ny][nx] < 6)
				continue; // CCTV면 지나가기

			ROOM[ny][nx] = 7;
		}
	}

	static boolean isValid(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < M && ROOM[ny][nx] != 6;
	}

}
