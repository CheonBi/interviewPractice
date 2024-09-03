import java.io.*;
import java.util.*;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class Main {

	static int N;
	static int[][] MAP;
	static final int[][] delta = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int tc = 1;
		
		while (true) {
			N = Integer.parseInt(br.readLine());
			

			if (N == 0) {
				break;
			}

			MAP = new int[N][N];

			for (int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < N; x++) {
					MAP[y][x] = Integer.parseInt(st.nextToken());
				}
			}

			sb.append("Problem").append(" ").append(tc).append(":").append(" ").append(greenIsZelda()).append("\n");
			tc++;
		}

		System.out.println(sb.toString());
		br.close();
	}

	static int greenIsZelda() {
		int[][] isVisited = new int[N][N]; //방문했던곳 생각안하기
		int[][] minLoseRupee = new int[N][N]; //경로 최소값 저장
        
        //배열 중 루피 값을 비교하여 작은값우선 처리
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				minLoseRupee[y][x] = INF;
			}
		}

		// 시작점 (0,0)
        // 시작점 루피도 더해주기 위해 초기화
		minLoseRupee[0][0] = MAP[0][0];
		pq.offer(new int[] { 0, 0, minLoseRupee[0][0] });

        //PQ를 통해 다익스트라
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();

			int curY = cur[0];
			int curX = cur[1];
			int curRupee = cur[2];

			if (isVisited[curY][curX] == 1)
				continue;

			isVisited[curY][curX] = 1;

			if (curY == N - 1 && curX == N - 1) {
				return curRupee;
			}

            //인접한 정점 중에 유리한 곳고르기
			for (int d = 0; d < 4; d++) {
				int ny = curY + delta[d][0];
				int nx = curX + delta[d][1];

				if (isValid(ny, nx) && isVisited[ny][nx] == 0 && minLoseRupee[ny][nx] > curRupee + MAP[ny][nx]) {
					minLoseRupee[ny][nx] = curRupee + MAP[ny][nx];
					pq.offer(new int[] { ny, nx, minLoseRupee[ny][nx] });
				}

			}
		}
		return -1;
	}

	static boolean isValid(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N;
	}
}
