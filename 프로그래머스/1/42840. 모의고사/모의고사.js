function solution(answers) {
    //1 2 3 4 5 ....
    //2 1 2 3 2 4 2 5 ...
    //3 3 1 1 2 2 4 4 5 5 ...
    
    const first = [1,2,3,4,5];
    const second = [2,1,2,3,2,4,2,5];
    const third = [3,3,1,1,2,2,4,4,5,5];
    let answer = [];
    
    const first_ans = answers.filter((e, idx) => e === first[idx % first.length]).length;
    const second_ans = answers.filter((e, idx) => e === second[idx % second.length]).length;
    const third_ans = answers.filter((e, idx) => e === third[idx % third.length]).length;
    
    const max = Math.max(first_ans, second_ans, third_ans)
    
    if(first_ans === max) answer.push(1)
    if(second_ans === max) answer.push(2)
    if(third_ans === max) answer.push(3)
    
    return answer;
}