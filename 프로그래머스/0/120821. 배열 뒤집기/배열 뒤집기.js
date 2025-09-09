const solution = (num_list) => {
    const result = [];
    
    for(let i = num_list.length - 1; i>=0; i--) {
        result.push(num_list[i]);
    }
    
    return result;
}

//reverse method
const solution = (num_list) => num_list.reverse();

//with unshift
//unshift() : 배열의 맨 앞에 값을 추가한다.
const solution = (num_list) => {
    const result = [];

    num_list.forEach((num) => result.unshift(num));

    return result;
}
