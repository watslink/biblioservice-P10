
CREATE SEQUENCE public.books_id_book_seq;

CREATE TABLE public.books (
                book_id INTEGER NOT NULL DEFAULT nextval('public.books_id_book_seq'),
                title VARCHAR NOT NULL,
                author VARCHAR NOT NULL,
                publish_year INTEGER NOT NULL,
                nb_pages INTEGER NOT NULL,
                number_in_stock INTEGER NOT NULL,
                CONSTRAINT books_pk PRIMARY KEY (book_id)
);


ALTER SEQUENCE public.books_id_book_seq OWNED BY public.books.book_id;

CREATE SEQUENCE public.users_user_id_seq;

CREATE TABLE public.users (
                user_id INTEGER NOT NULL DEFAULT nextval('public.users_user_id_seq'),
                username VARCHAR NOT NULL,
                password VARCHAR NOT NULL,
                mail VARCHAR NOT NULL,
                enabled BOOLEAN NOT NULL,
                CONSTRAINT users_pk PRIMARY KEY (user_id)
);


ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;

CREATE SEQUENCE public.borrowing_borrowing_id_seq;

CREATE TABLE public.borrowing (
                borrowing_id INTEGER NOT NULL DEFAULT nextval('public.borrowing_borrowing_id_seq'),
                book_id INTEGER NOT NULL,
                user_id INTEGER NOT NULL,
                return_date DATE NOT NULL,
                extended BOOLEAN NOT NULL,
                CONSTRAINT borrowing_pk PRIMARY KEY (borrowing_id)
);


ALTER SEQUENCE public.borrowing_borrowing_id_seq OWNED BY public.borrowing.borrowing_id;

ALTER TABLE public.borrowing ADD CONSTRAINT book_borrowing_fk
FOREIGN KEY (book_id)
REFERENCES public.books (book_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.borrowing ADD CONSTRAINT user_borrowing_fk
FOREIGN KEY (user_id)
REFERENCES public.users (user_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
