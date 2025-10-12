const solution = (brown, yellow) => {
    const carpet = brown + yellow;
    
    //최소 높이 -> 3
    for (let height = 3; height <= brown; height++) {
        if (carpet % height === 0) { //나머지가 없을 때만
            let weight = carpet / height;
            
            if((height - 2) * (weight - 2) === yellow) {
                return [weight, height];
            }
        }
    }
}