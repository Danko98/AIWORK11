package uz.gullbozor.gullbozor.cotroller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.dto.NewComDto;
import uz.gullbozor.gullbozor.entity.ArizaEntity;
import uz.gullbozor.gullbozor.entity.NewCom;
import uz.gullbozor.gullbozor.repository.ArizaRepo;
import uz.gullbozor.gullbozor.repository.NewComRepo;
import uz.gullbozor.gullbozor.service.ArizaExportService;
import uz.gullbozor.gullbozor.service.NewComService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/newCom")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NewComController {


    @Autowired
    private NewComRepo newComRepo;

    @Autowired
    private NewComService newComService;

    @PostMapping("/checkPhone")
    public ResponseEntity<ApiResponse> checkPhoneNumber(@RequestBody String phoneNumber) {

        ApiResponse apiResponse = newComService.checkPhoneNumber(phoneNumber);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody NewCom newComDto) {

//        newCom.setOwnerPhone(newComDto.getOwnerPhone());
//        newCom.setOwnerName(newComDto.getOwnerName());
//        newCom.setCity(newComDto.getCity());
//        newCom.setRegion(newComDto.getRegion());
//
//        newCom.setCategory1Oq(newComDto.getCategory1Oq());
//        newCom.setCategory1Zal(newComDto.getCategory1Zal());
//        newCom.setCategory1Mokko(newComDto.getCategory1Mokko());
//        newCom.setCategory1Mokry(newComDto.getCategory1Mokry());
//
//        newCom.setCategory2Oq(newComDto.getCategory2Oq());
//        newCom.setCategory2Zal(newComDto.getCategory2Zal());
//        newCom.setCategory2Mokko(newComDto.getCategory2Mokko());
//        newCom.setCategory2Mokry(newComDto.getCategory2Mokry());
//
//        newCom.setCategory3Oq(newComDto.getCategory3Oq());
//        newCom.setCategory3Zal(newComDto.getCategory3Zal());
//        newCom.setCategory3Mokko(newComDto.getCategory3Mokko());
//        newCom.setCategory3Mokry(newComDto.getCategory3Mokry());
//
//        newCom.setCategory4Oq(newComDto.getCategory4Oq());
//        newCom.setCategory4Zal(newComDto.getCategory4Zal());
//        newCom.setCategory4Mokko(newComDto.getCategory4Mokko());
//        newCom.setCategory4Mokry(newComDto.getCategory4Mokry());
//
//        newCom.setCategory5Oq(newComDto.getCategory5Oq());
//        newCom.setCategory5Zal(newComDto.getCategory5Zal());
//        newCom.setCategory5Mokko(newComDto.getCategory5Mokko());
//        newCom.setCategory5Mokry(newComDto.getCategory5Mokry());

        ApiResponse register = newComService.register(newComDto);
        return ResponseEntity.status(register.isSuccess() ? 200 : 400).body(register);


    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ApiResponse> edit(@RequestBody NewCom newComDto, @PathVariable Long id) {

        ApiResponse apiResponse = newComService.edit(newComDto, id);

        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);

    }

    @DeleteMapping("/delete/{phoneNumber}")
    public ResponseEntity<ApiResponse> delete(@PathVariable String phoneNumber) {

        ApiResponse apiResponse = newComService.delete(phoneNumber);

        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);

    }


}


