import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * BOJ No.2493 색종이 - 2 
	 */
	
	static int[][] white = new int[101][101];
	static boolean[][] visited = new boolean[101][101];
	
	static int[] dy = {0, 1, -1, 0};
	static int[] dx = {1, 0, 0, -1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int cnt = 0;
		
		// 검은색 종이 채우기
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			
			for(int r = y; r<y+10; r++) {
				for(int c = x; c<x+10; c++) {
					white[r][c] = 1;
				}
			}
		}
		
		/*
		 * 1(검은색 종이)에 인접한 0의 총 개수가 변의 길이 
		 */
        
		for(int y = 0; y<101; y++) {
			for(int x = 0; x<101; x++) {
				if(white[y][x] == 1) {
					for(int d = 0; d<4; d++) {
						int ny = y + dy[d];
						int nx = x + dx[d];
						
						/*
						 * 하얀 색종이 범위 밖이거나
						 * 검은색 종이거나
						 * 이미 더한 변이라면 넘어감
						 */
						if(!valid(ny,nx)) continue;
						else if(white[ny][nx] == 1) continue;
						else if(visited[ny][nx]) continue;
						else {
							cnt++;
						}
					}
				}
			}
		}
		
		
//		for(int r = 0; r<white.length; r++) {
//			for(int c = 0; c<white[0].length; c++) {
//				System.out.print(white[r][c]);
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		System.out.println(cnt);
		
	}
	
	
	//배열(하얀 색종이) 안에 있는지 검사하는 메소드
	static boolean valid(int ny, int nx) {
		return ny>=0 && ny<101 && nx>=0 && nx<101;
	}
	
}
