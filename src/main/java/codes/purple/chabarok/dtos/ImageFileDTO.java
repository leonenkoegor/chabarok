package codes.purple.chabarok.dtos;

import lombok.Data;
import lombok.experimental.Tolerate;

@Data
public class ImageFileDTO {
    private String name;
    private String type;
    private byte[] data;
}
