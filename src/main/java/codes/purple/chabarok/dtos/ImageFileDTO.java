package codes.purple.chabarok.dtos;

import lombok.Data;

@Data
public class ImageFileDTO {
    private String name;
    private String type;
    private byte[] data;
}
