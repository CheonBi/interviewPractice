const solution = (slice, n) => {
    let piece = Number(slice);
    
    while(true) {
        if(piece >= n) {
            break;
        }
        piece += slice;
    }
    
    return Math.trunc(piece / slice);
}