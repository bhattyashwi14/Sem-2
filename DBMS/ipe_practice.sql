--
-- PostgreSQL database dump
--

-- Dumped from database version 16.9
-- Dumped by pg_dump version 16.9

-- Started on 2026-03-03 19:31:25

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2 (class 3079 OID 16384)
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- TOC entry 5064 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


--
-- TOC entry 241 (class 1255 OID 16451)
-- Name: addbackup(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.addbackup() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
Begin
Insert into Customer_backup values (old.first_name,old.last_name, old.amount, old.payment_id);
return old;
End;
$$;


ALTER FUNCTION public.addbackup() OWNER TO postgres;

--
-- TOC entry 243 (class 1255 OID 16650)
-- Name: calculate_salary_bonus(integer, numeric); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.calculate_salary_bonus(id integer, bonus_percent numeric) RETURNS numeric
    LANGUAGE plpgsql
    AS $$
declare 
bonus_amount numeric;
begin
Select salary into bonus_amount from employee where eid=id;
bonus_amount:=bonus_amount*bonus_percent/100;
return bonus_amount;
end;
$$;


ALTER FUNCTION public.calculate_salary_bonus(id integer, bonus_percent numeric) OWNER TO postgres;

--
-- TOC entry 242 (class 1255 OID 16648)
-- Name: check_availability(bigint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.check_availability(isbn bigint) RETURNS integer
    LANGUAGE plpgsql
    AS $$
declare 
counter int;
begin 
select total_copies into counter from books where books_isbn=isbn;

if counter>0 then
return 1;
else
return 0;

end if;
end;
$$;


ALTER FUNCTION public.check_availability(isbn bigint) OWNER TO postgres;

--
-- TOC entry 244 (class 1255 OID 16664)
-- Name: check_duplicate(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.check_duplicate() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
declare 
exist_count int;
begin
select count(*)into exist_count from products where product_name=new.product_name;
if exist_count>0 then
Raise exception 'Duplicate values not allowed';
end if;
end;
$$;


ALTER FUNCTION public.check_duplicate() OWNER TO postgres;

--
-- TOC entry 245 (class 1255 OID 16676)
-- Name: issue(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.issue() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
declare
copies books13.book_count%type;
begin
select book_count into copies from books13 where btitle=new.btitle;
if copies>0 then
update books13 set book_count=copies-1 where btitle=new.btilte;
else
raise exception 'Copies not available';
return new;
end if;
end;
$$;


ALTER FUNCTION public.issue() OWNER TO postgres;

--
-- TOC entry 246 (class 1255 OID 16701)
-- Name: log_table(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.log_table() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
declare
p_name products14.product_name%type;
p_price products14.price%type;
begin
Insert into order_logs (product_id, quantity) values 
(new.product_id, new.quantity);
Select product_name,price into p_name,p_price from Products14 where product_id=new.product_id;
Update order_logs set price=p_price, product_name=p_name where product_id=new.product_id;
return new;
end;
$$;


ALTER FUNCTION public.log_table() OWNER TO postgres;

--
-- TOC entry 247 (class 1255 OID 16710)
-- Name: update_sal(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.update_sal() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
declare 
difference employees_sal.salary%type;
begin
difference=new.salary-old.salary;
if difference>0 then
raise notice 'New salary=%, Old salary=%, Difference=%',new.salary,old.salary,difference;
else
raise exception 'New salary cannot be less than old salary';
end if;
end;
$$;


ALTER FUNCTION public.update_sal() OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 226 (class 1259 OID 16573)
-- Name: agent; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.agent (
    a_id integer NOT NULL,
    a_name character varying(50),
    address character varying(100),
    a_city character varying(50),
    a_state character varying(50),
    c_name character varying(100),
    salary numeric(10,2),
    incentive numeric(10,2)
);


ALTER TABLE public.agent OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16528)
-- Name: artists; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.artists (
    a_id integer NOT NULL,
    first_name character varying(50),
    last_name character varying(50)
);


ALTER TABLE public.artists OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 16671)
-- Name: book_issue; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.book_issue (
    id integer NOT NULL,
    sid integer,
    btitle character varying(200)
);


ALTER TABLE public.book_issue OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 16642)
-- Name: books; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.books (
    books_isbn bigint NOT NULL,
    book_name character varying(200),
    total_copies integer
);


ALTER TABLE public.books OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 16666)
-- Name: books13; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.books13 (
    bid integer NOT NULL,
    btitle character varying(200),
    book_count integer
);


ALTER TABLE public.books13 OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16470)
-- Name: customer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer (
    customer_id integer NOT NULL,
    payment_id integer,
    first_name character varying(50),
    last_name character varying(50),
    amount numeric
);


ALTER TABLE public.customer OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16533)
-- Name: customer3; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer3 (
    c_id integer NOT NULL,
    first_name character varying(50),
    last_name character varying(50)
);


