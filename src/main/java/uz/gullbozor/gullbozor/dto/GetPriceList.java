package uz.gullbozor.gullbozor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPriceList {

    private Double totalPrice;
    private String PhoneNumber;
    private String location;
    private String tgLink;
    private String companyName;
    private Byte stars;

}
