package uz.gullbozor.gullbozor.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.gullbozor.gullbozor.entity.AdminHeadOne;
import uz.gullbozor.gullbozor.entity.AdminHeadTwo;
import uz.gullbozor.gullbozor.repository.AdminHeadOneRepo;
import uz.gullbozor.gullbozor.repository.AdminHeadTwoRepo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminHeadOneRepo adminHeadOneRepo;

    @Autowired
    private AdminHeadTwoRepo adminHeadTwoRepo;


    Date dNow = new Date( );

    SimpleDateFormat monthF = new SimpleDateFormat ("MM");
    byte month = Byte.parseByte(monthF.format(dNow));

    SimpleDateFormat monthName = new SimpleDateFormat ("MMMM");              // Sanalarni olish
    String monthNames = monthName.format(dNow);

    SimpleDateFormat yearF = new SimpleDateFormat ("yyyy");
    short year = Short.parseShort((yearF.format(dNow)));






    public void addHeadOne(Byte num) {


        if (adminHeadOneRepo.existsByYearAndMonth(year, month)) {

            Optional<AdminHeadOne> OptionalbyYearAndMonth = adminHeadOneRepo.findByYearAndMonth(year, month);

            AdminHeadOne adminHeadOne = OptionalbyYearAndMonth.get();

            switch (num) {

                case 1:
                    Long calls = adminHeadOne.getCalls();
                    calls++;
                    adminHeadOne.setCalls(calls);
                    break;

                case 2:
                    Long buys = adminHeadOne.getBuys();
                    buys++;
                    adminHeadOne.setBuys(buys);
                    break;

                case 3:
                    Long visitors = adminHeadOne.getVisitors();
                    visitors++;
                    adminHeadOne.setVisitors(visitors);
                    break;

                default: break;

            }
            adminHeadOneRepo.save(adminHeadOne);


        }else {


            AdminHeadOne adminHeadOne = new AdminHeadOne();
            adminHeadOne.setYear(year);
            adminHeadOne.setMonthName(monthNames);
            adminHeadOne.setMonth(month);
            adminHeadOneRepo.save(adminHeadOne);
            addHeadOne(num);
        }



    }

    public void addHeadTwo(Byte num) {

        if (adminHeadTwoRepo.existsByYearAndMonth(year, month)) {

            Optional<AdminHeadTwo> OptionalbyYearAndMonth = adminHeadTwoRepo.findByYearAndMonth(year, month);

            AdminHeadTwo adminHeadTwo = OptionalbyYearAndMonth.get();

            switch (num) {

                case 1:
                    Long trial = adminHeadTwo.getTrial();
                     trial++;
                    adminHeadTwo.setTrial(trial);
                    break;

                case 2:
                    Long paid = adminHeadTwo.getPaid();
                    paid++;
                    adminHeadTwo.setPaid(paid);
                    break;

                case 3:
                    Long exits = adminHeadTwo.getExits();
                    exits++;
                    adminHeadTwo.setExits(exits);
                    break;

                default: break;
            }

            adminHeadTwoRepo.save(adminHeadTwo);

        }else {

            AdminHeadTwo adminHeadTwo = new AdminHeadTwo();
            adminHeadTwo.setYear(year);
            adminHeadTwo.setMonthName(monthNames);
            adminHeadTwo.setMonth(month);
            adminHeadTwoRepo.save(adminHeadTwo);
            addHeadOne(num);
        }
    }


    public AdminHeadOne getHeadOne() {

        Optional<AdminHeadOne> byYearAndMonth = adminHeadOneRepo.findByYearAndMonth(year, month);
        return byYearAndMonth.get();


    }

    public AdminHeadTwo getHeadTwo() {

        Optional<AdminHeadTwo> adminHeadTwo = adminHeadTwoRepo.findByYearAndMonth(year, month);
        return adminHeadTwo.get();


    }















