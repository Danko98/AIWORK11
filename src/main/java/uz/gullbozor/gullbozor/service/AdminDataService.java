package uz.gullbozor.gullbozor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.gullbozor.gullbozor.entity.AdminEntity;
import uz.gullbozor.gullbozor.repository.AdminRepo;

import java.util.Optional;

@Service
public class AdminDataService {

    @Autowired
    private AdminRepo adminRepo;


    public void edit(Byte num) {
        Optional<AdminEntity> adminEntity = adminRepo.findById(1L);
        AdminEntity adminEntity1 = adminEntity.get();

        switch (num) {

            case 1:
                Long page1 = adminEntity1.getPage1();
                page1++;
                adminEntity1.setPage1(page1);
                break;

            case 2:
                Long page2 = adminEntity1.getPage2();
                page2++;
                adminEntity1.setPage2(page2);
                break;

            case 3:
                Long page3 = adminEntity1.getPage3();
                page3++;
                adminEntity1.setPage3(page3);
                break;

            case 4:
                Long page4 = adminEntity1.getPage4();
                page4++;
                adminEntity1.setPage4(page4);
                break;

            case 5:
                Long page5 = adminEntity1.getPage5();
                page5++;
                adminEntity1.setPage5(page5);
                break;

            case 6:
                Long page6 = adminEntity1.getPage6();
                page6++;
                adminEntity1.setPage6(page6);
                break;

            case 7:
                Long page7 = adminEntity1.getPage7();
                page7++;
                adminEntity1.setPage7(page7);
                break;

            case 8:
                Long page8 = adminEntity1.getPage8();
                page8++;
                adminEntity1.setPage8(page8);
                break;

            case 9:
                Long page9 = adminEntity1.getPage9();
                page9++;
                adminEntity1.setPage9(page9);
                break;

            case 10:
                Long page10 = adminEntity1.getPage10();
                page10++;
                adminEntity1.setPage10(page10);
                break;

            case 11:
                Long page11 = adminEntity1.getPage11();
                page11++;
                adminEntity1.setPage1(page11);
                break;

            case 12:
                Long calls = adminEntity1.getCalls();
                calls++;
                adminEntity1.setCalls(calls);
                break;

            case 13:
                Long buys = adminEntity1.getBuys();
                buys++;
                adminEntity1.setBuys(buys);
                break;

            case 14:
                Long visitors = adminEntity1.getVisitors();
                visitors++;
                adminEntity1.setVisitors(visitors);
                break;

            case 15:
                Long paid = adminEntity1.getPaid();
                paid++;
                adminEntity1.setPaid(paid);
                break;

            case 16:
                Long exits = adminEntity1.getExits();
                exits++;
                adminEntity1.setPaid(exits);
                break;

            case 17:
                Long trial = adminEntity1.getTrial();
                trial++;
                adminEntity1.setPaid(trial);
                break;

            default: break;

        }
    }

    public Long get(Byte id) {

        Optional<AdminEntity> adminEntity = adminRepo.findById(1L);
        AdminEntity adminEntity1 = adminEntity.get();


        switch (id) {

            case 1:
                return adminEntity1.getPage1();

            case 2:
                return adminEntity1.getPage2();

            case 3:
                return adminEntity1.getPage3();

            case 4:
                return adminEntity1.getPage4();

            case 5:
                return adminEntity1.getPage5();

            case 6:
                return adminEntity1.getPage6();

            case 7:
                return adminEntity1.getPage7();

            case 8:
                return adminEntity1.getPage8();

            case 9:
                return adminEntity1.getPage9();

            case 10:
                return adminEntity1.getPage10();

            case 11:
                return adminEntity1.getPage11();

            case 12:
                return adminEntity1.getCalls();

            case 13:
                return adminEntity1.getBuys();

            case 14:
                return adminEntity1.getVisitors();

            case 15:
                return adminEntity1.getPaid();

            case 16:
                return adminEntity1.getExits();

            case 17:
                return adminEntity1.getTrial();

            default: return 0L;

        }
    }


}
