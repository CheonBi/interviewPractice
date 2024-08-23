import java.io.*;
import java.util.*;

public class Main{

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int result = 0;

		if (N % 5 == 0) {
			result += N / 5; // 5로 나누어 질때
		} 
		else { // 안되면 3을 하나씩 제거
			while (N > 0) {
				if (N < 3) {
					result = -1;
					break;
				}

				if (N % 5 == 0) {
					result += N / 5;
					break;
				}
				
				N -= 3;
				result++;
			}

		}

		System.out.println(result);
	}
}
