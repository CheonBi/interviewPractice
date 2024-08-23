import java.io.*;
import java.util.*;

/**
 * BOJ No. 16435 스네이크 버드
 */

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int[] fruits = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i<N; i++) {
			fruits[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(fruits);
		
		for(int i = 0; i<N; i++) {
			if(fruits[i] <= L) L++;
			else break;
		}
		
		System.out.println(L);	
		
	}
}
