import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		ArrayDeque<Integer> bridge = new ArrayDeque<>();
		ArrayDeque<Integer> trucks = new ArrayDeque<>();
		
		st = new StringTokenizer(br.readLine());

		//트럭이 입력 순서대로 나오기때문에 Queue활용
		for(int i = 0; i<n; i++) {
			trucks.offer(Integer.parseInt(st.nextToken()));
		}
		
		
		//다리를 0으로 채우고 다리의 길이 만큼 이동하면서 계산
		//like 컨베이어 벨트
		for(int i = 0; i<w; i++) {
			bridge.offer(0);
		}
		
		/*
		 * totalTime - 총시간
		 * weight - 다리위에 있는 트럭들의 무게 
		 */
		int totalTime = 0;
		int weight = 0;
		
		while(!bridge.isEmpty()) {
			totalTime++;
			weight -= bridge.poll(); //다리에 있는 트럭이 내려옴
			
			if(!trucks.isEmpty()) {
				if(trucks.peek() + weight <= L) { //현재 다리위의 트럭들의 무게와 올라올 트럭의 무게가 합이 L보다 적다면
					weight += trucks.peek(); // 다리에 트럭무게를 더함
					bridge.offer(trucks.poll()); //poll은 다리위에 올릴때 한번만 두번하면 안됨
				}
				// 합이 L보다 크다면 다리위 트럭 움직이기 0을 추가한다면 컨베이어 벨트처럼 움직으는 효과
				else bridge.offer(0); 
			}
		}
		
		System.out.println(totalTime);
	}
}