const solution = (my_string) => {
    const numbers = my_string.split('').filter((v) => !Number.isNaN(Number.parseInt(v, 10)));
    return numbers.map((v) => Number.parseInt(v, 10)).sort();
} 