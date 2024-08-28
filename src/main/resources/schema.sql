CREATE TABLE IF NOT EXISTS USER_TB (
  user_id VARCHAR(50) NOT NULL,
  user_password VARCHAR(100) NOT NULL,
  user_phone VARCHAR(20) NOT NULL,
  user_email VARCHAR(50) NOT NULL,
  user_nickname VARCHAR(20) NOT NULL,
  user_role VARCHAR(15) NOT NULL,
  PRIMARY KEY (user_id),
  CONSTRAINT user_nickname_UNIQUE UNIQUE (user_nickname)
);