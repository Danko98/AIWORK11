package uz.gullbozor.gullbozor.cotroller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.entity.ArizaEntity;
import uz.gullbozor.gullbozor.repository.ArizaRepo;
import uz.gullbozor.gullbozor.service.ArizaExportService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ariza")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ArizaController {


    @Autowired
    private ArizaRepo arizaRepo;

    @Autowired

    private ArizaExportService arizaExportService;

    @GetMapping("/export-to-excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=RomUchunArizalar.xlsx";
        response.setHeader(headerKey,headerValue);
        arizaExportService.exportArizaToExcel(response);
    }

    @PostMapping("/checkPhone")
    public ApiResponse checkPhoneNumber(@RequestBody String phoneNumber) {
        
        if (arizaRepo.existsByPhoneNumber(phoneNumber)) {
            return new ApiResponse("Bu telefon raqamdan ariza qabul qilingan. Iltimos boshqa raqam kiriting",false);
        }else {
            return new ApiResponse("Ruxsat", true);
        }
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestBody ArizaEntity arizaEntityDto) {



        ArizaEntity arizaEntity = new ArizaEntity();

        arizaEntity.setHeight(arizaEntityDto.getHeight());
        arizaEntity.setCategory(arizaEntityDto.getCategory());
        arizaEntity.setName(arizaEntityDto.getName());
        arizaEntity.setWidth(arizaEntityDto.getWidth());
        arizaEntity.setColorNumber(arizaEntityDto.getColorNumber());
        arizaEntity.setGlassNumber(arizaEntityDto.getGlassNumber());
        arizaEntity.setShelfSize(arizaEntityDto.getShelfSize());
        arizaEntity.setPhoneNumber(arizaEntityDto.getPhoneNumber());
        arizaEntity.setRegionId(arizaEntityDto.getRegionId());

        // Data Time
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());

        arizaEntity.setCreateAt(date);

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

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        arizaRepo.deleteById(id);
        return new ApiResponse("Ariza o'chirildi",true);
    }

}


