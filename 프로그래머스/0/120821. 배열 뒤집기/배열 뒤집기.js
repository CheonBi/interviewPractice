const solution = (num_list) => {
    const result = [];

    num_list.forEach((num) => result.unshift(num));

    return result;
}