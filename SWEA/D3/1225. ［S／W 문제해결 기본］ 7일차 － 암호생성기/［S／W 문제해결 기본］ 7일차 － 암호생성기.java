import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		for(int tc = 1; tc<=10; tc++) {
			sb.append("#").append(tc).append(" ");
			
			br.readLine();
			
			ArrayDeque<Integer> queue = new ArrayDeque<>();
			
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int i = 0; i<8; i++) {
				queue.offer(Integer.parseInt(st.nextToken()));
			}
			
			outer : while(!queue.isEmpty()) {
				for(int i = 1; i<=5; i++) {
					int code = queue.poll();
					code = code - i;
					
					if(code <= 0) {
						code = 0;
						queue.offer(code);
						break outer;
					}
					else queue.offer(code);
				}
			}
			
			for(int i = 0, size = queue.size(); i<size; i++) {
				int code = queue.poll();
				sb.append(code).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
