import java.io.*;
import java.util.*;

public class Main {
	
	static char[] symbols;
	static int[] perm;
	static boolean visited[] = new boolean[10];
	static int K;
	
	
	
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
		
		for(int i = 0; i<10; i++) {
			visited[i] = true;
			perm[0] = i;
			makePerm(1,perm[0],0);
			visited[i] = false;
		}
		
		
		//최소값의 첫자리가 0이면 0을 붙여서 출력
		if(zeroFirst) {
			result.append(MAX).append("\n").append('0').append(MIN);
		} else {
			result.append(MAX).append("\n").append(MIN);
		}

		System.out.println(result);
		
	}
	
	static void makePerm(int depth, int prev, int index) {
		if(depth == K+1) {
			StringBuilder sb = new StringBuilder();
			
			//순열을 만들고 부등호 조건 탐색
//			for(int i = 0; i<symbols.length; i++) {
//				if(symbols[i] == '<') {
//					if(perm[i] > perm[i+1]) {
//						return;
//					}
//				}
//				
//				else if(symbols[i] == '>') {
//					if(perm[i] < perm[i+1]) {
//						return;
//					}
//				}
//			}
			
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
		
		
		//순열을 만들기 위한 탐색
		for(int i = 0; i<10; i++) {
			if(!visited[i] && ((symbols[index] == '<' && prev < i) || (symbols[index] == '>') && prev > i)) {
				visited[i] = true;
				perm[depth] = i;
				makePerm(depth + 1, i, index+1);
				visited[i] = false;
			}
		}
	}
	

}
