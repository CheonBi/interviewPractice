const solution = (numbers) => {
    const nums = numbers.sort((a,b) => a-b);
    
    return nums[nums.length-1] * nums[nums.length-2];
}