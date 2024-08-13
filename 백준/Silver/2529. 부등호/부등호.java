import java.io.*;
import java.util.*;

public class Main {
	
	static char[] symbols;
	static boolean visited[] = new boolean[10];
	static int K;
	static int[] perm;
	
	static long MAX = Long.MIN_VALUE;
	static long MIN = Long.MAX_VALUE;
	static boolean zeroFirst = false;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder result = new StringBuilder();
		
		StringTokenizer st = null;
		
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		symbols = new char[K];
		perm = new int[K+1];
		
		for(int i = 0; i<K; i++) {
			symbols[i] = st.nextToken().charAt(0);
		}
		
		makePerm(0);
		
		if(zeroFirst) {
			result.append(MAX).append("\n").append('0').append(MIN);
		} else {
			result.append(MAX).append("\n").append(MIN);
		}

		System.out.println(result);
		
	}
	
	static void makePerm(int depth) {
		if(depth == K+1) {
			StringBuilder sb = new StringBuilder();
			
			for(int i = 0; i<symbols.length; i++) {
				if(symbols[i] == '<') {
					if(perm[i] > perm[i+1]) {
						return;
					}
				}
				
				else if(symbols[i] == '>') {
					if(perm[i] < perm[i+1]) {
						return;
					}
				}
			}
			
			for(int num : perm) {
				sb.append(num);
			}
			
			if(perm[0] == 0 ) {
				zeroFirst = true;
			}

			
			long num = Long.parseLong(sb.toString());
			
			
			MAX = Math.max(MAX, num);
			MIN = Math.min(MIN, num);
			
			return;
		}
		
		
		for(int i = 0; i<10; i++) {
			if(!visited[i]) {
				visited[i] = true;
				perm[depth] = i;
				makePerm(depth + 1);
				visited[i] = false;
			}
		}
	}
}
