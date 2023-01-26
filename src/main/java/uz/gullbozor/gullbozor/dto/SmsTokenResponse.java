package uz.gullbozor.gullbozor.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class SmsTokenResponse {

    @JsonIgnore
    private Object object;
    @JsonIgnore
    private Long generateNumber;
    private boolean success;
    private String massage;

    public SmsTokenResponse(Object object, Long generateNumber) {
        this.object = object;
        this.generateNumber = generateNumber;
    }

    public SmsTokenResponse(String massage, boolean success) {
        this.success = success;
        this.massage = massage;
    }

}
