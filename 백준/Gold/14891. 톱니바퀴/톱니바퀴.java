/*
 * BOJ No.14891 톱니바퀴
 */
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		// 톱니바퀴 배열들
		// 12 - 1 - 3 - 5 - 6 - 7 - 9 - 11
		// 닿는부위

		// 1번 3시 - 2번 9시
		// 2번 3시 - 3번 9시
		// 3번 3시 - 4번 9시

		int[][] gears = new int[4][8];

		for (int i = 0; i < 4; i++) {
			char[] ch = br.readLine().toCharArray();
			for (int j = 0; j < 8; j++) {
				gears[i][j] = ch[j] - '0';
			}
		}

		int K = Integer.parseInt(br.readLine());

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()) - 1;
			int D = Integer.parseInt(st.nextToken());

			// 처음 움직일 기어저장
			int[] clocks = new int[4];
			clocks[N] = D;

			// 이후 오른쪽 왼쪽 기어들 자성을 체크하며
			// 시계 반시계 회전 확인

			// 오른쪽
			for (int G = N; G < 3; G++) {
				if (gears[G][2] != gears[G + 1][6]) {
					clocks[G + 1] = clocks[G] * -1;
				}
			}

			// 왼쪽
			for (int G = N; G > 0; G--) {
				if (gears[G][6] != gears[G - 1][2]) {
					clocks[G - 1] = clocks[G] * -1;
				}
			}

			// 돌리기
			for (int R = 0; R < 4; R++) {
				if (clocks[R] != 0) {
					rotate(clocks[R], R, gears);
				}
			}
		}
		
		System.out.println(calcScore(gears));

	}

	static void rotate(int clock, int num, int[][] gear) {
		// clock == 1: 시계 -1 : 반시계

		if (clock == 1) {
			int tmp = gear[num][7];
			for (int i = 7; i > 0; i--) {
				gear[num][i] = gear[num][i - 1];
			}
			gear[num][0] = tmp;
		}

		else {
			int tmp = gear[num][0];
			for (int i = 0; i < 7; i++) {
				gear[num][i] = gear[num][i + 1];
			}
			gear[num][7] = tmp;
		}
	}

	static int calcScore(int[][] gears) {
		int result = 0;

		result += gears[0][0] * 1 + gears[1][0] * 2 + gears[2][0] * 4 + gears[3][0] * 8;

		return result;
	}
}
