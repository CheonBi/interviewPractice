import java.io.*;
import java.util.*;

public class Solution {
	static int[][] synergy;
	static boolean[] isUsed;
	static int MIN, N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			synergy = new int[N][N];
			isUsed = new boolean[N];
			

			// 두 음식의 맛차이가 절대값이므로 0이상이다.
			MIN = Integer.MAX_VALUE;

			for (int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < N; x++) {
					synergy[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			select(0,0);

			sb.append("#").append(tc).append(" ").append(MIN).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	// 재료를 반 나누는 메소드
	// 조합
	static void select(int depth, int start) {
		if(depth == N/2) {
			//재료를 다고르고 요리시작
			cook();
			return;
		}
		
		for(int i = start; i<N; i++) {
			if(isUsed[i]) continue;
			isUsed[i] = true;
			select(depth + 1, i+1);
			isUsed[i] = false;
		}
	}

	//요리후 시너지 계산까지하는 메소드
	static void cook() {
		/*
		 * isUsed : T -> A요리
		 * isUsed : F -> B요리
		 */
		int size = N/2;
		int sumA = 0;
		int sumB = 0;
		
		//요리 재료를 담을 ArrayList
		ArrayList<Integer> cookA = new ArrayList<>(size);
		ArrayList<Integer> cookB = new ArrayList<>(size);
		
		//재료 분류
		for(int i = 0; i<isUsed.length; i++) {
			if(isUsed[i]) cookA.add(i);
			else cookB.add(i);
		}
		
		//시너지 계산
		for(int y = 0; y<size; y++) {
			for(int x = 0; x<size; x++) {
				sumA += synergy[cookA.get(y)][cookA.get(x)];
				sumB += synergy[cookB.get(y)][cookB.get(x)];
			}
		}
		
		MIN = Math.min(MIN, Math.abs(sumA-sumB));
	}
}
