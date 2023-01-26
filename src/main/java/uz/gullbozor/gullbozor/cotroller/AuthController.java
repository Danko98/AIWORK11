package uz.gullbozor.gullbozor.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.apiResponse.UserData;
import uz.gullbozor.gullbozor.dto.*;
import uz.gullbozor.gullbozor.service.AuthService;
import uz.gullbozor.gullbozor.verifysms.PhoneNumberDto;
import uz.gullbozor.gullbozor.verifysms.PhoneVerificationService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

//    @Autowired
//    private PhoneVerificationService phoneVerificationService;


    @PostMapping("/checkPhoneNumber")
    public ResponseEntity<UserData> checkPhoneNumber(@RequestBody CheckPhoneNumberDto checkPhoneNumberDto) {
        UserData userData = authService.checkPhoneNumber(checkPhoneNumberDto);
        return ResponseEntity.status(userData.isSuccess() ? 200 : 409).body(userData);
    }

//    @PostMapping("/login")
//    public ResponseEntity<UserData> login(@RequestBody Login login) {
//        UserData userData = authService.login(login);
//        return ResponseEntity.status(userData.isSuccess() ? 200 : 400).body(userData);
//    }

   @PostMapping("/registerUser")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody RegisterDto registerDto) {
       ApiResponse apiResponse = authService.registerUser(registerDto);
       return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
   }


    @PostMapping("/registerCompanyOwner")
    public ResponseEntity<ApiResponse> registerCompanyOwner(@RequestBody RegisterCompanyOwner registerCompanyOwner) {
        ApiResponse apiResponse = authService.registerCompanyOwner(registerCompanyOwner);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }




}
