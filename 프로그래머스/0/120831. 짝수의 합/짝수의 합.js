const solution = (n) => {
   let num_list = new Array(n);
    
    for(let i = 0; i<n; i++) {
        num_list[i] = i+1;
    }
    
    return num_list.reduce((acc, cur) => {
        return cur % 2 == 0 ? acc + cur :  acc;  
    }, 0)
}