import java.io.*;
import java.util.*;

/**
 * SWEA No.5215 햄버거 다이어트
 * (사실은 햄버거를 안먹는게 다이어트이다...)
 *
 */

//여러 재료를 조합하였을 햄버거의 선호도는 조합된 재료들의 맛에 대한 점수의 합으로 결정되고, 
//같은 재료를 여러 번 사용할 수 없으며, 
//햄버거의 조합의 제한은 칼로리를 제외하고는 없다
//햄버거를 조합할때 순서는 상관없다

public class Solution {

	static int[][] ingredients;
	static boolean[] isUsed;
	static int result;
	static int N, L;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken()); //재료수
			L = Integer.parseInt(st.nextToken()); //제한 칼로리
			
			ingredients = new int[N][2]; 
			isUsed = new boolean[N];
			
			result = Integer.MIN_VALUE;
			
			//각 재료의 평가 및 칼로리
			for(int i = 0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int score = Integer.parseInt(st.nextToken());
				int kcal = Integer.parseInt(st.nextToken());
				ingredients[i] = new int[] {score, kcal};
			}
			
			//재료를 고르는 개수에 따라 계산
			for(int i = 1; i<=N; i++) {
				int[][] hambuger = new int[i][2];
				MichelinGuide(0, 0, hambuger);
			}
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
	
	static void MichelinGuide(int depth, int start, int[][] hambuger)  {
		if(depth == hambuger.length) {
			int kcals = 0;
			int score = 0;
			for(int[] arr : hambuger) {
				score += arr[0];
				kcals += arr[1];
			}
			
			if(kcals > L) return;
			else {
				result = Math.max(result, score);
			}
			return;
		}
		
		for(int i = start; i<N; i++) {
			if(!isUsed[i]) {
				isUsed[i] = true;
				hambuger[depth] = ingredients[i];
				MichelinGuide(depth+1, i+1, hambuger);
				isUsed[i] = false;
			}
		}
	}
}
