function solution(sizes) {
    var answer = 0;
    
    const rotate = sizes.map(([w,h]) => w < h ? [h,w] : [w,h] )
        
    let max = [0,0]
    
    rotate.forEach(([w,h]) => {
        max[0] = Math.max(max[0], w)
        max[1] = Math.max(max[1], h)
    })
    
    return max[0] * max[1];
}