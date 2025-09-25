const solution = (numbers, K) => {
    return numbers[((K-1) * 2) % numbers.length];
}