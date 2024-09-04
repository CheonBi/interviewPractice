import java.io.*;
import java.util.*;

public class Main {

	static int N, M, MAX;
	static int[][] MAP;
	static boolean[][] isVisited;
	static final int[][] delta = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		MAX = Integer.MIN_VALUE;

		MAP = new int[N][M];
		isVisited = new boolean[N][M];

		List<int[]> pure = new ArrayList<>();
		List<int[]> virus = new ArrayList<>();

		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < M; x++) {
				MAP[y][x] = Integer.parseInt(st.nextToken());

				if (MAP[y][x] == 0)
					pure.add(new int[] { y, x });
				else if (MAP[y][x] == 2) {
					virus.add(new int[] { y, x });
				}
			}
		}

		int[] walls = new int[3]; // 새로 벽을 세울 포인트를 순열 배열

		// 세울 벽 위치 찾기
		combination(0, 0, pure, virus, walls);
		
		System.out.println(MAX);
	}

	static void combination(int depth, int start, List<int[]> pure, List<int[]> virus, int[] walls) {
		if (depth == 3) { //세울 벽 위치를 다 찾았다면 

			//벽세우고
//			swap(walls, pure, 1);

			//바이러스 전염 bfs
			int cnt = bfs(pure, virus, walls);

			if (cnt > MAX)
				MAX = cnt;

			//벽 허물고
//			swap(walls, pure, 0);

			return;
		}

		for (int i = start; i < pure.size(); i++) {
			walls[depth] = i;
			combination(depth + 1, i + 1, pure, virus, walls);
		}

	}

	static int bfs(List<int[]> pure, List<int[]> virus, int[] walls) {
		int[][] ifMap = new int[N][M];
		boolean[][] isVisited = new boolean[N][M]; // 방문처리
		Queue<int[]> q = new ArrayDeque<>();
		int sum = 0;

		// 맵 복사
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				ifMap[y][x] = MAP[y][x];
			}
		}
		
		//벽 세우기
		for (int i = 0; i < 3; i++) {
			int[] pos = pure.get(walls[i]);
			int y = pos[0];
			int x = pos[1];
			ifMap[y][x] = 1;
		}

		
		//전염시작
		for (int i = 0; i < virus.size(); i++) {
			int y = virus.get(i)[0];
			int x = virus.get(i)[1];
			q.offer(new int[] { y, x });
		}

		while (!q.isEmpty()) {
			int[] vir = q.poll();

			int y = vir[0];
			int x = vir[1];

			for (int d = 0; d < 4; d++) {
				int ny = y + delta[d][0];
				int nx = x + delta[d][1];

				if (isValid(ny, nx) && !isVisited[ny][nx] && ifMap[ny][nx] == 0) {
					ifMap[ny][nx] = 2; // 전염
					q.offer(new int[] { ny, nx });
				}
			}
		}

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				if (ifMap[y][x] == 0)
					sum += 1;
			}
		}

		return sum;

	}

	// 벽세우고 허물기
	static void swap(int[] walls, List<int[]> pure, int var) {
		for (int i = 0; i < 3; i++) {
			int[] pos = pure.get(walls[i]);
			int y = pos[0];
			int x = pos[1];
			MAP[y][x] = var;
		}
	}

	static boolean isValid(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < M;
	}

}
