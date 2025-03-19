const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

let input = [];

rl.on('line', function (line) {
    input = [line];
}).on('close',function(){
    
    
    str = input[0];
 
    const result = str.split('').map((ch) => {
        if(ch === ch.toUpperCase()) {
            return ch.toLowerCase();
        } else {
            return ch.toUpperCase();
        }
    })
    
    console.log(result.join(''))
    
//     for(let i = 0; i < str.length; i++){
//         if(str[i] === str[i].toUpperCase()){
//             result.push(str[i].toLowerCase());
//         } else {
//             result.push(str[i].toUpperCase());
//         }
//     }
    
//     console.log(result.join(''))

});