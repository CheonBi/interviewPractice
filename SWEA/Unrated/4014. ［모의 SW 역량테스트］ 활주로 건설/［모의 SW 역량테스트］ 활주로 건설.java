import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int T, N, X, result;
	static int[][] MAP;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //
			X = Integer.parseInt(st.nextToken()); // 활주로 길이
			result = 0;

			MAP = new int[N][N];

			// 입력
			for (int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < N; x++) {
					MAP[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			makeRow();
			makeCol();
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
		

	}

	static void makeRow() {
		for (int y = 0; y < N; y++) {
			int[] tempArr = new int[N];
			for (int x = 0; x < N; x++) {
				tempArr[x] = MAP[y][x];

			}
			if(calc(tempArr)) {
				result++;
			}
		}
	}
	
	static void makeCol() {
		for (int x = 0; x < N; x++) {
			int[] tempArr = new int[N];
			for (int y = 0; y < N; y++) {
				tempArr[y] = MAP[y][x];

			}
			if(calc(tempArr)) {
				result++;
			}
		}
	}


	static boolean calc(int[] tempArr) {
		int cnt = 1;
		
		for(int i = 1; i<tempArr.length; i++) {
			if(tempArr[i-1] == tempArr[i])
				cnt++;
			else if(tempArr[i-1] != tempArr[i]) {
				if(tempArr[i-1] - tempArr[i] == 1 && cnt >= 0) { //내리막일때 앞으로 나올 길이가 X만큼 같은 높이로 확보되어야함
					cnt = 1 - X;
				} else if(tempArr[i-1] - tempArr[i] == -1 && cnt >= X) { //오르막이면 이전에 X만큼 같은 높이로 확보되어야함
					cnt = 1;
				} else {
					cnt = -1;
					break;
				}
			}
		}
		
		if(cnt >= 0) return true;
		
		return false;
	}
}
