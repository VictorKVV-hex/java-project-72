CREATE TABLE urls (
  id bigint PRIMARY KEY GENERATED by default AS IDENTITY,
  name VARCHAR(255) NOT NULL,
  created_at TIMESTAMP NOT NULL
);