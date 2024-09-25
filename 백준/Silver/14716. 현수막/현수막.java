import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] MAP;
    static boolean[][] isVisited;
    static int[][] deltas = new int[][] {{1,0}, {-1,0}, {0,1}, {0,-1}, {1,1}, {1,-1}, {-1,-1}, {-1, 1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        //입력
        st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        MAP = new int[N][M];
        isVisited = new boolean[N][M];

        for(int y = 0; y < N; y++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < M; x++){
                MAP[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        for(int y = 0; y < N; y++){
            for(int x = 0; x < M; x++){
                if(MAP[y][x] == 1 && !isVisited[y][x]){
                    result += bfs(y,x);
                }
            }
        }

        System.out.println(result);
    }

    //bfs
    static int bfs(int y, int x){
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{y,x});
        isVisited[y][x] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curY = cur[0];
            int curX = cur[1];
            for(int d = 0; d<8; d++){
                int ny = curY + deltas[d][0];
                int nx = curX + deltas[d][1];

                if(!isValid(ny, nx) || isVisited[ny][nx] || MAP[ny][nx] == 0) continue;
                q.add(new int[]{ny, nx});
                isVisited[ny][nx] = true;
            }
        }
        return 1;
    }

    static boolean isValid(int ny, int nx){
        return ny>=0 && ny<N && nx>=0 && nx<M;
    }
}
