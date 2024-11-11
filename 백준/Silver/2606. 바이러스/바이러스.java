import java.io.*;
import java.util.*;

public class Main{
	static boolean[] visited;
	static int answer;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = null;

		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		visited = new boolean[V + 1];
		answer = 0;

		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			graph.get(from).add(to);
			graph.get(to).add(from);
		}

		// 무조건 1번 시작
		DFS(graph, V, 1);
		System.out.println(answer);
	}

	static void DFS(ArrayList<ArrayList<Integer>> graph, int V, int start) {

		visited[start] = true;

		// 시작지점이 1번이므로 1번에서 연결되는 것만 DFS 돌면됨
		for (int node : graph.get(start)) {
			if (!visited[node]) {
				answer++;
				DFS(graph, V, node);
			}
		}

	}
}
