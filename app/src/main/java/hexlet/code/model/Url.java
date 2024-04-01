package hexlet.code.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
@Getter
@Setter
@ToString
public class Url {
    private long id;
    private String name;
    private Timestamp createdAt;

    public Url(String name1, Timestamp createdAt1) {
        this.name = name1;
        this.createdAt = createdAt1;
    }
}
