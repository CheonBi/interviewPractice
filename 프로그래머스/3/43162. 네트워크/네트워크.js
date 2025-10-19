const solution = (n, computers) => {
    const visited = Array.from({length : n}, () => false);
    let result = 0;
        
    const bfs = (i) => {
        const queue = [];
        
        let edges = 1;
        
        if(!visited[i]) {
            queue.push(i);
            visited[i] = true;
        } else if(visited[i]) {
            return 0;
        }
        
        
        while(queue.length > 0) {
            const ci = queue.shift();
            for(let d = 0; d < n; d++) {
                if(!visited[d] && computers[ci][d] === 1) {
                    visited[d] = true;
                    queue.push(d);
                    edges++;
                }
            }
        }
        
        return 1;
    }
    
    for(let i = 0; i < n; i++) {
        result += bfs(i);
    }
    
    return result;
}