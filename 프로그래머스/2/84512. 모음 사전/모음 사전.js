const solution = (word) => {
    const vowels = [..."AEIOU"];
    const dict = [];
        
    const mkdict = (length, str) => {
        if(length === str.length) {
            dict.push(str);
            return;
        }
        
        for(const vowel of vowels) {
            mkdict(length, str + vowel);
        }
    }
    
    
    for(let i = 1; i <= 5; i++) {
        mkdict(i, "");
    }
    
    return dict.sort().indexOf(word) + 1;
}