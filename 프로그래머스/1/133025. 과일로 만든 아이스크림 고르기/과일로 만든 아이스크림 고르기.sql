select f.flavor
from first_half as f, icecream_info as i 
where f.total_order > 3000 and f.flavor = i.flavor and i.ingredient_type = 'fruit_based'
