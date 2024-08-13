import java.io.*;
import java.util.*;

/**
 * SWEA 6808. 규영이와 인영이의 카드 게임
 */
public class Solution {

	static boolean[] visited = new boolean[9];
	static int[] kyuyongCards = new int[9];
	static int[] inyoungCards = new int[9];
	static int[] perm = new int[9];
	static int winCnt, loseCnt;

	public static void main(String[] args) throws Exception {
		/*
		 * 18장 카드 한명 씩 9장 아홉 라운드
		 * 
		 * 한 라운드에 한장 씩 카드를 낸 다음 카드를 비교 높은 수가 적힌 카드를 낸 사람은 두 카드의 합산만큼 점수 획득
		 * 
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			winCnt = 0;
			loseCnt = 0;

			st = new StringTokenizer(br.readLine());

			//카드 분리를 위한 배열
			boolean[] split = new boolean[19];

			for (int i = 0; i < 9; i++) {
				int card = Integer.parseInt(st.nextToken());
				split[card] = true;
				kyuyongCards[i] = card;
			}

			int idx = 0;

			for (int i = 1; i <= 18; i++) {
				if (!split[i]) {
					inyoungCards[idx++] = i;
				}
			}

			Kakegurui(0,0);

			sb.append("#").append(tc).append(" ").append(winCnt).append(" ").append(loseCnt).append("\n");
		}

		System.out.println(sb);
	}

	//게임 돌리기
	//순열을 만들기 위한 index parameter
	static void Kakegurui(int depth, int index) {
		if (depth == 9) {
			int kyuyoungSum = 0;
			int inyoungSum = 0;

			//9개 순열이 만들어졌다면
			//카드 비교
			for (int i = 0; i < 9; i++) {
				int sum = kyuyongCards[i] + perm[i];
				if (kyuyongCards[i] > perm[i]) {
					kyuyoungSum += sum;
				} else {
					inyoungSum += sum;
				}
			}

			//무승부는 체크 하지 않음
			if (kyuyoungSum > inyoungSum) {
				winCnt++;
			}

			else if (kyuyoungSum < inyoungSum) {
				loseCnt++;
			}
			
			return;
		}

		for (int i = 0; i < 9; i++) {
			if (!visited[i]) {
				visited[i] = true;
				perm[index] = inyoungCards[i];
				Kakegurui(depth + 1, index+1);
				visited[i] = false;
			}
		}
	}
}
