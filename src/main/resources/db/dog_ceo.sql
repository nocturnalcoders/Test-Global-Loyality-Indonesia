create table breeds
(
    id    INT          NOT NULL AUTO_INCREMENT,
    breed VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

create table sub_breeds
(
    id        INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    sub_breed VARCHAR(255) NOT NULL,
    breed_id  INT          NOT NULL,
    FOREIGN KEY (breed_id) REFERENCES breeds (id),
    UNIQUE (sub_breed, breed_id)
);

create table images
(
    id           INT          NOT NULL AUTO_INCREMENT,
    url          VARCHAR(255) NOT NULL,
    breed_id     INT          NOT NULL,
    sub_breed_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (breed_id) REFERENCES breeds (id),
    FOREIGN KEY (sub_breed_id) REFERENCES sub_breeds (id)
);

-- Insert data into tables from https://dog.ceo/api/breeds/list/all
insert into breeds(breed)
values ('shiba');
insert into sub_breeds(sub_breed, breed_id)
values ('shiba', 1);
insert into images(url, breed_id, sub_breed_id)
values ('https://images.dog.ceo/breeds/shiba/shiba-1.jpg', 1, 1);

insert into breeds(breed)
values ('sheepdog');
insert into sub_breeds(sub_breed, breed_id)
values ('english', 2);
insert into images(url, breed_id, sub_breed_id)
values ('https://images.dog.ceo/breeds/sheepdog-english/n02105641_1003.jpg', 2, 2);

insert into breeds(breed)
values ('terrier');
insert into sub_breeds(sub_breed, breed_id)
values ('australian', 3);
insert into images(url, breed_id, sub_breed_id)
values ('https://images.dog.ceo/breeds/terrier-australian/n02096294_1003.jpg', 3, 3);