ALTER TABLE public.customer3 OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 16578)
-- Name: customer4; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer4 (
    cust_id integer NOT NULL,
    name character varying(50),
    age integer,
    cust_address character varying(100),
    cust_city character varying(50),
    cust_state character varying(50),
    a_id integer,
    policy_no integer
);


ALTER TABLE public.customer4 OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16477)
-- Name: customer_backup; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer_backup (
    first_name character varying(50),
    last_name character varying(50),
    amount numeric,
    payment_id integer
);


ALTER TABLE public.customer_backup OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16434)
-- Name: department; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.department (
    did integer,
    mid integer,
    dname character varying(30),
    location character varying(100)
);


ALTER TABLE public.department OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 16610)
-- Name: department5; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.department5 (
    dep_id integer NOT NULL,
    d_name character varying(100),
    course character varying(100),
    location character varying(50),
    sub_code character varying(50),
    subject character varying(100)
);


ALTER TABLE public.department5 OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16437)
-- Name: employee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employee (
    eid integer,
    ename character varying(30),
    mobile character varying(10),
    salary numeric,
    joining_date date,
    mid integer
);


ALTER TABLE public.employee OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 16703)
-- Name: employees_sal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employees_sal (
    employeeid integer NOT NULL,
    name character varying(100) NOT NULL,
    salary numeric(12,2) NOT NULL
);


ALTER TABLE public.employees_sal OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 16615)
-- Name: faculty; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.faculty (
    f_id integer NOT NULL,
    f_name character varying(100),
    address character varying(200),
    city character varying(50),
    state character varying(50),
    age integer,
    salary numeric(12,2),
    d_id integer
);


ALTER TABLE public.faculty OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16568)
-- Name: insurance_company; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.insurance_company (
    c_id integer NOT NULL,
    c_name character varying(100),
    city character varying(50),
    state character varying(50),
    policy_no integer,
    policy_name character varying(100),
    premium numeric(10,2),
    cust_id integer,
    a_id integer
);


ALTER TABLE public.insurance_company OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16431)
-- Name: manager; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.manager (
    mid integer,
    eid integer,
    mname character varying(30)
);


ALTER TABLE public.manager OWNER TO postgres;

--
-- TOC entry 239 (class 1259 OID 16694)
-- Name: order_logs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.order_logs (
    log_id integer NOT NULL,
    product_id integer,
    product_name character varying(100),
    price numeric(10,2),
    quantity integer,
    order_date date DEFAULT CURRENT_DATE
);


ALTER TABLE public.order_logs OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 16693)
-- Name: order_logs_log_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.order_logs_log_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.order_logs_log_id_seq OWNER TO postgres;

--
-- TOC entry 5065 (class 0 OID 0)
-- Dependencies: 238
-- Name: order_logs_log_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.order_logs_log_id_seq OWNED BY public.order_logs.log_id;


--
-- TOC entry 237 (class 1259 OID 16683)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    order_id integer NOT NULL,
    product_id integer,
    quantity integer NOT NULL
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16538)
-- Name: paintings; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.paintings (
    p_id integer NOT NULL,
    name character varying(100),
    a_id integer,
    listed_price numeric(10,2)
);


ALTER TABLE public.paintings OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 16656)
-- Name: products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products (
    product_id integer NOT NULL,
    product_name character varying(100)
);


ALTER TABLE public.products OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 16678)
-- Name: products14; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products14 (
    product_id integer NOT NULL,
    product_name character varying(100) NOT NULL,
    price numeric(10,2) NOT NULL
);


ALTER TABLE public.products14 OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16548)
-- Name: sales; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sales (
    s_id integer NOT NULL,
    date date,
    p_id integer,
    a_id integer,
    c_id integer,
    sales_price numeric(10,2)
);


