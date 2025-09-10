const solution = (num_list) => {
    let result = [0,0];
    for(const num of num_list) {
        num % 2 == 0 ? result[0] += 1 : result[1] += 1;
    }
    
    return result;
}