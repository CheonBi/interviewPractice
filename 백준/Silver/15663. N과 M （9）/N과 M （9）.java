import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr;
    static int[] inputs;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M];
        inputs = new int[N];


        st = new StringTokenizer(br.readLine());
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[N];

        Arrays.sort(inputs);

        dfs(0);

        System.out.println(sb.toString());
    }

    static void dfs(int depth) {
        if (depth == M) {
            for (int var : arr) {
                sb.append(var).append(' ');
            }
            sb.append("\n");
            return;
        }

        int prev = 0;

        for (int i = 0; i < N; i++) {
            if (!visited[i] && inputs[i] != prev) {
                visited[i] = true;
                prev = inputs[i];
                arr[depth] = inputs[i];
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }
}