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


//Union - Find
const find = (x, parents) => {
    
    if(x != parents[x]) return parents[x] = find(parents[x], parents);

    return x;
}

const union = (x, y, parents) => {
    const rootX = find(x, parents);
    const rootY = find(y, parents);
    
    if(rootX === rootY) return;
    
    parents[rootY] = rootX;
}

const solution = (n, computers) => {
    let parents = Array.from({length: n}, (_, i) => i);
    let result = new Set();  
    
    for(let i = 0; i < n; i++) {
        for(let j = i + 1; j < n; j++) {
            if(computers[i][j] === 1) union(i, j, parents);
        }
    }
    
    for(let i = 0; i< n; i ++) {
        result.add(find(parents[i], parents));
    }
    
    return result.size;
}
