const solution = (my_string) => {
    const numbers = [...my_string].filter((v) => !isNaN(v));
    
    return numbers.map((v) => Number.parseInt(v, 10))
        .reduce((acc, cur) => cur += acc);
}