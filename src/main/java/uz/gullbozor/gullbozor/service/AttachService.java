package uz.gullbozor.gullbozor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.gullbozor.gullbozor.entity.Attach;
import uz.gullbozor.gullbozor.entity.MainAttach;
import uz.gullbozor.gullbozor.repository.AttachRepo;
import uz.gullbozor.gullbozor.repository.MainAttachRepo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;


@Service
public class AttachService {

    public Long generatedLong;

    @Autowired
    private AttachRepo attachRepo;



    @Autowired
    private MainAttachRepo mainAttachRepo;

    private static final String uploadDirectory= "yuklanganlar";
    private static final String competitionUploadDirectory= "tanlovdagirasmlar";

    public String getFileUrl(Long mainAttachId) {
        Optional<MainAttach> optionalMainAttach = mainAttachRepo.findById(mainAttachId);
        MainAttach mainAttach = optionalMainAttach.get();
        String name = mainAttach.getName();
        String imageURL = "https://gulbazar.herokuapp.com/yuklanganlar/"+name;
        return imageURL;

    }

    public Long addMainAttach(MultipartFile file) {

        if (file != null) {
            try {
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
                return mainAttach.getId();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }


        }
        return null;
    }



    public void addAttach(MultipartFile file, Long id) {

        if (file != null) {
            try {
                Attach attach = new Attach();
                attach.setMainAttachId(id);
                attach.setContentType(file.getContentType());
                attach.setSize(file.getSize());
                attach.setFileOriginalName(file.getOriginalFilename());
                String originalFilename = file.getOriginalFilename();

                String[] split = originalFilename.split("\\.");
                String name = UUID.randomUUID().toString() + "." + split[split.length - 1];
                attach.setName(name);

                attachRepo.save(attach);

                Path path = Paths.get(uploadDirectory + "/" + name);

                Files.copy(file.getInputStream(), path);

            } catch (IOException e) {
                e.printStackTrace();

            }


        }

    }

    public byte[] open_general(Long mainAttachId) {
        byte[] data;
        try {
            Optional<MainAttach> optionalMainAttach = mainAttachRepo.findById(mainAttachId);
            MainAttach mainAttach = optionalMainAttach.get();

            Path file = Paths.get(uploadDirectory +"/"+ mainAttach.getName());
            data = Files.readAllBytes(file);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }








//    public MainAttach get(Long id) {
//        return mainAttachRepo.findById(id).orElseThrow(() -> {
//            throw new ItemNotFoundException("Attach not found");
//        });
//    }

}
