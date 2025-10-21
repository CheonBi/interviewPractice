const solution = (word) => {
    const vowels = [..."AEIOU"];
    const dict = [];
        
    const dfs = (depth, str) => {
        if(depth === str.length) {
            dict.push(str);
            return;
        }
        
        for(const vowel of vowels) {
            dfs(depth, str + vowel);
        }
    }
    
    
    for(let i = 1; i <= 5; i++) {
        dfs(i, "");
    }
    
    return dict.sort().indexOf(word) + 1;
}