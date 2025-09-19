const solution = (dot) => {
    const [pointX, pointY] = dot;
    const phase = pointX * pointY > 0;
    return pointX > 0 ? (phase ? 1 : 4) : (phase ?  3 : 2);
}