const solution = (numbers) => {
    const answer = new Set();
    
    const isPrime = (number) => {
        if(number < 2) return false;
        
        for(let i = 2; i <= Math.sqrt(number); i++){
            if(number % i === 0) return false;
        }
        return true;
    }
        
    
    const getPermutation = (arr, selectNumber) => {
        const results = [];
        if(selectNumber === 1) return arr.map((v) => [v]); //escape
        
        arr.forEach((fixed, index, origin) => {
            const rest = [...origin.slice(0,index), ...origin.slice(index+1)];
            const permutations = getPermutation(rest, selectNumber - 1);
            const attached = permutations.map((permutation) => [fixed, ...permutation]);
            
            results.push(...attached);
        });
        
        return results;
    }
    
    for(let i = 1; i <= numbers.length; i++) {
        const permutation = [...getPermutation([...numbers], i)];
        const primeNumbers = permutation.filter((arr) => {
            const number = +arr.join('');
            const isPrimeNum = isPrime(number);
            return isPrimeNum;
        })
        primeNumbers.forEach((arr) => {
            answer.add(+arr.join(""));
        });
    }
    
    return answer.size;
}