package uz.gullbozor.gullbozor.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.gullbozor.gullbozor.dto.ImageDto;
import uz.gullbozor.gullbozor.entity.MainAttach;
import uz.gullbozor.gullbozor.repository.AttachRepo;
import uz.gullbozor.gullbozor.repository.MainAttachRepo;
import uz.gullbozor.gullbozor.service.AttachService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.UUID;


@RestController
@RequestMapping("/attachment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AttachmentController {

    @Autowired
    private AttachRepo attachRepo;

    @Autowired
    private MainAttachRepo mainAttachRepo;

    @Autowired
    private AttachService attachService;

    private static final String uploadDirectory= "yuklanganlar";

//    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping("/uploadImage")
    public ImageDto uploadToSystem(MultipartHttpServletRequest request) throws IOException {

        Iterator<String> fileNames = request.getFileNames();
        int count = 0;
        ImageDto imageDto = new ImageDto();

        while (fileNames.hasNext()) {
            String baseUrl = "http://185.217.131.88:8080/attachment/open/";
            count++;
            MultipartFile file = request.getFile(fileNames.next());
            if (file != null) {
                MainAttach mainAttach = new MainAttach();

                mainAttach.setContentType(file.getContentType());
                mainAttach.setSize(file.getSize());
                mainAttach.setFileOriginalName(file.getOriginalFilename());
                String originalFilename = file.getOriginalFilename();
                String[] split = originalFilename.split("\\.");
                String name = UUID.randomUUID().toString() + "." + split[split.length - 1];
                mainAttach.setName(name);
                mainAttachRepo.save(mainAttach);
                Path path = Paths.get(uploadDirectory + "/" + name);
                Files.copy(file.getInputStream(), path);
                switch (count){
                    case 1:
                        imageDto.setImage1(baseUrl + mainAttach.getId());
                        break;
                    case 2:
                        imageDto.setImage2(baseUrl + mainAttach.getId());
                        break;
                    case 3:
                        imageDto.setImage3(baseUrl + mainAttach.getId());
                        break;
                    case 4:
                        imageDto.setImage4(baseUrl + mainAttach.getId());
                        break;
                    case 5:
                        imageDto.setImage5(baseUrl + mainAttach.getId());
                        break;
                    case 6:
                        imageDto.setImage6(baseUrl + mainAttach.getId());
                        break;
                    case 7:
                        imageDto.setImage7(baseUrl + mainAttach.getId());
                        break;
                    case 8:
                        imageDto.setImage8(baseUrl + mainAttach.getId());
                        break;
                }
            }
        }


        return imageDto;
    }

//    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping("/uploadImageOne")
    public ResponseEntity<String> uploadToSystemOne(MultipartHttpServletRequest request) throws IOException {

        Iterator<String> fileNames = request.getFileNames();
        String imageUrl = null;


            String baseUrl = "http://185.217.131.168:8080/attachment/open/";

            MultipartFile file = request.getFile(fileNames.next());
            if (file != null) {
                MainAttach mainAttach = new MainAttach();

                mainAttach.setContentType(file.getContentType());
                mainAttach.setSize(file.getSize());
                mainAttach.setFileOriginalName(file.getOriginalFilename());
                String originalFilename = file.getOriginalFilename();
                String[] split = originalFilename.split("\\.");
                String name = UUID.randomUUID().toString() + "." + split[split.length - 1];
                mainAttach.setName(name);
                mainAttachRepo.save(mainAttach);
                Path path = Paths.get(uploadDirectory + "/" + name);
                Files.copy(file.getInputStream(), path);

                imageUrl = (baseUrl + mainAttach.getId());
            }



        return ResponseEntity.ok(imageUrl);
    }

    @GetMapping(value = "/open/{mainAttachId}", produces = MediaType.ALL_VALUE)
    public ResponseEntity<?> getImageByte(@PathVariable("mainAttachId") Long  mainAttachId) {
         return ResponseEntity.ok().header("Content-Type","image/png").body(attachService.open_general(mainAttachId));
    }





//
//    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN','ROLE_USER')")
//    @GetMapping(value = "/bestFlower/open/{bestFlowerId}", produces = MediaType.ALL_VALUE)
//    public byte[] getBestFlowerImageByte(@PathVariable("bestFlowerId") Long  bestFlowerId) {
//        return attachService.openBestFlower(bestFlowerId);

//    }

//    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN','ROLE_USER')")
//    @PostMapping("/bestFlower/upload")
//    public ResponseEntity<?> uploadBestFlowerToSystem(MultipartHttpServletRequest request) throws IOException {
//
//        Iterator<String> fileNames = request.getFileNames();
//        Long bestFlowerAttachId;
//
//                MultipartFile file = request.getFile(fileNames.next());
//                if (file != null) {
//                    BestFlowerAttach bestFlowerAttach = new BestFlowerAttach();
//                    bestFlowerAttach.setContentType(file.getContentType());
//                    bestFlowerAttach.setSize(file.getSize());
//                    bestFlowerAttach.setFileOriginalName(file.getOriginalFilename());
//                    String originalFilename = file.getOriginalFilename();
//                    String[] split = originalFilename.split("\\.");
//                    String name = UUID.randomUUID().toString() + "." + split[split.length - 1];
//                    bestFlowerAttach.setName(name);
//                    bestFlowerAttachRepo.save(bestFlowerAttach);
//                    Path path = Paths.get(competitionUploadDirectory + "/" + name);
//                    Files.copy(file.getInputStream(), path);
//                    bestFlowerAttachId = bestFlowerAttach.getId();
//                }else {
//                    return ResponseEntity.ok("No picture came");
//                }
//        return ResponseEntity.ok(bestFlowerAttachId);
//    }
//





}
