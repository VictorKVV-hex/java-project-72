package hexlet.code.dto.urls;

import hexlet.code.dto.BasePage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.sql.Timestamp;

@AllArgsConstructor
@Getter
public class UrlPage extends BasePage {
    private  Long id;
    private  String name;
    private  Timestamp createdAt;
}
