package uz.gullbozor.gullbozor.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uz.gullbozor.gullbozor.entity.ArizaEntity;
import uz.gullbozor.gullbozor.export.ExcelExportUtils;
import uz.gullbozor.gullbozor.repository.ArizaRepo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class ArizaExportService {

    private ArizaRepo arizaRepo;

    public List<ArizaEntity> exportArizaToExcel(HttpServletResponse response) throws IOException {
        List<ArizaEntity> arizaEntityList = arizaRepo.findAll();
        ExcelExportUtils excelExportUtils = new ExcelExportUtils(arizaEntityList);
        excelExportUtils.exportDataToExcel(response);
        return arizaEntityList;
    }
}
