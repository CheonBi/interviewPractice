import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[] parent = new int[N + 1];

			//루트 초기화
			for (int i = 1; i <= N; i++) {
				parent[i] = i;
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());

				int state = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				switch (state) {

				case 0: // 합집합
					union(a, b, parent);
					break;

				case 1:
					if (find(a, parent) == find(b, parent))
						sb.append(1);
					else
						sb.append(0);
					break;
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	//대표 원소 찾기
	static int find(int n, int[] parent) {
		if (n == parent[n]) {
			return n;
		}
		return parent[n] = find(parent[n], parent);
	}

	//합집합 연산
	//두 원소의 대표를 찾아서 연산
	static void union(int x, int y, int[] parent) {
		int nx = find(x, parent);
		int ny = find(y, parent);

		if (nx <= ny) {
			parent[ny] = nx;
		} else {
			parent[nx] = ny;
		}
	}
}
