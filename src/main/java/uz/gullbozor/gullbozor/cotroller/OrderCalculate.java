package uz.gullbozor.gullbozor.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.dto.NewOrderParam;
import uz.gullbozor.gullbozor.service.OrderCalculateService;

@RestController
@RequestMapping("/orderCalculate")
public class OrderCalculate {

    @Autowired
    private OrderCalculateService orderCalculateService;


//    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping
    public ResponseEntity<ApiResponse> getResultCalculate(@RequestBody NewOrderParam newOrderParam) {
        ApiResponse apiResponse = orderCalculateService.calculateOrder(newOrderParam);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }



}
