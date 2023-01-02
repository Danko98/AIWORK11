package uz.gullbozor.gullbozor.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "new_product_entity")
public class NewProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long productId;
    private Byte productCount;
    private Double productPrice;

}
