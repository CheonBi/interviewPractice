import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= 10; tc++) {

			int N = Integer.parseInt(br.readLine());

			char[] arr = new char[N];
			arr = br.readLine().toCharArray().clone();

			int answer = isValid(arr);

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.println(sb);
	}

	static int isValid(char[] arr) {
		ArrayDeque<Character> stack = new ArrayDeque<>();
		int size = arr.length;

		for (int i = 0; i < size; i++) {
			if (stack.isEmpty()) {
				stack.push(arr[i]);
				continue;
			}

			//
			char oper = stack.peekFirst();
			char pushOper = arr[i];

			if((oper == '(' && pushOper == ')')) {
				stack.pop();
			}
			
			else if((oper == '<' && pushOper == '>')) {
				stack.pop();
			}
			
			else if((oper == '{' && pushOper == '}')) {
				stack.pop();
			}
			
			else if((oper == '[' && pushOper == ']')) {
				stack.pop();
			}
			
			else {
				stack.push(pushOper);
			}
		}
		
		if(stack.isEmpty()) return 1;
		else return 0;
	}
}
