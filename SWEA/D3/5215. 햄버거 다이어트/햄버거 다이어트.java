import java.io.*;
import java.util.*;

/**
 * SWEA No.5215 햄버거 다이어트 (사실은 햄버거를 안먹는게 다이어트이다...)
 *
 */

//여러 재료를 조합하였을 햄버거의 선호도는 조합된 재료들의 맛에 대한 점수의 합으로 결정되고, 
//같은 재료를 여러 번 사용할 수 없으며, 
//햄버거의 조합의 제한은 칼로리를 제외하고는 없다
//햄버거를 조합할때 순서는 상관없다

public class Solution {

	static int[] scores;
	static int[] kcals;
	static boolean[] isUsed;
	static int result;
	static int N, L;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken()); // 재료수
			L = Integer.parseInt(st.nextToken()); // 제한 칼로리

			scores = new int[N];
			kcals = new int[N];

			result = 0;

			// 각 재료의 평가 및 칼로리
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int score = Integer.parseInt(st.nextToken());
				int kcal = Integer.parseInt(st.nextToken());

				scores[i] = score;
				kcals[i] = kcal;
			}

            //subset
            MichelinGuide(0, 0, 0);

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
        br.close();
		System.out.println(sb);
	}

	static void MichelinGuide(int depth, int score, int kcal) {
		if (depth >= N) {
            if(kcal > L) return;
            
            if(kcal <= L && score > result){
            	result = score;
            }
            return;
		}

		// 현재 재료 더하기
		MichelinGuide(depth + 1, score + scores[depth], kcal + kcals[depth]);

		// 현재 재료 더하기 X
		MichelinGuide(depth + 1, score, kcal);
	}
}
