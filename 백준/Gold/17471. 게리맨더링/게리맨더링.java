import java.io.*;
import java.util.*;

/*
 * BOJ No. 17471 게리맨더링
 */
public class Main {

	static int MIN = 0;

	public static void main(String[] args) throws Exception {

		/*
		 * 2 ≤ N ≤ 10 1 ≤ 구역의 인구 수 ≤ 100
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());

		int[] people = new int[N + 1];

		int[][] adjMatrix = new int[N+1][N+1];
		
		// 선거구 분배 배열
		int[] area = new int[N + 1];

		MIN = Integer.MAX_VALUE;

		// 인구수
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}

		// 인접정보
		for (int i = 1; i <= N; i++) {

			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());

			for (int j = 0; j < cnt; j++) {
				int n = Integer.parseInt(st.nextToken());
				adjMatrix[i][n] = 1;
			}

		}

		divideArea(1, N, area, people, adjMatrix);
		System.out.println(MIN == Integer.MAX_VALUE ? -1 : MIN);
	}

	static void divideArea(int depth, int N, int[] area, int[] people, int[][] adjMatrix) {
		if (depth == N + 1) {

//			System.out.println(Arrays.toString(area));
			
			if (isLink(0, area, N, adjMatrix) && isLink(1, area, N, adjMatrix)) {

				int sumA = 0;
				int sumB = 0;
				
				for(int i = 1; i<=N; i++) {
					if(area[i] == 0) {
						sumA += people[i];
					} else if(area[i] == 1) {
						sumB += people[i];
					}
				}

				MIN = Math.min(MIN, Math.abs(sumA - sumB));
			}

			return;
		}

		area[depth] = 1;
		divideArea(depth + 1, N, area, people, adjMatrix);

		area[depth] = 0;
		divideArea(depth + 1, N, area, people, adjMatrix);
	}

	static boolean isLink(int type, int[] area, int N, int[][] adjMatrix) {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] isVisited = new boolean[N + 1];

		for (int i = 1; i < area.length; i++) {
			if (area[i] == type) {
				queue.offer(i);
				isVisited[i] = true;
				break;
			}
		}

		//선택한 구역의 인접구역을 가져와
		//인접구역들도 같은 그룹인지 판단
		while (!queue.isEmpty()) {
			int p = queue.poll();
			
			for(int i = 1; i<=N; i++) {
				if (!isVisited[i] && area[i] == type && adjMatrix[p][i] == 1) {
					isVisited[i] = true;
					queue.offer(i);
				}
			}
		}
		
		for(int i = 1; i<=N; i++) {
			if(area[i] == type && !isVisited[i]) return false;
		}

		return true;
	}
}
