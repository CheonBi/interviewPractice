const solution = (d, budget) => {
    let result = 0;
    
    d.sort((a,b) => a-b);
    
    for(const part of d) {
        if(budget - part < 0) return result;
        
        budget -= part
        result++;
    }
    
    return result;
}