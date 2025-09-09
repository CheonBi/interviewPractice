const solution = (price) => {
    
    if(price >= Number(100000) && price < Number(300000)) return Math.trunc(price * 0.95);
    
    if(price >= Number(300000) && price < Number(500000)) return Math.trunc(price * 0.9);
    
    if(price >= Number(500000)) return Math.trunc(price * 0.8);
    
    return price;
}


//for .. of를 활용하여 할인율 배열 순회 계산
const discounts = [
    [500000, 20],
    [300000, 10],
    [100000,  5],
]

const solution = (price) => {
    for (const discount of discounts)
        if (price >= discount[0])
            return Math.floor(price - price * discount[1] / 100)
    return price
}
