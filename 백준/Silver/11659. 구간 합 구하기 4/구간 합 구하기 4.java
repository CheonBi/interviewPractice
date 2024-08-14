import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] arg) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		int[] prefix = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		
		//원본배열
		for(int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		//누적합
		//[0, .....];
		for(int i = 1; i<=N; i++) {
			prefix[i] = prefix[i-1] + arr[i-1]; 
		}
		
		//부분합 공식 활용
		//S(i,j) = sum[j] - sum[i-1]
		for(int k = 0; k<M; k++) {
			st = new StringTokenizer(br.readLine());
			
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			
			int ans = prefix[j] - prefix[i-1];
			
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);

	}
}