ALTER TABLE public.sales OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 16625)
-- Name: student; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.student (
    enrl_no integer NOT NULL,
    s_name character varying(100),
    s_address character varying(200),
    s_city character varying(50),
    s_state character varying(50),
    s_age integer,
    course character varying(100),
    f_id integer,
    hobby character varying(50)
);


ALTER TABLE public.student OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 16651)
-- Name: student_marksheet; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.student_marksheet (
    rollno integer NOT NULL,
    sname character varying(100),
    div character varying(10),
    dbms integer,
    ds integer,
    total_marks integer,
    percentage numeric(5,2),
    grade character(2)
);


ALTER TABLE public.student_marksheet OWNER TO postgres;

--
-- TOC entry 4835 (class 2604 OID 16697)
-- Name: order_logs log_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_logs ALTER COLUMN log_id SET DEFAULT nextval('public.order_logs_log_id_seq'::regclass);


--
-- TOC entry 5044 (class 0 OID 16573)
-- Dependencies: 226
-- Data for Name: agent; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.agent (a_id, a_name, address, a_city, a_state, c_name, salary, incentive) FROM stdin;
1	Ravi Kumar	Address1	Ahmedabad	Gujarat	Ahmedabad Insurance Company	30000.00	5000.00
2	Priya Shah	Address2	Mumbai	Maharashtra	Mumbai Insurance Co	28000.00	4000.00
3	Amit Kapoor	Address3	Delhi	Delhi	Delhi Life Insurance	32000.00	4500.00
\.


--
-- TOC entry 5039 (class 0 OID 16528)
-- Dependencies: 221
-- Data for Name: artists; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.artists (a_id, first_name, last_name) FROM stdin;
1	Pablo	Picasso
2	Vincent	van Gogh
3	Raja	Ravi Varma
\.


--
-- TOC entry 5053 (class 0 OID 16671)
-- Dependencies: 235
-- Data for Name: book_issue; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.book_issue (id, sid, btitle) FROM stdin;
\.


--
-- TOC entry 5049 (class 0 OID 16642)
-- Dependencies: 231
-- Data for Name: books; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.books (books_isbn, book_name, total_copies) FROM stdin;
9780451524935	1984	3
9780140449136	The Odyssey	0
9780679783275	Pride and Prejudice	5
9780743273565	The Great Gatsby	2
9780553213119	Dracula	4
1	Database Systems	5
2	Data Structures	3
3	Operating Systems	2
4	Computer Networks	4
\.


--
-- TOC entry 5052 (class 0 OID 16666)
-- Dependencies: 234
-- Data for Name: books13; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.books13 (bid, btitle, book_count) FROM stdin;
\.


--
-- TOC entry 5037 (class 0 OID 16470)
-- Dependencies: 219
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customer (customer_id, payment_id, first_name, last_name, amount) FROM stdin;
1	101	Ravi	Kumar	5000
2	102	Priya	Shah	12000
3	103	Amit	Kapoor	25000
\.


--
-- TOC entry 5040 (class 0 OID 16533)
-- Dependencies: 222
-- Data for Name: customer3; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customer3 (c_id, first_name, last_name) FROM stdin;
1	Ravi	Kumar
2	Priya	Shah
3	Amit	Kapoor
\.


--
-- TOC entry 5045 (class 0 OID 16578)
-- Dependencies: 227
-- Data for Name: customer4; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customer4 (cust_id, name, age, cust_address, cust_city, cust_state, a_id, policy_no) FROM stdin;
101	Neha Mehta	30	AddrC1	Ahmedabad	Gujarat	1	501
102	Raj Sharma	45	AddrC2	Mumbai	Maharashtra	2	502
103	Karan Patel	28	AddrC3	Ahmedabad	Gujarat	1	503
104	Simran Kaur	35	AddrC4	Delhi	Delhi	3	504
\.


--
-- TOC entry 5038 (class 0 OID 16477)
-- Dependencies: 220
-- Data for Name: customer_backup; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customer_backup (first_name, last_name, amount, payment_id) FROM stdin;
Neha	Mehta	15000	104
\.


