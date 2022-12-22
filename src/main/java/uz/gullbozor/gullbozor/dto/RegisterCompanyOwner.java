package uz.gullbozor.gullbozor.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
@Getter
@Setter
public class RegisterCompanyOwner {

    @Size(min = 3,max = 50)
    private String userName;// ismi

    @Size(min = 9,max = 16)
    private String phoneNumber; // telefon raqami (+998901234567)

    private String surname;

    private Long shopId;

    private String secretKey;

//    @NotNull
//    @Size(min = 6,max = 6)
//    private String otp; // sms code 6 xonalik (OTP)


}
