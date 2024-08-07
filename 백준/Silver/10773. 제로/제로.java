import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		
		int result = 0;
		Stack<Integer> stack = new Stack();
		
		for(int i = 0, size = K; i<size; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if(num == 0 && !stack.isEmpty()) {
				stack.pop();
			} else stack.push(num);
		}
		
		while(!stack.isEmpty()) {
			result += stack.pop();
		}
		System.out.println(result);
	}
}