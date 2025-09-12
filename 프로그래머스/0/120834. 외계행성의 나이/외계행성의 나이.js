
const solution = (age) => {
    const age_list = [...'abcdefghij'];
    return [...age.toString()].map(a=> age_list[a]).join("");
}