--
-- TOC entry 5035 (class 0 OID 16434)
-- Dependencies: 217
-- Data for Name: department; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.department (did, mid, dname, location) FROM stdin;
10	1	IT	Kota
11	1	HR	Ahmedabad
12	2	Finance	Mumbai
13	3	Marketing	Delhi
\.


--
-- TOC entry 5046 (class 0 OID 16610)
-- Dependencies: 228
-- Data for Name: department5; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.department5 (dep_id, d_name, course, location, sub_code, subject) FROM stdin;
1	IT Engineering	B.Tech	Ahmedabad	IT-101	Database Systems
2	Computer Science	B.Tech	Kota	CS-201	Algorithms
\.


--
-- TOC entry 5036 (class 0 OID 16437)
-- Dependencies: 218
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.employee (eid, ename, mobile, salary, joining_date, mid) FROM stdin;
101	Anil Mehta	9876500001	90000	2019-02-10	1
102	Sunita Sharma	9876500002	92000	2018-06-15	2
103	Vikram Patel	9876500003	88000	2020-09-01	14
104	Ravi Kumar	9876500004	75000	2022-05-10	1
105	Priya Shah	9876500005	68000	2023-01-15	1
106	Karan Patel	9812345678	50000	2021-11-20	2
107	Neha Gupta	9898765432	85000	2020-03-05	14
108	Amit Kapoor	9988776655	90000	2022-09-09	14
109	Sonal Jain	9876509876	72000	2023-04-18	\N
\.


--
-- TOC entry 5058 (class 0 OID 16703)
-- Dependencies: 240
-- Data for Name: employees_sal; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.employees_sal (employeeid, name, salary) FROM stdin;
1	Amit Sharma	50000.00
2	Neha Patel	60000.00
3	Rajesh Kumar	45000.00
4	Priya Mehta	70000.00
\.


--
-- TOC entry 5047 (class 0 OID 16615)
-- Dependencies: 229
-- Data for Name: faculty; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.faculty (f_id, f_name, address, city, state, age, salary, d_id) FROM stdin;
11	Dr. A K Sharma	AddrF1	Ahmedabad	Gujarat	45	80000.00	1
12	Ms. Priya Desai	AddrF2	Kota	Rajasthan	38	65000.00	2
13	Dr. Ramesh Patel	AddrF3	Surat	Gujarat	50	90000.00	1
\.


--
-- TOC entry 5043 (class 0 OID 16568)
-- Dependencies: 225
-- Data for Name: insurance_company; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.insurance_company (c_id, c_name, city, state, policy_no, policy_name, premium, cust_id, a_id) FROM stdin;
1	Ahmedabad Insurance Company	Ahmedabad	Gujarat	501	Life Secure	15000.00	101	1
2	Mumbai Insurance Co	Mumbai	Maharashtra	502	Health Shield	20000.00	102	2
3	Ahmedabad Insurance Company	Ahmedabad	Gujarat	503	Wealth Grow	18000.00	103	1
4	Delhi Life Insurance	Delhi	Delhi	504	Life Cover	22000.00	104	3
\.


--
-- TOC entry 5034 (class 0 OID 16431)
-- Dependencies: 216
-- Data for Name: manager; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.manager (mid, eid, mname) FROM stdin;
1	101	Anil Mehta
2	102	Sunita Sharma
14	103	Vikram Patel
\.


--
-- TOC entry 5057 (class 0 OID 16694)
-- Dependencies: 239
-- Data for Name: order_logs; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.order_logs (log_id, product_id, product_name, price, quantity, order_date) FROM stdin;
1	102	Notebook	50.00	7	2025-08-11
\.


--
-- TOC entry 5055 (class 0 OID 16683)
-- Dependencies: 237
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orders (order_id, product_id, quantity) FROM stdin;
201	101	5
202	102	2
203	103	4
204	102	7
\.


--
-- TOC entry 5041 (class 0 OID 16538)
-- Dependencies: 223
-- Data for Name: paintings; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.paintings (p_id, name, a_id, listed_price) FROM stdin;
101	The Weeping Woman	1	50000.00
102	Starry Night	2	80000.00
103	Shakuntala	3	60000.00
104	Guernica	1	90000.00
\.


--
-- TOC entry 5051 (class 0 OID 16656)
-- Dependencies: 233
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.products (product_id, product_name) FROM stdin;
1	Laptop
2	Smartphone
3	Tablet
5	laptop
\.


