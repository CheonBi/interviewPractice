import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] cups = new int[N + 1];
		int answer = -1;
		cups[X] = 1; // 간식이 있는 곳은 1로 변경

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			// 위치를 바꿀 인덱스
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			int tmp = cups[A];
			cups[A] = cups[B];
			cups[B] = tmp;

		}

		for (int i = 0; i < cups.length; i++) {
			if (cups[i] == 1) {
				answer = i;
			}
		}

		System.out.println(answer);
		br.close();
	}
}
