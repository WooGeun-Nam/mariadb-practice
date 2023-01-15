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

select b.no, b.title, ob.quantity from order_book ob join book b on ob.book_no = b.no order by ob.no asc;

select b.title, c.quantity, b.price from cart c join book b on c.book_no = b.no order by c.no;

select o.ordernumber, u.name, u.email, o.price, o.address from orders o join user u on o.user_no = u.no order by o.no;

select c.name, b.title, b.price from book b join category c on b.category_no = c.no order by b.no;