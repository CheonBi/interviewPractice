//올림으로 풀기
//올림으로 나머지 조각해결
const solution = (slice, n) => Math.ceil(n / slice);

//조각을 더하여 피자 판 맞추기
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
