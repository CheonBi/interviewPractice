import java.io.*;
import java.util.*;

public class Main {

	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		M = 0;

		int[][] MAP = new int[N][N];
		int startY = 0;
		int startX = 0;

		// 맵입력
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int x = 0; x < N; x++) {
				MAP[y][x] = Integer.parseInt(st.nextToken());
				if (MAP[y][x] == 9) {
					startY = y;
					startX = x;
				}
			}
		}

		System.out.println(searchFish(startY, startX, 0, MAP));
	}

	// 먹을 물고기 탐색
	static int searchFish(int y, int x, int d, int[][] MAP) {

		int[][] delta = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

		int sharksize = 2; // 초기 상어 사이즈는 2
		int huntCount = 0; // 상어가 먹은 먹이 수
		int time = 0; // 이동시간

		// 물고기 찾는 큐
		Queue<int[]> queue = new ArrayDeque<>();

		// 물고기 후보큐
		PriorityQueue<int[]> fishes = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[2] == o2[2]) { // 같은 거리일때
					if (o1[0] == o2[0]) { // 같은 행이면 더 낮은 열 (상어기준 왼쪽)
						return Integer.compare(o1[1], o2[1]);
					}
					return Integer.compare(o1[0], o2[0]); // 같은 열에 있으면 더 낮은 행(상어기준 위쪽)
				}
				return Integer.compare(o1[2], o2[2]); // 아니면 가장 가까운것
			}
		});

		// 먹이를 지속적으로 탐색
		while (true) {

			// 초기화
			queue.clear();
			fishes.clear();

			// 상어 이동 확인 배열
			boolean[][] isVisited = new boolean[N][N];

			// 상어 위치
			queue.offer(new int[] { y, x, 0 });

			while (!queue.isEmpty()) {
				int[] shark = queue.poll();

				for (int i = 0; i < 4; i++) {
					int ny = shark[0] + delta[i][0];
					int nx = shark[1] + delta[i][1];

					if (!isValid(ny, nx) || isVisited[ny][nx] || sharksize < MAP[ny][nx])
						continue;

					// 상어크기보다 작고 0이 아니면 먹이 후보군에 등록
					// 아니라면 상어 이동
					if (sharksize > MAP[ny][nx] && MAP[ny][nx] > 0) {
						fishes.offer(new int[] { ny, nx, shark[2] + 1 });
					} else {
						queue.offer(new int[] { ny, nx, shark[2] + 1 });
					}

					isVisited[ny][nx] = true;
				}
			}

			// 더이상 먹을 수 있는 먹이가 없다면 종료
			if (fishes.isEmpty())
				break;

			else {
				huntCount += 1;
				time += fishes.peek()[2];

				if (sharksize == huntCount) {
					sharksize += 1;
					huntCount = 0;
				}

				//기존 상어 위치 변경
				MAP[y][x] = 0;

				//상어의 한끼 식사 완료
				MAP[fishes.peek()[0]][fishes.peek()[1]] = 0;

				// 상어위치 변경
				y = fishes.peek()[0];
				x = fishes.peek()[1];
			}
		}

		return time;

	}

	static boolean isValid(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N;
	}

}
