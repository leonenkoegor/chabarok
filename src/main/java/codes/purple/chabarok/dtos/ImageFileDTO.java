package codes.purple.chabarok.dtos;

import codes.purple.chabarok.models.ImageFile;
import lombok.Data;

@Data
public class ImageFileDTO {
    private String name;
    private String type;
    private byte[] data;

    public ImageFileDTO(ImageFile imageFile) {
        this.name = imageFile.getName();
        this.type = imageFile.getType();
        this.data = imageFile.getData();
    }

    public ImageFileDTO() {
    }
}
