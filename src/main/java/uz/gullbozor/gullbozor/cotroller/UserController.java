package uz.gullbozor.gullbozor.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.dto.ProfileEditDto;
import uz.gullbozor.gullbozor.dto.VerifyDto;
import uz.gullbozor.gullbozor.dto.RegisterDto;
import uz.gullbozor.gullbozor.entity.UserEntity;
import uz.gullbozor.gullbozor.service.UserService;
import uz.gullbozor.gullbozor.verifysms.PhoneVerificationService;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private PhoneVerificationService phoneVerificationService;

    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PutMapping("/{id}")  // userni tahrirlash
    public ResponseEntity<ApiResponse> editUser(@RequestBody ProfileEditDto registerDto, @PathVariable Long id) {
        ApiResponse apiResponse = userService.editUser(registerDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/{id}")  // userni bittasini id orqali chaqirish
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long id) {
        ApiResponse apiResponse = userService.getUserById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN')")
    @GetMapping      // userlar ro'yxatini olish
    public ResponseEntity<List<UserEntity>> getUserList() {
        List<UserEntity> userList = userService.getUserList();
        return ResponseEntity.ok(userList);
    }

    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @DeleteMapping("/{id}") // userni id si orqali o'chirish
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id) {
        ApiResponse apiResponse = userService.deleteUserById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


}
