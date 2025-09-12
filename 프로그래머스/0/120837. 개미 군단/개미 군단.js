const solution = (hp) => {
    let result = 0;
    result += Math.trunc(hp / 5);
    result += Math.trunc((hp % 5) / 3);
    result += Math.trunc(((hp % 5) % 3));
    return result;
}