package uz.gullbozor.gullbozor.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.entity.ArizaEntity;
import uz.gullbozor.gullbozor.repository.ArizaRepo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ariza")
public class ArizaController {


    @Autowired
    private ArizaRepo arizaRepo;

    @PostMapping("/checkPhone")
    public ApiResponse checkPhoneNumber(@RequestBody String phoneNumber) {
        if (arizaRepo.existsByPhoneNumber(phoneNumber)) {
            return new ApiResponse("Bu telefon raqamdan ariza qabul qilingan. Iltimos boshqa raqam kiriting",false);
        }
        return new ApiResponse("Ruxsat",true);
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestBody ArizaEntity arizaEntityDto) {

        Date date = new Date();

        ArizaEntity arizaEntity = new ArizaEntity();

        arizaEntity.setHeight(arizaEntityDto.getHeight());
        arizaEntity.setCategory(arizaEntityDto.getCategory());
        arizaEntity.setName(arizaEntityDto.getName());
        arizaEntity.setWidth(arizaEntityDto.getWidth());
        arizaEntity.setColorNumber(arizaEntityDto.getColorNumber());
        arizaEntity.setGlassNumber(arizaEntityDto.getGlassNumber());
        arizaEntity.setShelfSize(arizaEntityDto.getShelfSize());
        arizaEntity.setPhoneNumber(arizaEntityDto.getPhoneNumber());
        arizaEntity.setCreateAt(date.getTime());

        arizaRepo.save(arizaEntity);
        return new ApiResponse("Ariza saqlandi",true);

    }


    @GetMapping("/byId/{id}")
    public ApiResponse getArizaById(@PathVariable Long id) {
        if (arizaRepo.existsById(id)) {
            Optional<ArizaEntity> optionalArizaEntity = arizaRepo.findById(id);

            return new ApiResponse(optionalArizaEntity.get());
        }
        return new ApiResponse("Bu id lik ariza topilmadi",false);
    }


    @GetMapping("/getAll")
    public List<ArizaEntity> getAll() {
        return arizaRepo.findAll();
    }


}


