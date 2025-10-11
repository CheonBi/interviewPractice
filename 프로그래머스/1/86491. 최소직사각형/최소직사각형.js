const solution = (sizes) => {
    
    let result = [0,0];
    
    for(const rect of sizes) {
        const [w,h] = rect;
        
        const max = Math.max(w,h);
        const min = Math.min(w,h);
        
        if(max > result[0]) result[0] = max;
        if(min > result[1]) result[1] = min;
    }
    
    return result[0] * result[1];
}