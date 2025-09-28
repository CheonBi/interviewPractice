const solution = (n) => {
    let i = 1;
    let fac = 1;
    
    while(fac * (i + 1) <= n){
        i++;
        fac = fac * i;
    }
    
    return i;
}