INSERT INTO public.books (title, author, publish_year, nb_pages, number_in_stock, nb_total)
VALUES
('La communauté de l''anneau', 'Tolkien', 1972, 352, 0, 3),
('Les deux tours', 'Tolkien', 1972, 306, 1, 2),
('Le retour du roi', 'Tolkien', 1973, 312, 0, 4),
('Le hobbit', 'Tolkien', 1969, 354, 0, 1),
('Shining', 'Stephen King', 1979, 254, 0, 1);

INSERT INTO public.borrowing (book_id, user_id, return_date, extended)
VALUES
(5, 1, '2019-12-25', false),
(9, 1, '2019-12-25', false);

INSERT INTO public.reservation (book_id, user_id, date_mailing, date_of_reservation)
VALUES
(6, 1, '2019-11-14', '2019-11-02'),
(7, 1, null, '2019-11-02'),
(8, 2, null, '2019-11-10'),
(8, 3, null, '2019-11-01');

