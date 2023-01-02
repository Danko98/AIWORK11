package uz.gullbozor.gullbozor.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ariza {
    
    private Integer width;
    private Integer height;
    private Integer count;
    private Byte colorNumber;
    private Byte glassNumber;
    private Integer category;
    private Byte shelfSize;
    private String orderHolderName;
    private String orderHolderPhone;

}