//    public void edit(Byte num) {
//        Optional<AdminEntity> adminEntity = adminRepo.findById(1L);
//        AdminEntity adminEntity1 = adminEntity.get();
//
//        switch (num) {
//
//            case 1:
//                Long page1 = adminEntity1.getPage1();
//                page1++;
//                adminEntity1.setPage1(page1);
//                break;
//
//            case 2:
//                Long page2 = adminEntity1.getPage2();
//                page2++;
//                adminEntity1.setPage2(page2);
//                break;
//
//            case 3:
//                Long page3 = adminEntity1.getPage3();
//                page3++;
//                adminEntity1.setPage3(page3);
//                break;
//
//            case 4:
//                Long page4 = adminEntity1.getPage4();
//                page4++;
//                adminEntity1.setPage4(page4);
//                break;
//
//            case 5:
//                Long page5 = adminEntity1.getPage5();
//                page5++;
//                adminEntity1.setPage5(page5);
//                break;
//
//            case 6:
//                Long page6 = adminEntity1.getPage6();
//                page6++;
//                adminEntity1.setPage6(page6);
//                break;
//
//            case 7:
//                Long page7 = adminEntity1.getPage7();
//                page7++;
//                adminEntity1.setPage7(page7);
//                break;
//
//            case 8:
//                Long page8 = adminEntity1.getPage8();
//                page8++;
//                adminEntity1.setPage8(page8);
//                break;
//
//            case 9:
//                Long page9 = adminEntity1.getPage9();
//                page9++;
//                adminEntity1.setPage9(page9);
//                break;
//
//            case 10:
//                Long page10 = adminEntity1.getPage10();
//                page10++;
//                adminEntity1.setPage10(page10);
//                break;
//
//            case 11:
//                Long page11 = adminEntity1.getPage11();
//                page11++;
//                adminEntity1.setPage1(page11);
//                break;
//
//            case 12:
//                Long calls = adminEntity1.getCalls();
//                calls++;
//                adminEntity1.setCalls(calls);
//                break;
//
//            case 13:
//                Long buys = adminEntity1.getBuys();
//                buys++;
//                adminEntity1.setBuys(buys);
//                break;
//
//            case 14:
//                Long visitors = adminEntity1.getVisitors();
//                visitors++;
//                adminEntity1.setVisitors(visitors);
//                break;
//
//            case 15:
//                Long paid = adminEntity1.getPaid();
//                paid++;
//                adminEntity1.setPaid(paid);
//                break;
//
//            case 16:
//                Long exits = adminEntity1.getExits();
//                exits++;
//                adminEntity1.setPaid(exits);
//                break;
//
//            case 17:
//                Long trial = adminEntity1.getTrial();
//                trial++;
//                adminEntity1.setPaid(trial);
//                break;
//
//            default: break;
//
//        }
//    }

//    public Long get(Byte id) {
//
//        Optional<AdminEntity> adminEntity = adminRepo.findById(1L);
//        AdminEntity adminEntity1 = adminEntity.get();
//
//
//        switch (id) {
//
//            case 1:
//                return adminEntity1.getPage1();
//
//            case 2:
//                return adminEntity1.getPage2();
//
//            case 3:
//                return adminEntity1.getPage3();
//
//            case 4:
//                return adminEntity1.getPage4();
//
//            case 5:
//                return adminEntity1.getPage5();
//
//            case 6:
//                return adminEntity1.getPage6();
//
//            case 7:
//                return adminEntity1.getPage7();
//
//            case 8:
//                return adminEntity1.getPage8();
//
//            case 9:
//                return adminEntity1.getPage9();
//
//            case 10:
//                return adminEntity1.getPage10();
//
//            case 11:
//                return adminEntity1.getPage11();
//
//            case 12:
//                return adminEntity1.getCalls();
//
//            case 13:
//                return adminEntity1.getBuys();
//
//            case 14:
//                return adminEntity1.getVisitors();
//
//            case 15:
//                return adminEntity1.getPaid();
//
//            case 16:
//                return adminEntity1.getExits();
//
//            case 17:
//                return adminEntity1.getTrial();
//
//            default: return 0L;
//
//        }
//    }


}
