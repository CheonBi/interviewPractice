const solution = (num_list, n) => {
    let result = [];
    for(let i = 0; i < num_list.length;) {
        let arr = [];
        
        for(let j = 0; j<n; j++){
            arr.push(num_list[i]);
            i += 1;
        }
        
        result.push(arr);

    }
    
    return result;
}