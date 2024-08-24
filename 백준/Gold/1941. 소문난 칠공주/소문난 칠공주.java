import java.io.*;
import java.util.*;

/*
 * BOJ No.1941 소문난 칠공주
 * 그녀들의 기잔치....
 */
public class Main {

	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[][] MAP = new char[5][5]; // 총 25명의 여학생들로 이루어진 여학생반

		boolean[] recruited = new boolean[25]; // 선택된 학생의 위치를 알기위한 배열
		
		/*
		 * (1,1) 은 MAP의 6번째 값으로 계산
		 *  row * 5 + col 으로 계산가능
		 *  0 1 2 3 4 -> (0,0) (0,1) (0,2) (0,3) (0,4)
		 *  5 6 7 8 9 -> (1,0) (1,1) (1,2) (1,3) (1,4)
		 *  
		 *  따라서 조합에서 해당 MAP의 좌표가 선택되면 점화식으로 계산하여
		 *  배열상태를 true로 변경
		 *  다시 원복해서 좌표값을 구하고 싶다면
		 *  
		 *  idx / 5 = row
		 *  idx % 5 = col
		 */

		result = 0;

		for (int y = 0; y < 5; y++) {
			MAP[y] = br.readLine().toCharArray();
		}

		// 칠공주 찾기
		recruitPrincess(0, 0, 0, MAP, recruited);
		System.out.println(result);
		br.close();

	}

	static void recruitPrincess(int depth, int start, int doyeon, char[][] MAP, boolean[] recruited) {
		if (doyeon >= 4)
			return;

		// 7명 섭외 완료
		if (depth == 7) {
			//자리가 연결되어 있는지 판단
			//depth올라올때 start도 기존에 한번더 추가해서 올라옴
			//때문에 -1하여 정상화
			
			start -=1;
			if (sevenPrincess(start/ 5, start % 5, MAP, recruited))
				result++;
			return;
		}

		for (int i = start; i < 25; i++) {
			recruited[i] = true;

			// 도연파일경우 도연파 수 증가
			if (MAP[i / 5][i % 5] == 'Y') {
				recruitPrincess(depth + 1, i + 1, doyeon + 1, MAP, recruited);
			} else { // 다솜파면 그냥 조합 진행
				recruitPrincess(depth + 1, i + 1, doyeon, MAP, recruited);
			}

			recruited[i] = false;
		}

	}

	static boolean sevenPrincess(int y, int x, char[][] MAP, boolean[] recruited) {

		ArrayDeque<int[]> queue = new ArrayDeque<>();
		int[][] delta = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } }; // 상 하 좌 우

		boolean[][] isVisited = new boolean[5][5];
		isVisited[y][x] = true;

		queue.offer(new int[] { y, x });

		int linked = 1;

		// 탐색 시작
		while (!queue.isEmpty()) {
			int[] princess = queue.poll();

			for (int d = 0; d < 4; d++) {
				int ny = princess[0] + delta[d][0];
				int nx = princess[1] + delta[d][1];
				int ndx = ny * 5 + nx;

				// 범위내이고, 전체 맵 내에서 방문하지 않았고, 조합 내에 포함되어 있다면 진행
				if (isValid(ny, nx) && !isVisited[ny][nx] && recruited[ndx]) {
					isVisited[ny][nx] = true;
					queue.offer(new int[] { ny, nx });
					linked += 1;
				}
			}
		}

		// 7명이 모두 인접되어 있다면 true
		// 아니라면 false
		return linked == 7;
	}

	static boolean isValid(int ny, int nx) {
		return ny >= 0 && ny < 5 && nx >= 0 && nx < 5;
	}

}
