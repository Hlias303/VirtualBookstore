-- Idempotent: safely seeds books without images for Option A (no Flyway, no consolidated data.sql)
INSERT INTO books (name, description, release_date, price)
SELECT * FROM (VALUES
    ('The Silent Patient', 'A psychological thriller about a woman''s act of violence against her husband—and of the therapist obsessed with uncovering her motive.', '2019-02-05', 15),
    ('Atomic Habits', 'An easy & proven way to build good habits & break bad ones.', '2018-10-16', 12),
    ('To Kill a Mockingbird', 'A novel of warmth and humor despite dealing with serious issues of rape and racial inequality.', '1960-07-11', 10),
    ('1984', 'A dystopian social science fiction novel and cautionary tale about the dangers of totalitarianism.', '1949-06-08', 9),
    ('The Alchemist', 'A philosophical book about a shepherd''s journey to fulfill his personal legend.', '1988-04-15', 11),

    -- ===== Modern bestsellers =====
    ('The Psychology of Money', 'Timeless lessons on wealth, greed, and happiness by Morgan Housel.', '2020-09-08', 16),
    ('Sapiens: A Brief History of Humankind', 'A sweeping narrative of humanity''s creation and evolution from a renowned historian.', '2011-01-01', 17),
    ('Educated', 'A memoir about a young woman who leaves her survivalist family to pursue an education.', '2018-02-20', 14),
    ('The Martian', 'An astronaut stranded on Mars must rely on science and ingenuity to survive.', '2011-09-28', 13),
    ('Gone Girl', 'A sinister tale of a marriage that spirals into deception and manipulation.', '2012-06-05', 13),
    ('The Girl on the Train', 'A psychological thriller of unreliable memories and missing persons.', '2015-01-05', 12),
    ('The Hunger Games', 'In a dystopian future, a girl volunteers to fight to the death for her sister.', '2008-09-14', 12),
    ('Divergent', 'In a society divided by virtues, one girl refuses to fit into a single faction.', '2011-04-25', 11),

    -- ===== Fantasy & adventure =====
    ('The Hobbit', 'A hobbit''s unexpected journey to reclaim a dragon-guarded treasure.', '1937-09-21', 13),
    ('Harry Potter and the Sorcerer''s Stone', 'A boy discovers he is a wizard and begins his education at Hogwarts.', '1997-06-26', 14),
    ('The Lord of the Rings', 'An epic quest to destroy a ring of power and save Middle-earth from darkness.', '1954-07-24', 18),

    -- ===== Classics =====
    ('The Great Gatsby', 'A mysterious millionaire''s obsession with love and the American Dream.', '1925-04-10', 8),
    ('Brave New World', 'A chilling vision of a future society ruled by technology and conditioning.', '1932-07-31', 10),
    ('The Catcher in the Rye', 'A teenager''s cynical odyssey through New York City after leaving school.', '1951-07-16', 11),
    ('Fahrenheit 451', 'In a future where books are banned, one fireman begins to question everything.', '1953-10-19', 9),
    ('The Little Prince', 'A pilot meets a young prince who teaches him what truly matters in life.', '1943-04-06', 7)
) AS v(name, description, release_date, price)

WHERE NOT EXISTS (SELECT 1 FROM books WHERE books.name = v.name);
