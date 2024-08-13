import java.io.*;
import java.util.*;

public class Solution {

	/**
	 * SWEA 1210. Ladder1
	 */

	/*
	 * 좌 - 우 아래로
	 */
	static int[][] ladderMap;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		for (int i = 0; i < 10; i++) {
			int tc = Integer.parseInt(br.readLine());
			int answer = -1;

			// 사다리 맵 제작
			// 100 * 100
			ladderMap = new int[100][100];

			for (int y = 0; y < 100; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < 100; x++) {
					ladderMap[y][x] = Integer.parseInt(st.nextToken());
				}
			}

			for (int x = 0; x < ladderMap[0].length; x++) {
				if (ladderMap[0][x] != 1)
					continue;

				if (Ladder(new int[] { 0, x })) {
					answer = x;
				} 
			}

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.println(sb);
	}

	static boolean Ladder(int[] start) {
		boolean[][] visited = new boolean[100][100];

		ArrayDeque<int[]> deque = new ArrayDeque<>();
		deque.offer(start);
		visited[start[0]][start[1]] = true;

		while (!deque.isEmpty()) {
			int[] point = deque.poll();
			
			int y = point[0];
			int x = point[1];

			if (ladderMap[y][x] == 2) {
				return true;
			}
	
			int L = x - 1;
			int R = x + 1;
			int D = y + 1;

			// 좌우 이동 가능 판별
			// 좌우 먼저 판단하고 좌우로 갈수 없으면 아래로 간다
			// 방문한 곳은 못감
			
			
			/*
			 *  방문 처리를 안할 시
			 *  
			 *   1 0 0 1 0 0 0
			 *   1 0 0 1 0 0 0
			 *   1 0 0 1 0 0 0
			 *   1 0 0 1 1 1 1 <-- 요런 부분에서 헛돔
			 *   1 1 1 1 0 0 0 <-- 요런 부분에서 헛돔
			 */
			if (valid(y, L) && !visited[y][L]) {
				visited[y][L] = true;
				deque.offer(new int[] { y, L });
			} 
			
			else if (valid(y, R) && !visited[y][R]) {
				visited[y][R] = true;
				deque.offer(new int[] { y, R });
			} 
			
			else if (valid(D, x) && !visited[D][x]) {
				visited[D][x] = true;
				deque.offer(new int[] { D, x });
			}
		}
		return false;
	}

	static boolean valid(int y, int x) {
		return y>=0 && y<100 && x>=0 && x<100 && ladderMap[y][x] != 0;
	}

}
