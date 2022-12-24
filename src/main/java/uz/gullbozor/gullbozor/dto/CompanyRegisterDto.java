package uz.gullbozor.gullbozor.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
@Getter
@Setter
public class CompanyRegisterDto {


    @Size(min = 3,max = 50)
    private String userName;// ismi

    @Size(min = 9,max = 16)
    private String phoneNumber; // telefon raqami (+998901234567)

    private String secretKey;

    //***********************************************


    private Long companyOwnerId;
    private String CompanyName;

    private String location;
    private String tgLink;
    private Integer regionId;
    private Integer cityId;
    private Integer dillerId;

    private Double partOfWindow;

    private Double partOfDoor;

    private Double value;

    private Long tgClick;

    private Long mapClick;

    private Long phoneClick;

    private Long view;

    private Boolean installIsFree;


}
