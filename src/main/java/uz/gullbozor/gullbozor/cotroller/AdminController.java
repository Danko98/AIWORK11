package uz.gullbozor.gullbozor.cotroller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.gullbozor.gullbozor.entity.AdminEntity;
import uz.gullbozor.gullbozor.entity.AdminHeadOne;
import uz.gullbozor.gullbozor.repository.AdminHeadOneRepo;
import uz.gullbozor.gullbozor.repository.AdminRepo;
import uz.gullbozor.gullbozor.service.AdminService;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {


    @Autowired
    private AdminService adminService;



    @Autowired
    private AdminRepo adminRepo;

    @PostMapping("/add/{num}")
    public void addData(@PathVariable Byte num) {

        switch (num) {
            case 1:
            case 2:
            case 3: adminService.addHeadOne(num);
            break;

            case 4:
            case 5:
            case 6: adminService.addHeadTwo(num);
            break;

            default: break;
        }
    }




//    @PostMapping("/headOne")
//    public ResponseEntity<Long> getDate() {
//
//
//
//
//    }

    @GetMapping("/create")
    public ResponseEntity<AdminEntity> createTable() {
        AdminEntity adminEntity = new AdminEntity();
        adminRepo.save(adminEntity);
        return ResponseEntity.ok(adminEntity);
    }





}


