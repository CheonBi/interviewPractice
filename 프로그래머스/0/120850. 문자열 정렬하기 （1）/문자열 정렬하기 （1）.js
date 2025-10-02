const solution = (my_string) => {
    const numbers = my_string.split('').filter((v) => !isNaN(v)).map((v) => parseInt(v));
    return numbers.sort((a,b) => a - b);
} 