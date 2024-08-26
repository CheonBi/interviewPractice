import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		// 총 알파벳 갯수
		int[] alphaWeight = new int[26];

		// 단어 수만큼
		for (int i = 0; i < N; i++) {
			String word = br.readLine();
			
			for(int j = 0; j<word.length(); j++) {
				
				//각 자릿수만큼 10의 거듭제곰을 더해준다
				alphaWeight[word.charAt(j) - 'A'] += (Math.pow(10, word.length() - 1 -j));
			}
		}
		
		Arrays.sort(alphaWeight);
		
		int result = 0;
		int num = 9;
		
		for(int i = 25; i>=0; i--) {
			if(alphaWeight[i] == 0) continue;
			
			//가중치가 많은 수부터 9, 8 .. 곱해준다.
			result += (alphaWeight[i] * num);
			num--;
		}
		
		System.out.println(result);

	}
}
