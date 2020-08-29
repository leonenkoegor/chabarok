package codes.purple.chabarok.dtos;

import codes.purple.chabarok.models.OrderedTable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class OrderedTableDTO {
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date orderedDate;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date orderedFromTime;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date orderedToTime;
    private Long peoples;
    private String name;
    private String phoneNumber;

    public OrderedTableDTO(OrderedTable orderedTable) {
        this.orderedDate = orderedTable.getOrderedDate();
        this.orderedFromTime = orderedTable.getOrderedFromTime();
        this.orderedToTime = orderedTable.getOrderedToTime();
        this.peoples = orderedTable.getPeoples();
        this.name = orderedTable.getName();
        this.phoneNumber = orderedTable.getPhoneNumber();
    }

    public OrderedTableDTO() {
    }
}
