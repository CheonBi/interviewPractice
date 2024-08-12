import java.io.*;
import java.util.*;

public class Main {

	static int[][] map;

	static final int[] dy = { 0, 1, 0, -1 };
	static final int[] dx = { 1, 0, -1, 0 };

	static boolean[] visited;

	static ArrayList<int[]> chicken = new ArrayList<>();
	static ArrayList<int[]> house = new ArrayList<>();

	static int N, M;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); // 치킨집 개수

		map = new int[N][N];

		// 마을정보
		// 0 : 빈곳 , 1: 일반집, 2: 치킨집
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < N; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
				if (map[y][x] == 1)
					house.add(new int[] { y, x });
				else if (map[y][x] == 2)
					chicken.add(new int[] { y, x });
			}
		}

		// 지점수대로 트래킹하기 위한 배열
		visited = new boolean[chicken.size()];

		dfs(0, 0);
		System.out.println(answer);

		br.close();
	}

	static void dfs(int depth, int start) {
		// 지점이 M개 선택됬다면 종료조건
		if (depth == M) {
			int sum = 0;
			for (int[] h : house) { // 집에서 치킨집 까지 돌면서 거리를 계산
				int min = Integer.MAX_VALUE;
				for (int c = 0; c < chicken.size(); c++) { // 치킨집 좌표 순회
					if (visited[c]) { // 선택한 지점들만 계산
						int distance = Math.abs(h[0] - chicken.get(c)[0]) + Math.abs(h[1] - chicken.get(c)[1]);
						min = Math.min(min, distance); // 최소 치킨거리
					}
				}
				sum += min; // 치킨거리 합 저장 -> 도시의 치킨거리
			}
			answer = Math.min(answer, sum); // 선택된 지점조합 별 도시의 치킨거리 비교
			return;
		}

		for (int i = start; i < chicken.size(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(depth + 1, i + 1);
				visited[i] = false;
			}
		}
	}
}
