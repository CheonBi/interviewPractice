import java.io.*;
import java.util.*;

public class Solution{

	static int[][] MAP;
	static boolean[][] isVisited;
	static int[][] delta = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상 하 좌 우
	static int[][] pipes = new int[][] { 
			{}, // 0
			{ 0, 1, 2, 3 }, // 1
			{ 0, 1 }, // 2
			{ 2, 3 }, // 3
			{ 0, 3 }, // 4
			{ 1, 3 }, // 5
			{ 1, 2 }, // 6
			{ 0, 2 }, // 7
	};

	// 상 하 좌 우 
	// 붙을수 있는 파이프 타입들
	static int[][] canConnectPipes = new int[][] { { 1, 2, 5, 6 }, { 1, 2, 4, 7 }, { 1, 3, 4, 5 }, { 1, 3, 6, 7 } };
	static int N, M, R, C, L, result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			result = 1;

			MAP = new int[N][M];
			isVisited = new boolean[N][M];

			for (int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < M; x++) {
					MAP[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			bfs();

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.println(sb.toString());
		br.close();

	}

	static void bfs() {
		
		Queue<int[]> q = new ArrayDeque<int[]>();
		
		int pipeType = MAP[R][C]; //시작지점 파이프 타입
		isVisited[R][C] = true;
		int time = 1; //1시간뒤에 파이프에 숨음
		
		q.offer(new int[] { R, C, pipeType });
		
		while(!q.isEmpty()) {
			
			int size = q.size(); //한 턴 계산을 위한 size
			
			if(time == L) {
				return;
			}
			
			for (int i = 0; i < size; i++) { //한턴에 도는 수만큼 있을 수 있음
				int[] arr = q.poll();
				int y = arr[0];
				int x = arr[1];
				int curPipeType = arr[2];
				
				int[] nextDelta = pipes[curPipeType];
				
				//타입이 가진 크기만큼 돈다
				//가능한 방향으로
				for(int j = 0; j<nextDelta.length; j++) {
					
					int d = nextDelta[j];
					int ny = y + delta[d][0];
					int nx = x + delta[d][1];
					
					if(isValid(ny,nx)) {
						
						int[] canNextPipeTypes = canConnectPipes[d];
						int nextPipeType = MAP[ny][nx];
						
						//갈방향에 파이프가 올바른 파이프가 붙어 있는지 검사
						//올바른 파이프가 붙어있고 방문하지 않은 장소라면 탈주범이 위치가능한 장소 수 더하기
						for (int k = 0; k < canNextPipeTypes.length; k++) {
							if (canNextPipeTypes[k] == nextPipeType && !isVisited[ny][nx] && MAP[ny][nx] > 0) {
								q.offer(new int[] { ny, nx, nextPipeType });
								isVisited[ny][nx] = true;
								result++;
							} else {
								continue;
							}
						}
					}
				}
			}
			
			if(++time == L) {
				return;
			}
		}
	}

	static boolean isValid(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < M;
	}
}