--
-- TOC entry 5054 (class 0 OID 16678)
-- Dependencies: 236
-- Data for Name: products14; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.products14 (product_id, product_name, price) FROM stdin;
101	Pen	10.00
102	Notebook	50.00
103	Marker	25.00
\.


--
-- TOC entry 5042 (class 0 OID 16548)
-- Dependencies: 224
-- Data for Name: sales; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sales (s_id, date, p_id, a_id, c_id, sales_price) FROM stdin;
201	2023-08-05	101	1	1	52000.00
202	2023-08-10	102	2	2	80000.00
203	2023-08-03	103	3	3	60000.00
204	2023-08-15	104	1	2	95000.00
\.


--
-- TOC entry 5048 (class 0 OID 16625)
-- Dependencies: 230
-- Data for Name: student; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.student (enrl_no, s_name, s_address, s_city, s_state, s_age, course, f_id, hobby) FROM stdin;
1001	Neha Mehta	AddrS1	Ahmedabad	Gujarat	20	B.Tech	11	Reading
1002	Raj Sharma	AddrS2	Kota	Rajasthan	21	B.Tech	12	Cricket
1003	Karan Patel	AddrS3	Mumbai	Maharashtra	22	B.Tech	11	Music
1004	Simran Kaur	AddrS4	Surat	Gujarat	20	B.Tech	13	Dancing
\.


--
-- TOC entry 5050 (class 0 OID 16651)
-- Dependencies: 232
-- Data for Name: student_marksheet; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.student_marksheet (rollno, sname, div, dbms, ds, total_marks, percentage, grade) FROM stdin;
101	Neha Mehta	A	78	85	163	81.00	B 
102	Raj Sharma	B	65	72	137	68.00	D 
103	Karan Patel	A	88	90	178	89.00	B 
104	Simran Kaur	C	55	60	115	57.00	E 
105	Pooja Joshi	B	92	95	187	93.00	A 
\.


--
-- TOC entry 5066 (class 0 OID 0)
-- Dependencies: 238
-- Name: order_logs_log_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.order_logs_log_id_seq', 1, true);


--
-- TOC entry 4850 (class 2606 OID 16577)
-- Name: agent agent_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.agent
    ADD CONSTRAINT agent_pkey PRIMARY KEY (a_id);


--
-- TOC entry 4840 (class 2606 OID 16532)
-- Name: artists artists_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.artists
    ADD CONSTRAINT artists_pkey PRIMARY KEY (a_id);


--
-- TOC entry 4870 (class 2606 OID 16675)
-- Name: book_issue book_issue_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_issue
    ADD CONSTRAINT book_issue_pkey PRIMARY KEY (id);


--
-- TOC entry 4868 (class 2606 OID 16670)
-- Name: books13 books13_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books13
    ADD CONSTRAINT books13_pkey PRIMARY KEY (bid);


--
-- TOC entry 4860 (class 2606 OID 16646)
-- Name: books books_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_pkey PRIMARY KEY (books_isbn);


--
-- TOC entry 4842 (class 2606 OID 16537)
-- Name: customer3 customer3_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer3
    ADD CONSTRAINT customer3_pkey PRIMARY KEY (c_id);


--
-- TOC entry 4852 (class 2606 OID 16582)
-- Name: customer4 customer4_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer4
    ADD CONSTRAINT customer4_pkey PRIMARY KEY (cust_id);


--
-- TOC entry 4838 (class 2606 OID 16476)
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (customer_id);


--
-- TOC entry 4854 (class 2606 OID 16614)
-- Name: department5 department5_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department5
    ADD CONSTRAINT department5_pkey PRIMARY KEY (dep_id);


--
-- TOC entry 4878 (class 2606 OID 16707)
-- Name: employees_sal employees_sal_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employees_sal
    ADD CONSTRAINT employees_sal_pkey PRIMARY KEY (employeeid);


--
-- TOC entry 4856 (class 2606 OID 16619)
-- Name: faculty faculty_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.faculty
    ADD CONSTRAINT faculty_pkey PRIMARY KEY (f_id);


--
-- TOC entry 4848 (class 2606 OID 16572)
-- Name: insurance_company insurance_company_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.insurance_company
    ADD CONSTRAINT insurance_company_pkey PRIMARY KEY (c_id);


