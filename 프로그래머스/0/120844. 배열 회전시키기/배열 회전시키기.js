const solution = (numbers, direction) => {
    //right -> 1. delete lastIndex 2. push first
    //left -> 1. delete firstIndex 2. push last
    const result = direction === "right" ? numbers.unshift(numbers.pop()) : numbers.push(numbers.shift());
    return numbers;
    
}