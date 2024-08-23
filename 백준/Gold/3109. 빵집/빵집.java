import java.io.*;
import java.util.*;

/**
 * 
 * BJ No. 3109 빵집 
 * 김원웅씨 ^^ 도둑질은 하지 맙시다 ^^
 *
 */
public class Main {
	static int R, C, result;
	static char[][] MAP;
	static int[][] delta = { { -1, 1 }, { 0, 1 }, { 1, 1 } }; // 우상 - 우 - 우하

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		MAP = new char[R][C];

		for (int y = 0; y < R; y++) {
			MAP[y] = br.readLine().toCharArray();
		}

		for (int y = 0; y < R; y++) {
			stealGas(y, 0);

		}

		System.out.println(result);
	}

	static boolean stealGas(int y, int x) {

		for (int d = 0; d < 3; d++) {
			int ny = y + delta[d][0];
			int nx = x + delta[d][1];

			if (isValid(ny, nx)) {
				
				if(nx == C - 1) {
					result++;
					return true;
				}
				
				MAP[ny][nx] = '-';
				
				if(stealGas(ny, nx)) return true;
			}
		}
		return false;
	}

	static boolean isValid(int ny, int nx) {
		return ny >= 0 && ny < R && nx >= 0 && nx < C && MAP[ny][nx] == '.';
	}
}
