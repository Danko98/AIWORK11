package uz.gullbozor.gullbozor.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewOrderParam {



    private Byte type;
    private Integer width;
    private Integer height;
    private Integer count;
    private Byte colorNumber;
    private Byte glassNumber;
    private Integer category;
    private Byte shelfSize;
    private Long companyId;
    private Double salePriceDto;



}
