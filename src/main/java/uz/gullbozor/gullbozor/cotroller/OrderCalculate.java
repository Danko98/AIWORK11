package uz.gullbozor.gullbozor.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.dto.GetPriceList;
import uz.gullbozor.gullbozor.dto.NewOrderParam;
import uz.gullbozor.gullbozor.service.OrderCalculateService;

import java.util.List;

@RestController
@RequestMapping("/orderCalculate")
public class OrderCalculate {

    @Autowired
    private OrderCalculateService orderCalculateService;


//    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/getOnlyPrice")
    public ResponseEntity<ApiResponse> getPriceCalculate(@RequestBody List<NewOrderParam> newOrderParam) {
        ApiResponse apiResponse = orderCalculateService.completed(newOrderParam);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/getDetails")
    public ResponseEntity<ApiResponse> getResultCalculate(@RequestBody NewOrderParam newOrderParam) {
        ApiResponse apiResponse = orderCalculateService.calculateOrder(newOrderParam);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/getPriceList/{cityId}")
    private List<GetPriceList> getPriceLists(@RequestBody NewOrderParam newOrderParam, @PathVariable Integer cityId) {

        return orderCalculateService.getPriceLists(newOrderParam,cityId);
    }



}
