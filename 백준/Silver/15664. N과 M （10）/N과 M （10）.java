import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[] arr;
    static int[] points;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        points = new int[N];
        visited = new boolean[N];
        arr = new int[M];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            points[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(points);
        dfs(0, 0);
        System.out.println(sb);

    }

    private static void dfs(int depth, int start) {
        if(depth == M){
            for(int num : arr){
                sb.append(num).append(' ');
            }
            sb.append("\n");
            return;
        }

        int prev = 0;

        for(int i = start; i<N; i++){
            if(!visited[i] && points[i] > prev){
                visited[i] = true;
                arr[depth] = points[i];
                prev = points[i];
                dfs(depth+1, i);
                visited[i] = false;
            }
        }
    }

}