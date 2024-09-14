import java.io.*;
import java.util.*;

public class Main {

	static int N, L, R, result;
	static int[][] delta = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static int[][] MAP;
	static boolean flag = true;
	static boolean[][] isVisited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		MAP = new int[N][N];
		isVisited = new boolean[N][N];

		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < N; x++) {
				MAP[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		int day = 0;
		while (true) { // 이동할 수 없을 때까지
			if (bfs() == 0) {
				System.out.println(day);
				return;
			} else
				day++;
		}
	}

	// 1.연합 알아내기
	// 2.인구이동
	// 3.다시 연합 찾기
	// 4.인구이동

	static int bfs() {
		isVisited = new boolean[N][N]; // 방문 초기화
		int flag = 0;

		// 진짜 이런문제 너무 무섭다.
		// 시간복잡도 넘을까봐 함부로 작성하지 못하는 이 슬픔
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (!isVisited[y][x]) {

					// bfs
					ArrayList<int[]> arr = new ArrayList<>(); // 연합 리스트
					Queue<int[]> q = new ArrayDeque<>(); // 탐색 큐

					q.offer(new int[] { y, x });

					int sum = 0;

					while (!q.isEmpty()) {
						int[] node = q.poll();

						int sy = node[0];
						int sx = node[1];

						if (isVisited[sy][sx])
							continue;
						isVisited[sy][sx] = true;

						arr.add(node);
						sum += MAP[sy][sx];

						for (int d = 0; d < 4; d++) {
							int ny = sy + delta[d][0];
							int nx = sx + delta[d][1];

							if (!isValid(ny, nx, sy, sx))
								continue;
							if (isVisited[ny][nx])
								continue;

							q.add(new int[] { ny, nx });
						}
					}

					if (arr.size() != 1) {
						// 0,0 밖에 없다는 것은 인구이동 불가능
						sum /= arr.size();

						// 인구 이동
						for (int[] n : arr) {
							MAP[n[0]][n[1]] = sum;
						}

						flag++;
					}
				}
			}
		}

		// 모든 좌표 bfs 수행 해도 0이면 인구이동 수행이 불가
		return flag;
	}

	static boolean isValid(int ny, int nx, int sy, int sx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N // 배열 범위 밖, 인구차이 L과 R 사이
				&& (Math.abs(MAP[ny][nx] - MAP[sy][sx]) >= L && Math.abs(MAP[ny][nx] - MAP[sy][sx]) <= R);
	}

}
