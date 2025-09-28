const solution = (box, n) => {
    let result = 1;
    
    for (const v of box) {
        result *= Math.trunc(v / n);
    }
    
    return result;
    
}