import java.io.*;
import java.util.*;

public class Main {

	static int result;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine()); // (1 ≤ N ≤ 8)

		int[][] eggs = new int[N][]; // 계란 정보 배열
		boolean[] isCrashed = new boolean[N]; // 계란을 사용 여부 확인 배열, 깨진 계란도 확인

		result = Integer.MIN_VALUE;

		// 계란 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			// 내구도 S(1 ≤ S ≤ 300)와 무게 W(1 ≤ W ≤ 300), S - W
			eggs[i] = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
		}

		crashEgg(0, 0, N, eggs);

		System.out.println(result);

	}

	static void crashEgg(int depth, int crashEggs, int N, int[][] eggs) {

		// 최대값은 항상 갱신
		result = Math.max(result, crashEggs);

		if (depth == N) {
			return;
		}

		// 손에 든 계란이 깨진경우
		// 다음 계란으로 판단
		if (eggs[depth][0] <= 0) {
			crashEgg(depth + 1, crashEggs, N, eggs);
		}

		// 아닌 경우
		else {
			for (int i = 0; i < N; i++) {
				if (i == depth || eggs[i][0] <= 0)
					continue; // 이미 깨져있는 계란이면 넘어감

				eggs[depth][0] -= eggs[i][1];
				eggs[i][0] -= eggs[depth][1];
				
				//그대로 더해버리면 최종적인 경우의 수를 구할 수 없음
				int newCrashEggs = crashEggs;
				
				if(eggs[depth][0] <= 0) newCrashEggs++;
				if(eggs[i][0] <= 0) newCrashEggs++;
				
				crashEgg(depth+1, newCrashEggs, N, eggs);
				
				//다음 유망성 판단을 위해 복구
				eggs[depth][0] += eggs[i][1];
				eggs[i][0] += eggs[depth][1];
				
			}
		}

	}
}
