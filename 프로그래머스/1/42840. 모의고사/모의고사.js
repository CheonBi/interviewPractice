const solution = (answers) => {
    
    let result = [];
    
    const patternA = [1,2,3,4,5];
    const patternB = [2,1,2,3,2,4,2,5];
    const patternC = [3,3,1,1,2,2,4,4,5,5];
    
    const supoA = answers.filter((v,index) => v == patternA[index%5]).length;
    const supoB = answers.filter((v,index) => v == patternB[index%8]).length;
    const supoC = answers.filter((v,index) => v == patternC[index%10]).length;
    
    const max = Math.max(supoA, Math.max(supoB, supoC));
    
    if(supoA === max) result.push(1);
    if(supoB === max) result.push(2);
    if(supoC === max) result.push(3);
    
    return result;
}