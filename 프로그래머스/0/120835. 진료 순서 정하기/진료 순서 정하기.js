const solution = (emergency) => {
    const priority = [...emergency].sort((a,b) => b - a);
    const arr = emergency.map((emer) => priority.indexOf(emer) + 1);
    return arr;
}