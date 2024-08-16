import java.io.*;
import java.util.*;

public class Main {

	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());

		// 사용될 S
		char[] codes = br.readLine().toCharArray();

		// 문자 조건들
		int[] checkCode = new int[4];
		st = new StringTokenizer(br.readLine());
		int validCount = 0;

		for (int i = 0; i < 4; i++) {
			checkCode[i] = Integer.parseInt(st.nextToken());
			if (checkCode[i] == 0) {
				validCount++;
			}
		}

		// 임시 비밀번호 배열
		int[] tempCodes = new int[4];

		result = 0;

		// 초기값 세팅
		for (int i = 0; i < P; i++) {
			ADD(codes[i], tempCodes, checkCode);
		}
		
		if(valid(tempCodes, checkCode)) result++;

		for (int i = P; i < S; i++) {
			int j = i - P;
			REMOVE(codes[j], tempCodes, checkCode);
			ADD(codes[i], tempCodes, checkCode);
			
			if(valid(tempCodes, checkCode)) result++;
		}

		System.out.println(result);
	}

	static void ADD(char code, int[] tempCodes, int[] checkCode) {
		switch (code) {

		case 'A':
			tempCodes[0]++;
			break;

		case 'C':
			tempCodes[1]++;
			break;

		case 'G':
			tempCodes[2]++;
			break;

		case 'T':
			tempCodes[3]++;
			break;

		}
	}

	static void REMOVE(char code, int[] tempCodes, int[] checkCode) {
		switch (code) {

		case 'A':
			tempCodes[0]--;
			break;

		case 'C':
			tempCodes[1]--;
			break;

		case 'G':
			tempCodes[2]--;
			break;

		case 'T':
			tempCodes[3]--;
			break;

		}
	}
	
	static boolean valid(int[] tempCodes, int[] checkCode) {
		for(int i = 0; i<4; i++) {
			if(tempCodes[i] < checkCode[i]) return false;
		}
		return true;
	}
}
