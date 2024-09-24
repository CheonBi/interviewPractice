import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static int[][] delta = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}, {1,1}, {1,-1}, {-1,-1}, {-1,1}};
    static int[][] MAP;
    static boolean[][] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        MAP = new int[N][M];
        visited = new boolean[N][M];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; x++) {
                MAP[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if(MAP[y][x] == 0){ //빈공간에서 상어 만날때
                    result = Math.max(result,bfs(y,x));
                }
            }
        }

        System.out.println(result);
    }

    static int bfs(int y, int x){
        boolean[][] isVisited = new boolean[N][M];
        Queue<int[]> q = new ArrayDeque<>();

        isVisited[y][x] = true;
        q.offer(new int[]{y,x,0});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curY = cur[0];
            int curX = cur[1];
            int dist = cur[2];

            //8방 탐색
            for(int d = 0; d < 8; d++){
                int ny = curY + delta[d][0];
                int nx = curX + delta[d][1];

                if(!isValid(ny,nx) || isVisited[ny][nx]) continue;

                if(MAP[ny][nx] == 1){
                    return dist + 1;
                } else {
                    isVisited[ny][nx] = true;
                    q.offer(new int[]{ny,nx,dist+1});
                }
            }
        }

        return -1;

    }

    static boolean isValid(int ny, int nx){
        return ny >= 0 && ny < N && nx >= 0 && nx < M;
    }
    
}
