SELECT book_id, date_format(published_date, '%Y-%m-%d') as published_date 
from book 
where category='인문' and year(published_date)='2021'

# select *
# from book;