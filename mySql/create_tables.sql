use `blogDB`;
create table `user` (
                        `id` INTEGER NOT NULL AUTO_INCREMENT,
                        `first_name` VARCHAR(255) NOT NULL,
                        `last_name` VARCHAR(255) NOT NULL,
                        `password` VARCHAR(255) NOT NULL,
                        `email` varchar(255) NOT NULL,
                        `registration_date` DATE NOT NULL ,

                        PRIMARY KEY(`id`),
                        UNIQUE (id)
);

create table `article` (
                           `id` INTEGER NOT NULL AUTO_INCREMENT,
                           `title` VARCHAR(255) NOT NULL,
                           `text` TEXT NOT NULL,
                           `status` ENUM('PRIVATE', 'DRAFT'),
                           `author_id` INTEGER,
                           `created_at` DATE NOT NULL,
                           `changed_at` DATE NOT NULL,

                           PRIMARY KEY(`id`),
                           FOREIGN KEY (`author_id`) REFERENCES `user`(`id`),
                           UNIQUE (id)
);

create table `article_unit` (
                            `author_id` INTEGER,
                            `article_id` INTEGER,

                            FOREIGN KEY (`author_id`) REFERENCES `user`(`id`),
                            FOREIGN KEY (`article_id`) REFERENCES `article`(`id`)
);

create table `tag` (
                       `article_id` INTEGER,
                       `value` ENUM('PROGRAMMING', 'LIFESTYLE', 'JOB', 'HOME'),

                       FOREIGN KEY (`article_id`) REFERENCES `article`(`id`)
);
