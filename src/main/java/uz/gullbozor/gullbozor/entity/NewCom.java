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
@Table(name = "new_com")
public class NewCom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ownerName;
    private String ownerPhone;
    private String region;
    private String city;

    private Long category1Oq;
    private Long category1Zal;
    private Long category1Mokko;
    private Long category1Mokry;

    private Long category2Oq;
    private Long category2Zal;
    private Long category2Mokko;
    private Long category2Mokry;

    private Long category3Oq;
    private Long category3Zal;
    private Long category3Mokko;
    private Long category3Mokry;

    private Long category4Oq;
    private Long category4Zal;
    private Long category4Mokko;
    private Long category4Mokry;

    private Long category5Oq;
    private Long category5Zal;
    private Long category5Mokko;
    private Long category5Mokry;

    private Long shelf15Oq;
    private Long shelf20Oq;
    private Long shelf25Oq;
    private Long shelf30Oq;
    private Long shelf35Oq;
    private Long shelf40Oq;
    private Long shelf45Oq;

    private Long shelf15Zal;
    private Long shelf20Zal;
    private Long shelf25Zal;
    private Long shelf30Zal;
    private Long shelf35Zal;
    private Long shelf40Zal;
    private Long shelf45Zal;

    private Long shelf15Mokko;
    private Long shelf20Mokko;
    private Long shelf25Mokko;
    private Long shelf30Mokko;
    private Long shelf35Mokko;
    private Long shelf40Mokko;
    private Long shelf45Mokko;

    private Long shelf15Mokry;
    private Long shelf20Mokry;
    private Long shelf25Mokry;
    private Long shelf30Mokry;
    private Long shelf35Mokry;
    private Long shelf40Mokry;
    private Long shelf45Mokry;


    private Long yodOynaPrice;
    private Long mexanizmPrice;



}
