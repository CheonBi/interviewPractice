const solution = (price) => {
    
    if(price >= Number(100000) && price < Number(300000)) return Math.trunc(price * 0.95);
    
    if(price >= Number(300000) && price < Number(500000)) return Math.trunc(price * 0.9);
    
    if(price >= Number(500000)) return Math.trunc(price * 0.8);
    
    return price;
}