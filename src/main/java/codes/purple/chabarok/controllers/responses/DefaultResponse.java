package codes.purple.chabarok.controllers.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DefaultResponse {

    protected Status status;
    private String message;

}
