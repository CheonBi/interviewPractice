import java.io.*;
import java.util.*;

public class Solution {

	static int N, CNT;
	static int[] RESULT;
	static int[][] ROOM;
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // {y,x}, 상 하 좌 우

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			ROOM = new int[N][N];
			RESULT = new int[2];
			
			for (int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < N; x++) {
					ROOM[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					CNT = 1;
					move(y, x);
					
					if(CNT > RESULT[0]) {
						RESULT[0] = CNT;
						RESULT[1] = ROOM[y][x];
					} else if(CNT == RESULT[0] && RESULT[1] > ROOM[y][x]) {
						RESULT[1] = ROOM[y][x];
					}
				}
			}

			

			sb.append("#").append(tc).append(" ").append(RESULT[1]).append(" ").append(RESULT[0]).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

	static void move(int y, int x) {

		for (int d = 0; d < 4; d++) {
			int ny = y + delta[d][0];
			int nx = x + delta[d][1];
			
			if(isValid(ny,nx) && (ROOM[y][x] + 1) == ROOM[ny][nx]) {
				CNT++;
				move(ny, nx);
			} 
		}
	}

	static boolean isValid(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N;
	}

}
