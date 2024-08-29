import java.io.*;
import java.util.*;

/**
 * 
 * SWEA No. 7465 창용 마을 무리의 개수
 *
 */
public class Solution {

	// Union - Find 집합 개수 세기
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[] parents = new int[N+1];

			// Make-Set
			for (int i = 0; i < parents.length; i++) {
				parents[i] = i;
			}

			for (int i = 0; i < M; i++) {
				// Union
				st = new StringTokenizer(br.readLine());

				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				Union(x, y, parents);
			}

			sb.append("#").append(tc).append(" ").append(countRoot(parents)).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

	static int Find(int N, int[] parents) {
		if (N == parents[N]) {
			return N;
		}
		return parents[N] = Find(parents[N], parents); // 경로 압축
	}

	static void Union(int x, int y, int[] parents) {
		int nx = Find(x, parents);
		int ny = Find(y, parents);

		// 수가 적은 쪽으로 union
		if (nx < ny) {
			parents[ny] = nx;
		} else if (ny < nx) {
			parents[nx] = ny;
		}
	}

	// 대표자를 찾아 집합의 수 카운트
	static int countRoot(int[] parents) {
		int cnt = 0;
		for (int i = 1; i < parents.length; i++) {
			// 인덱스와 배열의 값이 같다는 것은 본인이 root라는 의미
			if (parents[i] == i) {
				cnt += 1;
			}
		}
		return cnt;
	}

}
