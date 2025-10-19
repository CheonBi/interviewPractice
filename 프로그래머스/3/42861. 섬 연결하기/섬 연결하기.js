const find = (x, parents) => {
    if(x != parents[x]) return parents[x] = find(parents[x], parents);
    
    return x;
}

const union = (x,y, parents) => {
    x = find(x, parents);
    y = find(y, parents);
    
    if(x < y) return parents[y] = x;
    
    return parents[x] = y;
} 

const solution = (n, costs) => {
    
    let result = 0;
    
    //U-F Parents Array
    const parents = Array.from({length : n}, (_, i) => i);
    
    //간선 비용 오름차순
    costs.sort((a,b) => {
        if(a[2] === b[2]) return a[0] - b[0];
        return a[2] - b[2];
    })
    
    //U-F로 인해 이미 같은 집합에 있다면 연결하지 않음
    for(const cost of costs) {
        if(find(cost[0], parents) != find(cost[1], parents)) { //연결되어있지 않다면(같은 Root가 아니라면)
            result += cost[2];
            union(cost[0], cost[1], parents); //연결
        }
    }
    return result;
}