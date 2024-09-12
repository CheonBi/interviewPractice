SELECT count(if(age is null, user_id, null)) as users
from user_info