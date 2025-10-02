const solution = (n) => {
    const result = new Set(); //중복제거
    
    for(let i = 2; Math.pow(i,2) <= n; i++){
        while(n % i === 0) {
            result.add(i);
            n /= i;
        }
    }
    
    if(n > 1) {
        result.add(n);
    }
    
    return Array.from(result);
}