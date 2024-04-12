create table tasks
(
    id               serial    primary key,
    title            varchar   not null,
    description      varchar   not null,
    due_date         timestamp not null,
    completed        boolean   default false
);