package uz.gullbozor.gullbozor.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "new_order_list_entity")
public class OrderHead {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long companyId;
    private Double discount;
    private Double cost;
    private Double totalPrice;
    private Double lastTotalPrice;
    private String orderHolderName;
    private String orderHolderPrice;
    private Byte status;



}
