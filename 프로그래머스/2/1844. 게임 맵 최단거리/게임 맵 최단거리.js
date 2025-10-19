const solution = (maps) => {
    
    const n = maps.length;
    const m = maps[0].length;
    
    const dir = [[-1,0], [1,0], [0,-1], [0, 1]] //y,x 상 하 좌 우

    const isValid = (y,x) => {
        return y < n && y >= 0 && x < m && x >= 0;
    }
    
    const bfs = () => {
        let visited = Array.from({length : n}, () => Array.from({length : m}, () => false));
        let queue = [];
                
        queue.push([0, 0, 1]);
        visited[0][0] = true;
        
        while(!!queue.length) {
            const [y,x,z] = [...queue.shift()];
                  
            if(y === n - 1 && x === m - 1) {
                return z;
            }
            

            for(let i = 0; i < dir.length; i++) {
                const [dy,dx] = dir[i];
                let ny = y + dy;
                let nx = x + dx;
                let nz = z + 1;
                    
                //맵 유효 판단
                if(isValid(ny,nx) && !visited[ny][nx] && maps[ny][nx] === 1) {
                    queue.push([ny,nx, nz]);
                    visited[ny][nx] = true;
                }
            }
        }
        
        return -1;
    }

    return bfs();
}