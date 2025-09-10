const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});


rl.on('line', function (line) {
    input = line;
}).on('close', function () {
    let N = Number(input);
    for(let i = 1; i<=N; i++){
        console.log('*'.repeat(i));
    }
});