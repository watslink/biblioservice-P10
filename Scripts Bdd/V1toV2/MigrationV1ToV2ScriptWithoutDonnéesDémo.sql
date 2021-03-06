

CREATE SEQUENCE public."reservation_reservation_id_seq";

CREATE TABLE public."reservation" (
                "reservation_id" INTEGER NOT NULL DEFAULT nextval('public."reservation_reservation_id_seq"'),
                "book_id" INTEGER NOT NULL,
                "user_id" INTEGER NOT NULL,
                "date_mailing" DATE,
                "date_of_reservation" DATE NOT NULL,
                CONSTRAINT "reservation_pk" PRIMARY KEY ("reservation_id")
);


ALTER SEQUENCE public."reservation_reservation_id_seq" OWNED BY public."reservation"."reservation_id";

ALTER TABLE public."books" ADD COLUMN "nb_total" INTEGER NOT NULL;

ALTER TABLE public."reservation" ADD CONSTRAINT "books_reservation_fk"
FOREIGN KEY ("book_id")
REFERENCES public."books" ("book_id")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public."reservation" ADD CONSTRAINT "users_reservation_fk"
FOREIGN KEY ("user_id")
REFERENCES public."users" ("user_id")
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
