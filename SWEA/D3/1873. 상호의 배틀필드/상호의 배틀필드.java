import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static char[][] map;
    static int[] cur;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            map = new char[H][W];
            cur = new int[2];

            for(int h = 0; h < H; h++) {
                map[h] = br.readLine().toCharArray();
                for(int w = 0; w < W; w++) {
                    if(map[h][w] == '<' || map[h][w] == '>' || map[h][w] == '^' || map[h][w] == 'v') {
                        cur[0] = h;
                        cur[1] = w;
                        break;
                    }
                }
            }

            int N = Integer.parseInt(br.readLine());
            String str = br.readLine();
            char[] command = str.toCharArray();

            for (int i = 0; i<N; i++) {
                move(cur[0], cur[1], command[i]);
            }

            StringBuilder sb = new StringBuilder();
            for(int i = 0; i<H; i++){
                for(int j = 0; j<W; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }

            System.out.print("#" + tc + " " + sb);
        }
    }

    static boolean isIn(int y, int x){
        return y >= 0 && y < map.length && x >= 0 && x < map[y].length;
    }

    static boolean isValid(int y, int x){
        return map[y][x] == '.' && map[y][x] != '-' && (map[y][x] != '#' || map[y][x] != '*');
    }

    static void move(int y, int x, char dir){
        int ny = 0;
        int nx = 0;
        switch(dir){
            case 'U' :
                ny = y-1;
                nx = x;
                if(isIn(ny, nx) && isValid(ny, nx)){
                    map[ny][nx] = '^';
                    map[y][x] = '.';
                    cur[0] = ny;
                    cur[1] = nx;
                    break;
                }
                map[y][x] = '^';
                cur[0] = y;
                cur[1] = x;
                break;

            case 'D' :
                ny = y+1;
                nx = x;
                if(isIn(ny, nx) && isValid(ny, nx)){
                    map[ny][nx] = 'v';
                    map[y][x] = '.';
                    cur[0] = ny;
                    cur[1] = nx;
                    break;
                }
                map[y][x] = 'v';
                cur[0] = y;
                cur[1] = x;
                break;

            case 'L' :
                ny = y;
                nx = x-1;
                if(isIn(ny, nx) && isValid(ny, nx)){
                    map[ny][nx] = '<';
                    map[y][x] = '.';
                    cur[0] = ny;
                    cur[1] = nx;
                    break;
                }
                map[y][x] = '<';
                cur[0] = y;
                cur[1] = x;
                break;

            case 'R' :
                ny = y;
                nx = x+1;
                if(isIn(ny, nx) && isValid(ny, nx)){
                    map[ny][nx] = '>';
                    map[y][x] = '.';
                    cur[0] = ny;
                    cur[1] = nx;
                    break;
                }
                map[y][x] = '>';
                cur[0] = y;
                cur[1] = x;
                break;

            case 'S' :
                shoot(y, x, map[y][x]);
                break;
        }

    }

    static void shoot(int y, int x, char dir){
        switch(dir){
            case '^':
                for(int i = y; i>=0; i--){
                    if(map[i][x] == '*'){
                        map[i][x] = '.';
                        break;
                    } else if(map[i][x] == '#'){
                        break;
                    }
                }
                break;

            case 'v':
                for(int i = y; i<map.length; i++){
                    if(map[i][x] == '*'){
                        map[i][x] = '.';
                        break;
                    } else if(map[i][x] == '#'){
                        break;
                    }
                }
                break;

            case '<':
                for(int i = x; i>=0; i--){
                    if(map[y][i] == '*'){
                        map[y][i] = '.';
                        break;
                    } else if(map[y][i] == '#'){
                        break;
                    }
                }
                break;

            case '>':
                for(int i = x; i<map[0].length; i++){
                    if(map[y][i] == '*'){
                        map[y][i] = '.';
                        break;
                    } else if(map[y][i] == '#'){
                        break;
                    }
                }
                break;
        }
    }
}
