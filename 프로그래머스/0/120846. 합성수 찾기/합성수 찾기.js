const solution = (n) => {
    let result = 0;
    
    for(let i = 0; i <= n; i++) {
        let sum = 0;
        for(let j = 0; j <= i; j++) {
            if(i % j == 0) sum += 1;
        }
        
        if(sum >= 3) result += 1;
    }
    
    return result;
}