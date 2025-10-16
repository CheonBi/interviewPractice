const solution = (numbers, target) => {
    
    let result = 0;
    
    const dfs = (start, depth, n, visited) => {
        //타겟넘버 계산
        if(depth === n) {
            let targetNumber = 0;
            for(const element of numbers) {
                targetNumber += element;
            }
        
            if(targetNumber === target) result++;
            
            return;
        }
        
        for(let i = start; i < numbers.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                numbers[i] *= -1; 
                
                dfs(i+1, depth + 1, n, visited);
                
                visited[i] = false;
                numbers[i] *= -1;
            }
        }
        
        return;
    }
    
    for(let i = 0; i < numbers.length; i++) {
        let visited = Array.from({length: numbers.length}, () => false);
        dfs(0, 0, i+1, visited);
    }
    
    return result;
}