package codes.purple.chabarok.models;

import codes.purple.chabarok.dtos.OrderedTableDTO;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orderedTables")
@Data
public class OrderedTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date orderedDate;
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private Date orderedFromTime;
    @DateTimeFormat(pattern = "HH:mm")
    @Temporal(TemporalType.TIME)
    private Date orderedToTime;
    private Long peoples;
    private String name;
    private String phoneNumber;

    public OrderedTable(OrderedTableDTO orderedTableDTO) {
        this.orderedDate = orderedTableDTO.getOrderedDate();
        this.orderedFromTime = orderedTableDTO.getOrderedFromTime();
        this.orderedToTime = orderedTableDTO.getOrderedToTime();
        this.peoples = orderedTableDTO.getPeoples();
        this.name = orderedTableDTO.getName();
        this.phoneNumber = orderedTableDTO.getPhoneNumber();
    }

    @Tolerate
    public OrderedTable() {
    }
}
