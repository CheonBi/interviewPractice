import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

	static int[][] reports, reals;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		for (int tc = 0; tc < 4; tc++) {
			// 첫번째 인덱스 -> 나라
			// 두번째 인덱스 -> 승 무 패 (0 1 2)
			reports = new int[6][3];
			reals = new int[6][3];

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					reports[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			System.out.println(battle(0, 1));
		}

	}

	static int battle(int home, int away) {
		if (away == 6) {
			home += 1;
			away = home + 1;
		}

		if (home == 5) {
			return isValid();
		}

		int result = 0;
		boolean flag = false;

		for (int g = 0; g < 3; g++) {
			reals[home][g]++;
			reals[away][2 - g]++;

			flag = true;

			// 끝까지 돌필요 없이 안되면 미리 탈출
			for (int j = 0; j < 6; j++) {
				for (int k = 0; k < 3; k++) {
					if (reports[j][k] < reals[j][k])
						flag = false;
				}
			}

			if (flag)
				result += battle(home, away + 1);

			reals[home][g]--;
			reals[away][2 - g]--;
		}

		if (result > 0)
			return 1;
		else
			return 0;
	}

	static int isValid() {
		for (int j = 0; j < 6; j++) {
			for (int k = 0; k < 3; k++) {
				if (reports[j][k] != reals[j][k])
					return 0;
			}
		}
		return 1;
	}
}
