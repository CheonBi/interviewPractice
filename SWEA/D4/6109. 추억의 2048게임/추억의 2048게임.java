import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static boolean[][] isMerged;
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; //상 하 좌 우
	static int[][] BOARD;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			String direction = st.nextToken();

			BOARD = new int[N][N];
			isMerged = new boolean[N][N];

			// 게임 보드 입력
			for (int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int x = 0; x < N; x++) {
					BOARD[y][x] = Integer.parseInt(st.nextToken());
				}
			}

			// 움직일 방향에 따라 파라미터 설정
			switch (direction) {
				case "up":
					/*
					 * Y: 1 -> N-1
					 * X: 0 -> N-1
					 * 현재 위치와 위에 위치한 값을 비교
					 * 때문에 Y축을 0과 1부터 비교 시작
					 */
					for (int x = 0; x < N; x++) {
						for (int y = 1; y < N; y++) {
							move(y, x, 0);
						}
					}
					break;
	
				case "down":
					
					/*
					 * Y: N-2 -> 0
					 * X: 0 -> N-1
					 * 현재 위치와 아래에 위치한 값을 비교
					 * 때문에 Y축을 N-2와 N-1부터 비교시작
					 */
					for (int x = 0; x < N; x++) {
						for (int y = N - 2; y >= 0; y--) {
							move(y, x, 1);
						}
					}
					break;
					
				case "left":
					/*
					 * Y: 0 -> N-1
					 * X: 1 -> N-1
					 * 현재 위치와 왼쪽에 위치한 값을 비교
					 * 때문에 X축을  0과 1부터 비교 시작
					 */
					for (int y = 0; y < N; y++) {
						for (int x = 1; x < N; x++) {
							move(y, x, 2);
						}
					}
					break;
	
				case "right":
					/*
					 * Y: 0 -> N-1
					 * X: N-2 -> 0
					 * 현재 위치와 오른쪽에 위치한 값을 비교
					 * 때문에 Y축을 N-2와 N-1부터 비교시작
					 */
					for (int y = 0; y < N; y++) {
						for (int x = N - 2; x >= 0; x--) {
							move(y, x, 3);
						}
					}
					break;
			}
			
			sb.append("#").append(tc).append("\n");
			for(int[] arr : BOARD) {
				for(int n : arr) {
					sb.append(n).append(" ");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
		br.close();
	}
	
	static void move(int y, int x, int d) {
		int ny = y + delta[d][0];
		int nx = x + delta[d][1];
		
		//범위 밖이거나 이미 검정한 위치면 return
		if(!valid(ny, nx) || isMerged[ny][nx]) return;
		
		//현재 위치가 0이 아니고
		//비교할 위치와 현재위치가 값이 같고 이미 검정한 위치가 아니면 실행
		if(BOARD[y][x] != 0 && BOARD[ny][nx] == BOARD[y][x] && !isMerged[y][x]) {
			BOARD[ny][nx] = BOARD[y][x] * 2; //현재 위치의 값의 두배 (합치는것과 같은 결과)
			BOARD[y][x] = 0; //현재 위치 0으로 초기화
			isMerged[ny][nx] = true; //연속적으로 합쳐지지 않기 위해 true 처리
		} else if(BOARD[ny][nx] == 0) {
			BOARD[ny][nx] = BOARD[y][x]; //비교할 위치가 0이면 현재위치 값을 이동
			BOARD[y][x] = 0; //현재 위치는 0으로 초기화
		}
		
		//연속적으로 밀리는 타일 비교를 위해 재귀
		move(ny, nx, d);
	}
	
	static boolean valid(int ny, int nx) {
		return ny >= 0 && ny < N && nx>=0 && nx<N;
	}
}
