import java.io.*;
import java.util.*;

public class Solution {
	static int H, W, N, result; // y , x , 구슬 발사 횟수
	static int[][] MAP;
	static int[][] delta = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static Stack<Integer> stack = new Stack<>(); // 중력전용

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			result = Integer.MAX_VALUE;

			MAP = new int[H][W];
			int[] beadPos = new int[N];

			for (int y = 0; y < H; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < W; x++) {
					MAP[y][x] = Integer.parseInt(st.nextToken());
				}
			}

			selectBeadPos(0, beadPos);
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	// 순열로 벽돌 위치 정하고
	// 부시기 시작
	static void selectBeadPos(int depth, int[] beadPos) {
		if (depth == N) {

			
			// 맵복사해서 하기
			int[][] cloneMAP = mapClone();
			
			
			//부시고 중력
			crash(beadPos, cloneMAP);
			
			result = Math.min(result, getBlocks(cloneMAP));
//			System.out.println(Arrays.toString(beadPos));

//			for (int i = 0; i < H; i++) {
//				System.out.println(Arrays.toString(cloneMAP[i]));
//			}
//			System.out.println();

			return;
		}

		for (int i = 1; i <= W; i++) {
			beadPos[depth] = i;
			selectBeadPos(depth + 1, beadPos);
		}
	}

	static void crash(int[] beadPos, int[][] cloneMAP) {
		// 깨고 중력적용
		int sx;

		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] isCrashed;

		for (int i = 0; i < beadPos.length; i++) {
			sx = beadPos[i] - 1;

			isCrashed = new boolean[H][W];

			for (int y = 0; y < H; y++) {
				if (cloneMAP[y][sx] > 0) {
					q.offer(new int[] { y, sx, cloneMAP[y][sx] });
					break;
				}
			}

			while (!q.isEmpty()) {
				int[] block = q.poll();
				int y = block[0];
				int x = block[1];
				int p = block[2];

				isCrashed[y][x] = true;
				cloneMAP[y][x] = 0;

				// 1짜리 벽돌은 혼자 부서짐
				if (p == 1) {
					cloneMAP[y][x] = 0;
					continue;

				} else {

					for (int d = 0; d < 4; d++) {
						int ny = y + delta[d][0];
						int nx = x + delta[d][1];

						// 벽돌 숫자 - 1 만큼
						for (int k = 0; k < p - 1; k++) {

							if (!isValid(ny, nx))
								continue;

							if (!isCrashed[ny][nx] && cloneMAP[ny][nx] > 0) {
								q.offer(new int[] { ny, nx, cloneMAP[ny][nx] });
								cloneMAP[ny][nx] = 0;
								isCrashed[ny][nx] = true;
							}

							ny += delta[d][0];
							nx += delta[d][1];
						}
					}
				}

			}

			gravity(cloneMAP);

//			for (int n = 0; n < H; n++) {
//				System.out.println(Arrays.toString(cloneMAP[n]));
//			}
//			System.out.println();

		}
	}

	static void gravity(int[][] cloneMAP) {
		stack.clear();

		for (int x = 0; x < W; x++) {
			for (int y = 0; y < H; y++) {
				if (cloneMAP[y][x] > 0) {
					stack.push(cloneMAP[y][x]);
					cloneMAP[y][x] = 0;
				}
			}

			int g = H - 1;

			while (!stack.isEmpty()) {
				cloneMAP[g][x] = stack.pop();
				g--;
			}
		}
	}
	
	static int getBlocks(int[][] cloneMAP) {
		int blocks = 0;
		for (int y = 0; y < H; y++) {
			for (int x = 0; x < W; x++) {
				if (cloneMAP[y][x] > 0)
					blocks += 1;
			}
		}
		return blocks;
	}

	static int[][] mapClone() {
		int[][] clone = new int[H][W];

		for (int y = 0; y < H; y++) {
			for (int x = 0; x < W; x++) {
				clone[y][x] = MAP[y][x];
			}
		}

		return clone;
	}

	static boolean isValid(int ny, int nx) {
		return ny >= 0 && ny < H && nx >= 0 && nx < W;
	}

}
