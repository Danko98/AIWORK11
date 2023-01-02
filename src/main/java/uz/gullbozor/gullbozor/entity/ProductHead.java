package uz.gullbozor.gullbozor.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "/product_head")
public class ProductHead {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private Integer width;
    private Integer height;
    private Double totalPrice;
    private Double cost;
    private Double partOfCompany;
    private Double discount;
    private Double lastTotalPrice;
    private Integer category;
    private Integer count;
    private Byte colorNumber;
    private Byte glassNumber;
    private Byte shelfSize;
    private Long companyId;
    private Byte ruchkaNumber;




}
