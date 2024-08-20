import java.io.*;
import java.util.*;

public class Main {
	static int N, M, R;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());

		/*
		 * 2 ≤ N, M ≤ 300 1 ≤ R ≤ 1,000 min(N, M) mod 2 = 0
		 */

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N][M];

		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < M; x++) {
				arr[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		int cnt = Math.min(N, M) / 2;
		
		for(int i = 0; i<R; i++) {
			swap(cnt, arr);
		}
		
		for(int y = 0; y<N; y++) {
			for(int x = 0; x<M; x++) {
				sb.append(arr[y][x]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
		
	}

	static void swap(int cnt, int[][] arr) {

		while (cnt-- > 0) {

			int start = arr[cnt][cnt];

			// top line
			for (int x = cnt; x < M - cnt - 1; x++) {
				arr[cnt][x] = arr[cnt][x + 1];
			}

			// right line
			for (int y = cnt; y < N - cnt - 1; y++) {
				arr[y][M - cnt - 1] = arr[y + 1][M - cnt - 1];
			}


			// bottom line
			for (int x = M - cnt - 1; x > cnt; x--) {
				arr[N - cnt - 1][x] = arr[N - cnt - 1][x - 1];
			}
			

			// left line
			for (int y = N - cnt - 1; y > cnt; y--) {
				arr[y][cnt] = arr[y-1][cnt];
			}


			arr[cnt + 1][cnt] = start;

		}
	}
}
