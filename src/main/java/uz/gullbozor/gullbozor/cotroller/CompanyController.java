package uz.gullbozor.gullbozor.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.dto.CompanyRegisterDto;
import uz.gullbozor.gullbozor.entity.CompanyEntity;
import uz.gullbozor.gullbozor.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {


    @Autowired
    private CompanyService companyService;

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse> addCompany(@RequestBody CompanyRegisterDto companyRegisterDto) {
        ApiResponse apiResponse = companyService.addCompany(companyRegisterDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping("/{companyId}")
    public ResponseEntity<ApiResponse> editCompany(@RequestBody CompanyRegisterDto companyRegisterDto, @PathVariable Long companyId) {
        ApiResponse apiResponse = companyService.editCompany(companyRegisterDto, companyId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/{companyId}")
    public ResponseEntity<ApiResponse> getCompanyId(@PathVariable Long companyId) {
        ApiResponse apiResponse = companyService.getCompanyById(companyId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/{regionId}")
    public ResponseEntity<List<CompanyEntity>> getCompanyListByRegionId(@PathVariable Integer regionId) {
        List<CompanyEntity> companyEntityList = companyService.getCompanyListByRegionId(regionId);
        return ResponseEntity.ok(companyEntityList);
    }

    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/{cityId}")
    public ResponseEntity<List<CompanyEntity>> getCompanyListByCityId(@PathVariable Integer cityId) {
        List<CompanyEntity> companyEntityList = companyService.getCompanyListByCityId(cityId);
        return ResponseEntity.ok(companyEntityList);
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{companyId}")
    public ResponseEntity<ApiResponse> deleteRegionById(@PathVariable Long companyId) {
        ApiResponse apiResponse = companyService.deleteCompany(companyId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }



}
