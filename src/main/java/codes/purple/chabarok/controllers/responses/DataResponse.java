package codes.purple.chabarok.controllers.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataResponse {
    private Status status;
    private Object data;
}
