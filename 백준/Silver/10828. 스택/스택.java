import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st;
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int i = 0, size = N; i<size; i++) {
			st = new StringTokenizer(br.readLine());
			
			String op = st.nextToken();
			
			switch (op) {
				case "push":
					int item = Integer.parseInt(st.nextToken());
					stack.push(item);
					break;
				case "pop":
					if(!stack.isEmpty()) {
						sb.append(stack.pop()).append("\n");
					} else {
						sb.append(-1).append("\n");
					}
					break;
				case "size":
					sb.append(stack.size()).append("\n");
					break;
				case "empty":
					if(!stack.isEmpty()) sb.append(0).append("\n");
					else sb.append(1).append("\n");
					break;
				case "top":
					if(!stack.isEmpty()) sb.append(stack.peek()).append("\n");
					else sb.append(-1).append("\n");
					break;
			}	
		}
		System.out.println(sb.toString());
	}
}