INSERT INTO public.users (username, mail, password, enabled)
VALUES
('melissa.hamon', 'biblioocclient@gmail.com', '$2a$10$Z4F50wfLr4Iuc1HckouM9OoJIkr4O/Gi9TvC0PAkJp3vBb23o.MvC', true), 
('emile.jacob', 'emile.jacob@fakeemail.com', '$2a$10$Z4F50wfLr4Iuc1HckouM9OoJIkr4O/Gi9TvC0PAkJp3vBb23o.MvC', true), 
('carla.gilles', 'carla.gilles@fakeemail.com', '$2a$10$Z4F50wfLr4Iuc1HckouM9OoJIkr4O/Gi9TvC0PAkJp3vBb23o.MvC', true), 
('bastien.gueguen', 'bastien.gueguen@fakeemail.com', '$2a$10$Z4F50wfLr4Iuc1HckouM9OoJIkr4O/Gi9TvC0PAkJp3vBb23o.MvC', true), 
('felix.barreau', 'felix.barreau@fakeemail.com', '$2a$10$Z4F50wfLr4Iuc1HckouM9OoJIkr4O/Gi9TvC0PAkJp3vBb23o.MvC', true), 
('yasmine.gerard', 'yasmine.gerard@fakeemail.com', '$2a$10$Z4F50wfLr4Iuc1HckouM9OoJIkr4O/Gi9TvC0PAkJp3vBb23o.MvC', true), 
('anael.lebreton', 'anael.lebreton@fakeemail.com', '$2a$10$Z4F50wfLr4Iuc1HckouM9OoJIkr4O/Gi9TvC0PAkJp3vBb23o.MvC', true), 
('charlotte.meyer', 'charlotte.meyer@fakeemail.com', '$2a$10$Z4F50wfLr4Iuc1HckouM9OoJIkr4O/Gi9TvC0PAkJp3vBb23o.MvC', true), 
('valentin.allain', 'valentin.allain@fakeemail.com', '$2a$10$Z4F50wfLr4Iuc1HckouM9OoJIkr4O/Gi9TvC0PAkJp3vBb23o.MvC', true), 
('jeremy.carlier', 'jeremy.carlier@fakeemail.com', '$2a$10$Z4F50wfLr4Iuc1HckouM9OoJIkr4O/Gi9TvC0PAkJp3vBb23o.MvC', true), 
('alice.fernandes', 'alice.fernandes@fakeemail.com', '$2a$10$Z4F50wfLr4Iuc1HckouM9OoJIkr4O/Gi9TvC0PAkJp3vBb23o.MvC', true), 
('noemie.lucas', 'noemie.lucas@fakeemail.com', '$2a$10$Z4F50wfLr4Iuc1HckouM9OoJIkr4O/Gi9TvC0PAkJp3vBb23o.MvC', true), 
('clement.bataille', 'clement.bataille@fakeemail.com', '$2a$10$Z4F50wfLr4Iuc1HckouM9OoJIkr4O/Gi9TvC0PAkJp3vBb23o.MvC', true), 
('yasmine.bourgeois', 'yasmine.bourgeois@fakeemail.com', '$2a$10$Z4F50wfLr4Iuc1HckouM9OoJIkr4O/Gi9TvC0PAkJp3vBb23o.MvC', true), 
('yuna.julien', 'yuna.julien@fakeemail.com', '$2a$10$Z4F50wfLr4Iuc1HckouM9OoJIkr4O/Gi9TvC0PAkJp3vBb23o.MvC', true); 


INSERT INTO public.books (title, author, publish_year, nb_pages, number_in_stock, nb_total)
VALUES
('Contes', 'Hans Christen Andersen', 1837, 150, 1, 2),
('L''étranger', 'Albert Camus', 1942, 328, 4, 5),
('Voyage au bout de la nuit', 'Celine', 1932, 252, 2, 3),
('1984', 'George Orwell', 1949, 198, 42, 42);
('La communauté de l''anneau', 'Tolkien', 1972, 352, 0, 3),
('Les deux tours', 'Tolkien', 1972, 306, 1, 2),
('Le retour du roi', 'Tolkien', 1973, 312, 0, 4),
('Le hobbit', 'Tolkien', 1969, 354, 0, 1),
('Shining', 'Stephen King', 1979, 254, 0, 1);

INSERT INTO public.borrowing (book_id, user_id, return_date, extended)
VALUES
(1, 1, '2019-06-28', false),
(2, 1, '2019-04-28', false),
(3, 1, '2019-03-28', true),
(5, 1, '2019-12-25', false),
(9, 1, '2019-12-25', false),
(5, 2, '2019-12-25', false),
(6, 3, '2019-12-25', false),
(7, 2, '2019-12-30', false),
(7, 3, '2019-12-29', false),
(7, 4, '2019-12-28', false),
(7, 5, '2019-12-27', false),
(8, 3, '2019-12-25', false);

INSERT INTO public.reservation (book_id, user_id, date_mailing, date_of_reservation)
VALUES
(6, 1, '2019-11-14', '2019-11-02'),
(7, 1, null, '2019-11-02'),
(8, 2, null, '2019-11-10'),
(8, 3, null, '2019-11-01');

