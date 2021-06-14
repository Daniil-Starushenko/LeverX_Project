use `blogDB`;
create table `user` (
                        `id` INTEGER NOT NULL AUTO_INCREMENT,
                        `first_name` VARCHAR(255) NOT NULL,
                        `last_name` VARCHAR(255) NOT NULL,
                        `password` VARCHAR(255) NOT NULL,
                        `email` varchar(255) NOT NULL,
                        `registration_date` DATE,
                        PRIMARY KEY(`id`),
                        UNIQUE (id)
);

create table `article` (
                        `id` INTEGER NOT NULL AUTO_INCREMENT,

                        PRIMARY KEY(`id`),
                        UNIQUE (id)
);
