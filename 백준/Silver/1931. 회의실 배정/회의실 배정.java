import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		/*
		  time[][0] 은 시작시점
		  time[][1] 은 종료시점 
		*/
		int[][] time = new int[N][2];
				
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			time[i][0] = Integer.parseInt(st.nextToken());	// 시작시간 
			time[i][1] = Integer.parseInt(st.nextToken());	// 종료시간 
		}
		
		
		// 끝나는 시간을 기준으로 정렬하기 위해 compare 재정의 
		Arrays.sort(time, new Comparator<int[]>() {
			
			@Override
			public int compare(int[] o1, int[] o2) {
				
				// 종료시간이 같을 경우 시작시간이 빠른순으로 정렬해야한다.  
				if(o1[1] == o2[1]) {
					return o1[0] - o2[0];
				}
				
				return o1[1] - o2[1];
			}
 
		});
		
		int count = 0;
		int prev = 0;
        
		for(int i = 0; i < N; i++) {
			
			// 회의 종료시간과 다음 회의 시작시간 비교
            // [다음 회의 시작시간 > 회의 종료 시간] 이어야한다. 
			if(prev <= time[i][0]) {
				prev = time[i][1];
				count++;
			}
		}
		
		System.out.println(count);
	}
 
}