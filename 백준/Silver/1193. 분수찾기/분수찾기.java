import java.io.*;

public class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());

        int stage = 1;

        int cnt = 1;
        int up = 0, down = 0;

        if(X == 1){
            System.out.println("1/1");
            return;
        }


        while (true) {
            int next = stage + 1;
            cnt += next;


            if (cnt >= X) {
                cnt -= next;
                stage++;
                break;
            } else {
                stage++;
            }
        }

        if (stage % 2 != 0) {
            up = stage;
            down = 1;

            for (int i = 0; i < stage; i++) {
                cnt++;
                if(cnt == X) {
                    break;
                } else {
                    up--;
                    down++;
                }
            }

        } else {
            up = 1;
            down = stage;

            for (int i = 0; i < stage; i++) {
                cnt++;
                if(cnt == X) {
                    break;
                } else {
                    up++;
                    down--;
                }
            }
        }

        System.out.println(up + "/" + down);
    }
}