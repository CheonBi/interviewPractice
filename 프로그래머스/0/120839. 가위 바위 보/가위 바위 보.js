const solution = (rsp) => {
    
    // 2 -> 0
    // 0 -> 5
    // 5 -> 2
    
    const arr = [...rsp];
    
    return arr.map((v) => {
        if(v == '2') return 0;
        else if(v == '0') return 5;
        else if(v == '5') return 2;
    }).join('');
}