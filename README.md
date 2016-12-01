# VIZIR-SAMPLE
Sample application for VIZIR.

To launch it:
* Clone the repo
* Update DataBaseConfig in order to return a valid datasource
* Launch the executable JAR (this is springBoot web application)

You can make a call in POST to http://localhost:8080/school/ to generate some data.
8999 lines will be added to the 'grades' tables, listing students result to an exam.
Results are skewed by gender, region, and students to display interesting data.

The SQL structure is :
````sql

CREATE TABLE public.grades
(
    id integer NOT NULL,
    average real,
    gender character varying(255) COLLATE "default".pg_catalog NOT NULL,
    geo real,
    history real,
    math real,
    region integer NOT NULL,
    result character varying(255) COLLATE "default".pg_catalog NOT NULL,
    school integer NOT NULL,
    sport real,
    CONSTRAINT grades_pkey PRIMARY KEY (id),
    CONSTRAINT grades_average_check CHECK (average <= 20::double precision AND average >= 0::double precision),
    CONSTRAINT grades_geo_check CHECK (geo <= 20::double precision AND geo >= 0::double precision),
    CONSTRAINT grades_history_check CHECK (history <= 20::double precision AND history >= 0::double precision),
    CONSTRAINT grades_math_check CHECK (math <= 20::double precision AND math >= 0::double precision),
    CONSTRAINT grades_sport_check CHECK (sport <= 20::double precision AND sport >= 0::double precision)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.grades
    OWNER to postgres;
````
