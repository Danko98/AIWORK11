package uz.gullbozor.gullbozor.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.entity.OthersPrice;
import uz.gullbozor.gullbozor.service.OtherPriceService;


@RestController
@RequestMapping("/otherPrice")
public class OtherPriceController {

    @Autowired
    private OtherPriceService otherPriceService;


//    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse> addOtherPrice(@RequestBody OthersPrice othersPriceDto) {
        ApiResponse apiResponse = otherPriceService.addOtherPrice(othersPriceDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

//    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping("/{otherPriceId}")
    public ResponseEntity<ApiResponse> editOtherPrice(@RequestBody OthersPrice othersPriceDto, @PathVariable Long otherPriceId) {
        ApiResponse apiResponse = otherPriceService.editOtherPrice(othersPriceDto, otherPriceId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

//    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/{otherPriceId}")
    public ResponseEntity<ApiResponse> getOtherPriceById(@PathVariable Long otherPriceId) {
        ApiResponse apiResponse = otherPriceService.getByIdOtherPrice(otherPriceId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

//    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @DeleteMapping("/{otherPriceId}")
    public ResponseEntity<ApiResponse> deleteOtherPriceById(@PathVariable Long otherPriceId) {
        ApiResponse apiResponse = otherPriceService.deleteById(otherPriceId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
