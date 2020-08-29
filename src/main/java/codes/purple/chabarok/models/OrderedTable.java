package codes.purple.chabarok.models;

import codes.purple.chabarok.dtos.OrderedTableDTO;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "orderedTables")
@Data
public class OrderedTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date orderedDate;
    private Long table;
    private Long peoples;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public OrderedTable(OrderedTableDTO orderedTableDTO) {
        this.orderedDate = orderedTableDTO.getOrderedDate();
        this.table = orderedTableDTO.getTable();
        this.peoples = orderedTableDTO.getPeoples();
        this.firstName = orderedTableDTO.getFirstName();
        this.lastName = orderedTableDTO.getLastName();
        this.phoneNumber = orderedTableDTO.getPhoneNumber();
    }

    @Tolerate
    public OrderedTable() {
    }
}
