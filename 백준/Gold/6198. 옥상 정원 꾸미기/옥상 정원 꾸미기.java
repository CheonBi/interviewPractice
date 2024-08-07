import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/**
 * BOJ No.6198 옥상 정원 꾸미기
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long result = 0;
		
		ArrayDeque<Integer> buildings = new ArrayDeque<>();
		
		for(int i = 0; i<N; i++) {
			int height = Integer.parseInt(br.readLine());

			//이전빌딩들이 나를 볼 수 있는지체크
            //바로 직전빌딩부터 체크
			while(!buildings.isEmpty()) {
				int prev = buildings.peek();
				if(prev <= height) {
                     //높이가 현재빌딩보다 작은 빌딩은 어차피 현재 빌딩에 이후에 볼 빌딩이 없으므로 삭제
					 buildings.pop();
				} else {
                    //스택의 길이가 현재 빌딩을 볼수 있는 빌딩수                   
					result += buildings.size();
					buildings.push(height);
					break;
				}
			}
			
            //첫 빌딩 push
			if(buildings.isEmpty()) buildings.push(height);
			
		}
		
		System.out.println(result);
	}
}
