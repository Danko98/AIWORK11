package uz.gullbozor.gullbozor.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class NewOrderList {

    List<NewProduct> productList;

    private Long companyId;
    private Double discount;
    private Double totalPrice;
    private Double lastTotalPrice;
    private String orderHolderName;
    private String orderHolderPrice;

}
