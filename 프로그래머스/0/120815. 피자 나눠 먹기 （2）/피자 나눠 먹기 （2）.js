const solution = (n) => {
    const piece = Number(6);
    
    const lcm = (n * piece) / gcd(n, piece);
    return lcm / piece;
}

const gcd = (a,b) => {
    return b === 0 ? a : gcd(b, a % b);
}