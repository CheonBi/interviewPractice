import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		// N, L, D
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());

        int total = N * L * (N+1) * 5;
		int time = 0;
		
		while(time <= total) {
            int turn = time / (L+5);
            if(turn < N){
                if(time < turn * (L+5) || time >= (turn * (L+5)) + L){
                    System.out.println(time);
                    return;
                }
            } else {
                    System.out.println(time);
                    return;
            }
            time += D;
		}
        System.out.println(time);
	}
}
