package uz.gullbozor.gullbozor.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.dto.SmsTokenDto;
import uz.gullbozor.gullbozor.service.AuthService;


@RestController
@RequestMapping("/sms")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SmsController {

    @Autowired
    private AuthService authService;

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping("/createToken")
    public ResponseEntity<ApiResponse> createToken(@RequestBody SmsTokenDto smsTokenDto) {
        ApiResponse smsToken = authService.createSmsToken(smsTokenDto);
        return ResponseEntity.ok(smsToken);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editSmsToken(@RequestBody SmsTokenDto smsTokenDto,@PathVariable Long id) {
        ApiResponse apiResponse = authService.editSmsToken(smsTokenDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{key}/{id}")
    public ResponseEntity<ApiResponse> getSmsToken(@PathVariable Long key, @PathVariable Long id) {
        ApiResponse apiResponse = authService.getSmsToken(key, id);
        return ResponseEntity.ok(apiResponse);
    }

}
