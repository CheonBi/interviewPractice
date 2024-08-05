import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		ArrayDeque<Integer> numbers = new ArrayDeque<>();
		int num = 1;
		
		for(int i = 0; i<n; i++) {
			int N = Integer.parseInt(br.readLine());
			
			if(N>=num) {
				for(int j = num; j<=N; j++) {
					numbers.offerLast(j);
					sb.append("+").append("\n");
				}
				num = N + 1;
			}
			
			else if(numbers.peekLast() != N) {
				System.out.println("NO");
				return;
			}
			
			numbers.pollLast();
			sb.append("-").append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
