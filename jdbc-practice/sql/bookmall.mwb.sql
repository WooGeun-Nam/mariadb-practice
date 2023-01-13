show tables;
desc category;
desc book;
desc cart;
desc user;
desc orders;
desc order_book;

select no, name from category order by no asc;
insert into category values(null, ?);

select no, title, price, category_no from book order by no asc;
insert into book values(null, ?, ?, ?);

select no, name, phonenumber, email, password from member order by no asc;
insert into member values(null, ?, ?, ?, ?);

select no, quantity, user_no, book_no from cart order by no asc;
insert into cart values(null, ?, ?, ?);

select no, ordernumber, price, address, user_no from orders order by no asc;
insert into orders values(null, ?, ?, ?, ?);

select no, quantity, book_no, orders_no from order_book order by no asc;
insert into order_book values(null, ?, ?, ?);

select quantity, book_no from cart where user_no = 1;