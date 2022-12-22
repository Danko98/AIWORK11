package uz.gullbozor.gullbozor.entity;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_head")
public class OrderHead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Short count;

    private Long orderId;
    private Integer width;
    private Integer height;
    private Integer sum;
    private Integer cost;
    private Integer benefit;
    private Integer categoryNum;

    private Byte sale;
    private Byte color;
    private Byte glassTypeNum;






}
