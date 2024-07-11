import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static int R,C,T;

    static int[][] room;
    static int[][] dust;
    static int cleaner = 0;

    //먼지확산
    public static void spread(){
        for(int i=0;i<room.length;i++){
            for(int j=0;j<room[0].length;j++){
                int minidust = room[i][j] / 5;
                for(int k=0;k<4;k++){
                    int nx = i+dx[k];
                    int ny = j+dy[k];
                    if((nx >= 0 && nx < room.length && ny >= 0 && ny < room[0].length) && room[nx][ny] != -1 ){
                        dust[nx][ny] += minidust;
                        room[i][j] -= minidust;
                    }
                }
            }
        }

        for(int i=0;i<room.length;i++){
            for(int j=0;j<room[0].length;j++){
                room[i][j] += dust[i][j];
                dust[i][j] = 0;
            }
        }
    }

    //공기청정기
    public static void wind(){
        int top = cleaner;
        int down = cleaner + 1;

        //#1. 위쪽 공기청정기
        // - 반시계 순환
        // 청정기를 기준으로 아래 -> 왼쪽 -> 위 -> 오른쪽 당김
        for(int i = top - 1; i > 0; i--){
            room[i][0] = room[i-1][0];
        }

        for(int i = 0; i < C-1; i++){
            room[0][i] = room[0][i+1];
        }

        for(int i = 0; i < top; i++){
            room[i][C-1] = room[i+1][C-1];
        }

        for(int i = C-1; i > 1; i--){
            room[top][i] = room[top][i-1];
        }

        room[top][1] = 0;

        //#2. 아래쪽 공기청정기
        // - 시계 순환
        // 청정기를 기준으로 위 -> 왼쪽 -> 아래 -> 오른쪽 당김
        for(int i = down + 1; i < R-1; i++){
            room[i][0] = room[i+1][0];
        }

        for(int i = 0; i < C - 1; i++){
            room[R-1][i] = room[R-1][i+1];
        }

        for(int i = R - 1; i > down; i--){
            room[i][C-1] = room[i-1][C-1];
        }

        for(int i = C - 1; i > 1; i--){
            room[down][i] = room[down][i-1];
        }
        room[down][1] = 0;
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        room = new int[R][C];
        dust = new int[R][C];

        for(int i = 0; i < R; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++){
                room[i][j] = Integer.parseInt(st.nextToken());
                if(room[i][j] == -1 && cleaner == 0){
                    cleaner =  i;
                }
            }
        }

        for(int i = 0; i < T; i++){
            spread();
            wind();
        }

        int result = 0;
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(room[i][j] == -1){
                    continue;
                }
                result += room[i][j];
            }
        }

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();

    }
}
