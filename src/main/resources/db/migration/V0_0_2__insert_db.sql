insert into users (user_id, email, password, bio, image, username)
values
('1', 'realworld1@email.com', '$2a$10$/Hxqaf3ZfncnQGn2/Qg2R.Uacd2ElztD.4viYFF6jPHeBrqoG9M/m', 'bio1', 'image1', 'realworld1'),
('2', 'realworld2@email.com', '$2a$10$/Hxqaf3ZfncnQGn2/Qg2R.Uacd2ElztD.4viYFF6jPHeBrqoG9M/m', 'bio2', 'image2', 'realworld2'),
('3', 'realworld3@email.com', '$2a$10$/Hxqaf3ZfncnQGn2/Qg2R.Uacd2ElztD.4viYFF6jPHeBrqoG9M/m', 'bio3', 'image3', 'realworld3');

insert into follow_relation (followee_id, follower_id)
values
(1, 2);