--
-- TOC entry 4876 (class 2606 OID 16700)
-- Name: order_logs order_logs_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_logs
    ADD CONSTRAINT order_logs_pkey PRIMARY KEY (log_id);


--
-- TOC entry 4874 (class 2606 OID 16687)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (order_id);


--
-- TOC entry 4844 (class 2606 OID 16542)
-- Name: paintings paintings_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paintings
    ADD CONSTRAINT paintings_pkey PRIMARY KEY (p_id);


--
-- TOC entry 4872 (class 2606 OID 16682)
-- Name: products14 products14_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products14
    ADD CONSTRAINT products14_pkey PRIMARY KEY (product_id);


--
-- TOC entry 4864 (class 2606 OID 16660)
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (product_id);


--
-- TOC entry 4866 (class 2606 OID 16662)
-- Name: products products_product_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_product_name_key UNIQUE (product_name);


--
-- TOC entry 4846 (class 2606 OID 16552)
-- Name: sales sales_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sales
    ADD CONSTRAINT sales_pkey PRIMARY KEY (s_id);


--
-- TOC entry 4862 (class 2606 OID 16655)
-- Name: student_marksheet student_marksheet_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student_marksheet
    ADD CONSTRAINT student_marksheet_pkey PRIMARY KEY (rollno);


--
-- TOC entry 4858 (class 2606 OID 16631)
-- Name: student student_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_pkey PRIMARY KEY (enrl_no);


--
-- TOC entry 4887 (class 2620 OID 16665)
-- Name: products check_insertion; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER check_insertion BEFORE INSERT ON public.products FOR EACH ROW EXECUTE FUNCTION public.check_duplicate();


--
-- TOC entry 4888 (class 2620 OID 16677)
-- Name: book_issue check_issue; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER check_issue AFTER INSERT ON public.book_issue FOR EACH ROW EXECUTE FUNCTION public.issue();


--
-- TOC entry 4889 (class 2620 OID 16702)
-- Name: orders insert_logs; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER insert_logs AFTER INSERT ON public.orders FOR EACH ROW EXECUTE FUNCTION public.log_table();


--
-- TOC entry 4886 (class 2620 OID 16482)
-- Name: customer insertion_backup; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER insertion_backup AFTER DELETE ON public.customer FOR EACH ROW EXECUTE FUNCTION public.addbackup();


--
-- TOC entry 4890 (class 2620 OID 16711)
-- Name: employees_sal print_sal; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER print_sal AFTER UPDATE ON public.employees_sal FOR EACH ROW EXECUTE FUNCTION public.update_sal();


--
-- TOC entry 4883 (class 2606 OID 16620)
-- Name: faculty faculty_d_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.faculty
    ADD CONSTRAINT faculty_d_id_fkey FOREIGN KEY (d_id) REFERENCES public.department5(dep_id);


--
-- TOC entry 4885 (class 2606 OID 16688)
-- Name: orders orders_product_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.products14(product_id);


--
-- TOC entry 4879 (class 2606 OID 16543)
-- Name: paintings paintings_a_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paintings
    ADD CONSTRAINT paintings_a_id_fkey FOREIGN KEY (a_id) REFERENCES public.artists(a_id);


--
-- TOC entry 4880 (class 2606 OID 16558)
-- Name: sales sales_a_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sales
    ADD CONSTRAINT sales_a_id_fkey FOREIGN KEY (a_id) REFERENCES public.artists(a_id);


--
-- TOC entry 4881 (class 2606 OID 16563)
-- Name: sales sales_c_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sales
    ADD CONSTRAINT sales_c_id_fkey FOREIGN KEY (c_id) REFERENCES public.customer3(c_id);


--
-- TOC entry 4882 (class 2606 OID 16553)
-- Name: sales sales_p_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sales
    ADD CONSTRAINT sales_p_id_fkey FOREIGN KEY (p_id) REFERENCES public.paintings(p_id);


--
-- TOC entry 4884 (class 2606 OID 16632)
-- Name: student student_f_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_f_id_fkey FOREIGN KEY (f_id) REFERENCES public.faculty(f_id);


-- Completed on 2026-03-03 19:31:25

--
-- PostgreSQL database dump complete
--

