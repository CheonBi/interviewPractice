const solution = (land) => {
    const n = land.length;
    const m = land[0].length;
    
    const visited = Array.from({length : n}, () => Array(m).fill(0));
    const dir = [[-1, 0], [1, 0], [0, -1], [0, 1]]; //상 하 좌 우 [y,x]
    
    let result = Array(m).fill(0);
    
    const isValid = (ny, nx) => {
        return ny >= 0 && ny < n && nx >= 0 && nx < m;
    }
    
    
    const bfs = (start) => {
        const groups = new Set();
        const queue = [];
        
        queue.push(start);
        let z = 1;
        
        while(queue.length > 0) {
            const [y,x] = queue.shift();
            visited[y][x] = 1;
            groups.add(x);
            
            for(const [dy, dx] of dir) {
                const ny = y + dy;
                const nx = x + dx;
                
                if(!isValid(ny,nx)) continue;

                if(visited[ny][nx] === 0 && land[ny][nx] === 1) {
                    visited[ny][nx] = 1;
                    queue.push([ny, nx]);
                    z++;
                }
            }
        }
        
        for(const idx of groups) {
            result[idx] += z;
        }
        
        return;
    }
    
    
    for(let i = 0; i < m; i++) {
        for(let j = 0; j < n; j++) {
            //기름 위치에서 bfs
            if(land[j][i] === 1 && visited[j][i] === 0) {
                bfs([j,i]); 
            } 
        }
    }
    
    return Math.max(...result);
}