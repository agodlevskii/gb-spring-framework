create table users (
      username varchar(50) not null primary key,
      password varchar(500) not null,
      enabled boolean not null
);

GRANT ALL PRIVILEGES ON TABLE users TO geek;

INSERT INTO users (username, password, enabled) VALUES ('user1', '$2a$10$KY6bjD09i41xJI/tWBdlrOmKCrDxJ9b8ABscb33.jUjkKJyQdvWlm', true),
                                                  ('user2', '$2a$10$IzeRzVRcXG9eaUGZQbeI.uziwce2J.9duFIST2o290DIMM0nOcQza', false),
                                                  ('manager1', '$2a$10$tv.gMlYe4ctJ23n1Uqcr..Qoe4pi5lcYQBtmJPMvZbRWaCGkq3yjG', true),
                                                  ('manager2', '$2a$10$p9q2FPGIheOny/8VWUKF2uiWoJeqHN8Zqe3rwi.ghjwccyRVe3op.', true),
                                                  ('admin1', '$2a$10$WW9cFZ.XbdHghom79lQuf.lzA0kdydLn48JDwyJwCNOTCfcD./Dge', true),
                                                  ('admin2', '$2a$10$4K/jfm7b3452X7bl3b3FYOfs6jaz7I2uVo7Xagx..eiXTbO/OZBQG', true),
                                                  ('root', '$2a$10$p9PwSpaDkyACqZlm8LY7MuhtvF1I0nAq4EhiKTvgMaR2WqpeyLjRm', true);

create table authorities (
     username varchar(50) not null,
     authority varchar(50) not null,
     constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);


GRANT ALL PRIVILEGES ON TABLE authorities TO geek;

INSERT INTO authorities (username, authority) VALUES ('user1', 'USER'), ('user2', 'USER'),
                                          ('manager1', 'MANAGER'), ('manager2', 'MANAGER'),
                                          ('admin1', 'ADMIN'), ('admin2', 'ADMIN'),
                                          ('root', 'ROOT');