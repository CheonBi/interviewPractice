const solution = (n,wires) => {
    let result = 99999;
    
    
    const graph = Array.from({length: n + 1}, () => []);
    
    
    for(const [from, to] of wires) {
        graph[from].push(to);
        graph[to].push(from);
    }
    
    //그래프 순회(노드 개수 구하기)
    const bfs = (start, except) => {
        let count = 0; //node 개수
        let queue = [start];
        
        let visited = Array.from({length : n + 1}, () => false);
        
        //시작노드 방문처리
        visited[start] = true;
        
        //종료
        while(queue.length) {
            let index = queue.shift();
            
            graph[index].forEach((element) => {
                if(visited[element] === false && element !== except) {
                    visited[element] = true;
                    queue.push(element);
                }
            })
            count++;
        }
        return count;
    }
    
    //끊고 그래프 순회 후 절대값 구하기
    for(const [from,to] of wires) {
        result = Math.min(result, Math.abs(bfs(from,to) - bfs(to, from)));
    }
    
    return result;
}