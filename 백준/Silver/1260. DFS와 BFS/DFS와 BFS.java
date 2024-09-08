import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		// 입력
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 정점 개수
		int M = Integer.parseInt(st.nextToken()); // 간선 개수
		int V = Integer.parseInt(st.nextToken()); // 시작 정점

		// 인접리스트
		ArrayList<Integer>[] adjList = new ArrayList[N + 1];
		boolean[] isVisited = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			adjList[from].add(to);
			adjList[to].add(from);
		}

		for (int i = 1; i <= N; i++) {
			Collections.sort(adjList[i]);
		}

		dfs(V, isVisited, adjList, sb);
		sb.append("\n");
		bfs(V, N, adjList, sb);

		System.out.println(sb.toString());
	}

	static void dfs(int depth, boolean[] isVisited, ArrayList<Integer>[] adjList, StringBuilder sb) {

		isVisited[depth] = true;
		sb.append(depth).append(" ");

		for (int next : adjList[depth]) {
			if (!isVisited[next]) {
				dfs(next, isVisited, adjList, sb);
			}
		}

	}

	static void bfs(int start, int N, ArrayList<Integer>[] adjList, StringBuilder sb) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] isVisited = new boolean[N + 1];

		q.offer(start);
		isVisited[start] = true;

		while (!q.isEmpty()) {
			int cur = q.poll();
			sb.append(cur).append(" ");

			for (int next : adjList[cur]) {
				if (!isVisited[next]) {
					isVisited[next] = true;
					q.offer(next);
				}
			}
		}
	}
}
