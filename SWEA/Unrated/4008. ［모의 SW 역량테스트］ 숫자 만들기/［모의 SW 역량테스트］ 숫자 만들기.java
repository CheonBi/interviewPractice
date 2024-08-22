import java.util.*;
import java.io.*;

public class Solution {

	/*
	 * numbers 계산될 숫자들 배열 
	 * orders 최종적으로 사용 될 연산자들 순서 배열
	 * operaters 연산자 사용여부 및 남은 개수를 위한 배열
	 */
	static int[] operaters, orders, numbers;
	static int N, MIN, MAX;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			numbers = new int[N]; // 계산될 숫자들 배열
			orders = new int[N - 1]; // 최종적으로 사용 될 연산자들 순서 배열
			operaters = new int[4]; // operate 사용여부 및 남은 개수를 위한 배열

			MIN = Integer.MAX_VALUE;
			MAX = Integer.MIN_VALUE;

			// 연산자들 상태 입력
			// 배열의 index -> 연산자 구분 key
			// 0 : +, 1 : -, 2: *, 3: /
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				operaters[i] = Integer.parseInt(st.nextToken());
			}
//			System.out.println(Arrays.toString(operaters));

			// 숫자들 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			
			operateCardShuffle(0);
			
			sb.append("#").append(tc).append(" ").append(MAX-MIN).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	static void operateCardShuffle(int depth) {
		if (depth == N-1) {
			calculation();
			return;
		}
		
		for(int i = 0; i<4; i++) {
			if(operaters[i] != 0) {
				orders[depth] = i;
				operaters[i]--;
				operateCardShuffle(depth+1);
				operaters[i]++;
			}
		}
	}

	static void calculation() {
		int result = numbers[0];
		
		for (int i = 0; i < N - 1; i++) {
			int operate = orders[i];
			
			switch (operate) {
			case 0:
				result = result + numbers[i+1];
				break;
				
			case 1:
				result = result - numbers[i+1];
				break;
				
			case 2:
				result = result * numbers[i+1];
				break;
				
			case 3:
				if(numbers[i+1] == 0) break;
				result = result / numbers[i+1];
				break;
			}
		}
		
		MIN = Math.min(MIN, result);
		MAX = Math.max(MAX, result);
	}
}
