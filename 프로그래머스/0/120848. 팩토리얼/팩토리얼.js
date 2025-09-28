const solution = (n) => {
    let i = 1;
    let fac = 1;
    
    while(fac <= n){
        i++;
        fac = fac * i;
    }
    
    return i - 1;
}