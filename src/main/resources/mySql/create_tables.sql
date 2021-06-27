use `blogDB`;
create table `user` (
                        `id` INTEGER NOT NULL AUTO_INCREMENT,
                        `first_name` VARCHAR(255) NOT NULL,
                        `last_name` VARCHAR(255) NOT NULL,
                        `password` VARCHAR(255) NOT NULL,
                        `email` varchar(255) NOT NULL,
                        `status` enum ('ACTIVATED', 'WAIT_ACTIVATING') not null,
                        `registration_date` DATE NOT NULL ,

                        UNIQUE (email),
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
                           `changed_at` DATE,

                           PRIMARY KEY(`id`),
                           FOREIGN KEY (`author_id`) REFERENCES `user`(`id`),
                           UNIQUE (id)
);

create table `article_unit` (
                            `id` INTEGER NOT NULL AUTO_INCREMENT,
                            `author_id` INTEGER,
                            `article_id` INTEGER,

                            PRIMARY KEY (`id`),
                            FOREIGN KEY (`author_id`) REFERENCES `user`(`id`),
                            FOREIGN KEY (`article_id`) REFERENCES `article`(`id`)
);

create table `tag` (
                       `id` INTEGER NOT NULL AUTO_INCREMENT,
                       `value` ENUM('PROGRAMMING', 'LIFESTYLE', 'JOB', 'HOME'),

                       PRIMARY KEY (`id`)
);

create table `each_tag` (
                        `tag_id` INTEGER,
                        `article_id` INTEGER,

                        FOREIGN KEY (`tag_id`) references `tag`(`id`),
                        FOREIGN KEY (`article_id`) references `article`(`id`)
);

create table `comment` (
                    `id` INTEGER NOT NULL AUTO_INCREMENT,
                    `message` TEXT NOT NULL,
                    `post_id` INTEGER,
                    `author_id` INTEGER,
                    `created_at` DATE NOT NULL,

                    PRIMARY KEY (`id`),
                    FOREIGN KEY (`post_id`) references `article`(`id`),
                    foreign key (`author_id`) references `user`(`id`)
);
