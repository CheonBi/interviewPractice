const solution = (balls, share) => {
    return Math.round(factorial(balls) / (factorial(balls - share) * factorial(share)));
}

const factorial = (n) => {
    if(n === 0 || n === 1) return 1;
    return n * factorial(n-1);
}