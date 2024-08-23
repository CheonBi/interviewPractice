/*
 * BOJ NO.1759 암호만들기
 */

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		//암호 구성에 필요한 변수들
		st = new StringTokenizer(br.readLine());
		
		// (3 ≤ L ≤ C ≤ 15) 
		int L = Integer.parseInt(st.nextToken()); //암호 자리수
		int C = Integer.parseInt(st.nextToken()); //사용될 문자의 종류
		
		
		char[] key = new char[L]; //암호제작을 위한 배열
		char[] alphabet = new char[C]; //사용될 문자 배열
		boolean[] isUsed = new boolean[C];
		
		//재료배열 입력
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<C; i++) {
			alphabet[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(alphabet);
		cypher(0, 0, key, alphabet, isUsed, L, C);
		
		br.close();
	}
	
	static void cypher(int depth, int start, char[] key, char[] alphabet, boolean[] isUsed, int L, int C) {
		if(depth == L) {
			StringBuilder sb = new StringBuilder();
			
			int vowel = 0;
			int con = 0;
			
			//배열을 순회하며 모음 자음 구성확인
			for(int i = 0; i<L; i++) {
				if(isVowels(key[i])) vowel++;
				else con++;
			}
			
			//암호 구성이 유요한지 검사
			if(isValid(vowel, con)) {
				sb.append(key);
				System.out.println(sb.toString());
			}
			return;
		}
		
		for(int i = start; i<C; i++) {
			if(!isUsed[i]) {
				isUsed[i] = true;
				key[depth] = alphabet[i];
				cypher(depth+1, i+1, key, alphabet, isUsed, L, C);
				isUsed[i] = false;
			}
		}
	}
	
	//자음 판별 메소드
	static boolean isVowels(char alpha) {
		return alpha == 'a' || alpha == 'e' || alpha == 'i' || alpha == 'o' || alpha == 'u';
	}
	
	//암호 조건 판별 메소드
	static boolean isValid(int vowel, int con) {
		return (vowel >= 1) && (con >= 2);
	}
}
