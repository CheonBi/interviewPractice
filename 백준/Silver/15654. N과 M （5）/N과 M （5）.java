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
		visited = new boolean[N];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			inputs[i] = Integer.parseInt(st.nextToken());
		}

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

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				arr[depth] = inputs[i];
				dfs(depth + 1);
				visited[i] = false;
			}

		}
	}
}
