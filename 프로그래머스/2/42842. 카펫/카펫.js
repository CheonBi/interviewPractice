function solution(brown, yellow) {
    const sum = brown + yellow;
    
    for(var y = 3; y <= sum / y; y++){
        if(sum % y == 0) {
            var x = sum / y;
            
            if((y-2) * (x-2) === yellow) {
                return [x,y]
            }
        }
    }
}