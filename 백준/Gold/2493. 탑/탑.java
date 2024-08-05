import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 탑의 개수 입력
		int N = Integer.parseInt(br.readLine());

		// 타워들을 저장할 Stack(Deque 활용)
		ArrayDeque<int[]> towers = new ArrayDeque<>();

		// 주어진 탑들의 높이 입력
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			//자신보다 앞 인덱스의 송신탑에 레이저를 발사 하므로 입력 받으면서 비교 가능
			
			//현재 타워
			//타워들은 정확한 인덱싱을 위해서 int[2] type
			int[] curTower = new int[] {Integer.parseInt(st.nextToken()) , i};

			//타워 스택이 
			while(!towers.isEmpty()) {
				//현재타워와 전송받을 타워 크기 비교
				//전송받을 타워가 같거나 크다면
				if(towers.peekLast()[0] >= curTower[0]) {
					sb.append(towers.peekLast()[1] + 1).append(" ");
					break;
				}
				
				//전송받을 타워가 작다는것은 필요없다는뜻
				//쓸모없는 것은 [소멸]
				towers.pollLast();
			}
			
			//타워 스택에 아무것도 없다면 전송할 타워가 없다는 의미이므로 0을 StringBuilder에 append
			if(towers.isEmpty()) sb.append('0').append(" ");

			//다음 타워의 비교를 위해 현재타워 push
			towers.offerLast(curTower);
		}
		



//		//모든 타워들 순회하면 종료
//		while (!towers.isEmpty()) {
//			
//			//타워는 왼쪽으로만 전파를 발사하기 때문에 뒤에서 poll
//			int[] curTower = towers.pollLast();
//			
//			//남은 타워의 사이즈 만큼 순회
//			for(int i = 0; i<towers.size(); i++) {
//				if(!towers.isEmpty()) { //타워가 존재할때 까지만 순회
//					int[] nextTower = towers.pollLast(); //발사받을 타워를 꺼냄
//					rebuild.offer(nextTower); //발사받고 다시 원본 스택에 넣어 순회하기 위한 스택에 offer
//					
//					//현재 타워와 다음타워의 크기를 비교
//					//다음 타워가 더 크다면 answer array update
//					//다음 타워가 더 작다면 넘어가기
//					//정확한 인덱싱을 위해서 타워에 idx 부여
//					if ((nextTower[0] >= curTower[0])) {
//						int idx = curTower[1];
//						int responseTower = nextTower[1] + 1;
//
//						ans[idx] = responseTower;
//						//다 넣었다면 다음 타워 순회를 위해 for문 탈출
//						break;
//					}
//				}
//			}
//			
//			//타워를 찾았다면 이전에 비교하지 않고 넘어간 타워들을 다시 넣어주기
//			while(!rebuild.isEmpty()) {
//				int[] rebuildTower = rebuild.pollLast();
//				towers.offer(rebuildTower);
//			}
//		}

		// answer array StringBuilder
//		for (int n : ans) {
//			sb.append(n).append(" ");
//		}

		System.out.println(sb.toString());
	}
}
