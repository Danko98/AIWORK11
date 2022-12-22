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
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer companyId;
    private String name;
    private String phoneNumber;

    private Integer sum;

    private Integer cost;

    private Integer benefit;

    private Integer sale;

    private Byte status;




}
