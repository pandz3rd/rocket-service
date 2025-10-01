
create database spaces

create table spaces.rockets (
	id int not null auto_increment primary key,
	name varchar(256) not null,
	fuel_tank int,
	astronout_cap int
) engine = innoDb

create table spaces.asteroids (
	id int not null auto_increment primary key,
	name varchar(256) not null,
	min_diameter_km double,
	max_diameter_km double,
	is_hazardous boolean
) engine = innoDb

create table spaces.rocket_asteroid (
    rocket_id int not null,
    asteroid_id int not null,
    primary key (rocket_id, asteroid_id),
    foreign key (rocket_id) references rockets(id),
    foreign key (asteroid_id) references asteroids(id)
) engine = innoDb;