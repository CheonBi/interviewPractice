const solution = (begin, target, words) => {
    
    const n = words.length;
    const visited = Array.from({length : n}, () => false);
    
    const bfs = () => {
        const queue = [];
        queue.push([begin, 0]);
        
        visited[words.indexOf(begin)] = true;
        
        while(queue.length > 0) {
            const [cur, n] = queue.shift();
            
            if(cur === target) return n;
            
            for(const word of words) {
                const w = [...word];
                const c = [...cur];
                
                const cnt = w.reduce((acc, cur, idx) => {
                    if(cur != c[idx]) return acc += 1;
                    
                    return acc;
                }, 0)
                
                
                if(cnt === 1 && !visited[words.indexOf(word)]) {
                    visited[words.indexOf(word)] = true;
                    queue.push([word, n + 1]);                    
                }
            } 
        }
        
        return 0;
    }
    
  
    return bfs();
}