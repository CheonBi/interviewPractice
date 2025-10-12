const solution = (k, dungeons) => {
    let answer = 0;
    const visited = new Array(dungeons.length).fill(false);
    
    const dfs = (depth, pirodo) => {
        for(let i = 0; i < dungeons.length; i++){                        
            if(!visited[i] && pirodo >= dungeons[i][0]) {
                //방문처리
                visited[i] = true;
                
                //dfs
                dfs(depth + 1, pirodo - dungeons[i][1]);
                
                //순열을 위해 처리
                visited[i] = false;
            }
        }
        answer = Math.max(answer, depth);
    }
    
    dfs(0, k);

    return answer;

}


