
INSERT INTO manufacturer (id, name, nationality) values ('1', 'Westmalle', 'Belgian');
INSERT INTO manufacturer (id, name, nationality) values ('2', 'BrewDog', 'Scottish');

INSERT INTO beer (id, name, description, graduation, beer_type, manufacturer_id) values ('1', 'Westmalle Tripel', 'Was first brewed in 1934 and the recipe has not changed since 1956.', 9.5, 'Tripel', '1');
INSERT INTO beer (id, name, description, graduation, beer_type, manufacturer_id) values ('2', 'Westmalle Double', 'The Westmalle Dubbel is just such a well-balanced beer, full in the mouth, both herbal and fruity.', 7, 'Double', '1');
INSERT INTO beer (id, name, description, graduation, beer_type, manufacturer_id) values ('3', 'Punk IPA', 'Punk IPA is the beer that kick-started it. This light, golden classic has been subverted with new world hops to create an explosion of flavour.', 5.4, 'IPA', '2');
INSERT INTO beer (id, name, description, graduation, beer_type, manufacturer_id) values ('4', 'Jack Hammer', 'Jack Hammer lives up to its billing. Our scene stealing IPA gives a ruthlessly bitter performance.', 7.2, 'IPA', '2');
