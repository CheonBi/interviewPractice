function solution(n, computers) {
    let answer = 0;
    
    
    const length = computers.length;
    const visited = Array.from({length : n}, () => false);
    
    for(let i = 0; i<length; i++){
        if(!visited[i]){
            dfs(i);
            answer++;
        }
    }
    
    
    function dfs(index) {
        visited[index] = true;
        
        for(let i = 0; i<length; i++){
            if(computers[index][i] === 1 && !visited[i]){
                dfs(i);
            }
        }
    }
    
    return answer;
}