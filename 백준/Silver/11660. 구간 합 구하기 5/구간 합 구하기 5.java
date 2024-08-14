import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		int[][] prefix = new int[N+1][N+1];
		
		for(int y = 0; y<N; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x<N; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int y = 1; y<=N; y++) {
			for(int x = 1; x<=N; x++) {
				prefix[y][x] = prefix[y-1][x] + prefix[y][x-1] - prefix[y-1][x-1] + map[y-1][x-1];
			}
		}

//		System.out.println(Arrays.deepToString(prefix));
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int sum = prefix[x2][y2] - prefix[x1-1][y2] - prefix[x2][y1-1] + prefix[x1-1][y1-1];
			sb.append(sum).append("\n");
		}		
		System.out.println(sb);		
		br.close();
	}
}
