import java.io.*;
import java.util.*;

public class Solution {

	static int ans;
	static boolean[] isVisited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			
			int N = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			
			//x - y 순서
			int[] company = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			int[] home = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			int[][] customers = new int[N][];
			isVisited = new boolean[N];
			
			
			//고객 정보
			for(int i = 0; i<N; i++) {
				customers[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			}
			
			ans = Integer.MAX_VALUE;
			
			dfs(0, home, customers, company[0], company[1], 0, N);

			sb.append("#").append(tc).append(" ").append(ans).append("\n");	
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	

	
	//최단거리 구하기
	static void dfs(int depth, int[] home, int[][] customers, int x, int y, int w, int N) {

		//이미 이전값을 넘었다면 return
		if(ans <= w) {
			return;
		}
		
		if(depth == N) {
			//회사 - 고객까지 가중치에서
			//집을 마지막 계산
			w += Math.abs(home[0] - x) + Math.abs(home[1] - y);
			
			ans = Math.min(ans, w);
			return;
		}
		
		for(int i = 0; i<N; i++) {
			if(!isVisited[i]) {
				isVisited[i] = true;
				
				//거리계산
				int nx = customers[i][0];
				int ny = customers[i][1];
				int nw = Math.abs(y-ny) + Math.abs(x-nx);
				
				dfs(depth + 1, home, customers, nx, ny, w + nw, N);
				
				isVisited[i] = false;
			}
				
		}
	}
}
