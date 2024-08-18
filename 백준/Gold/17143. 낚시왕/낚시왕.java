import java.io.*;
import java.util.*;

public class Main {
	// 위 아래 우 좌
	static int[][] delta = new int[][] { { 0, 0 }, { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
	static int[][] map;
	static int[][] sharks;
	static int result;
	static int R, C, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		result = 0;

		// R * C 입력
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		sharks = new int[M+1][5];

//		for (int y = 0; y < R; y++) {
//			for (int x = 0; x < C; x++) {
//				map[y][x] = -1;
//			}
//		}

		// 상어들 입력
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			sharks[i] = new int[] { r, c, s, d, z };
			map[r][c] = i;
		}

		// 낚시왕이 상어를 잡고 상어정보를 바탕으로 움직임
		for (int time = 0; time < C; time++) {
			sharkHunt(time);
			sharkMove();
			dieShark();
		}

		System.out.println(result);
	}

	static void sharkMove() {
		for (int i = 1; i <= M; i++) {
			if (sharks[i] != null) {
				int[] shark = sharks[i];

				int y = shark[0];
				int x = shark[1];
				int s = shark[2];
				int d = shark[3];
				int z = shark[4];
				
				map[y][x] = 0;

				for (int m = 0; m < s; m++) {
					int ny = y + delta[d][0];
					int nx = x + delta[d][1];

					if (!valid(ny, nx)) {
						if (d == 1 || d == 3) {
							d += 1;
						} else if (d == 2 || d == 4) {
							d -= 1;
						}
						
						ny = y + delta[d][0];
						nx = x + delta[d][1];
					}
					
					y = ny;
					x = nx;
				}
				
				sharks[i] = new int[] {y,x,s,d,z};
			}
		}
	}
	
	static void dieShark() {
		for(int i = 1; i<=M; i++) {
			if(sharks[i] != null) {
				
				int y = sharks[i][0];
				int x = sharks[i][1];
				int z = sharks[i][4];
				
				if(map[y][x] != 0) {
					int idx = map[y][x];
					
					int sharkSize = sharks[idx][4];
					
					if(z < sharkSize) {
						sharks[i] = null;
						map[y][x] = idx;
						
					} else if(z > sharkSize) {
						sharks[idx] = null;
						map[y][x] = i;
					}
					
				} else if(map[y][x] == 0) {
					map[y][x] = i;
				}
			}
		}
	}


	static void sharkHunt(int curX) {
		// x축 움직이며 y방향 탐색
		for (int y = 0; y < R; y++) {
			if (map[y][curX] != 0) {
				int idx = map[y][curX]; // 잡을 상어의 번호
				result += sharks[idx][4]; // 잡은 상어의 크기를 더하고
				map[y][curX] = 0; // 상어가 없는 상태로 초기화
				sharks[idx] = null;
				break;
			}
		}
	}

	static boolean valid(int ny, int nx) {
		return ny >= 0 && ny < R && nx >= 0 && nx < C;
	}
}
