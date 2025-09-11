const solution = (n,k) => {
    const service = Math.trunc(n/10)
    return n * 12000 + (k - service) * 2000;
}
