package uz.gullbozor.gullbozor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.entity.NewCom;
import uz.gullbozor.gullbozor.repository.NewComRepo;

import java.util.Optional;
@Service
public class NewComService {


    @Autowired
    private NewComRepo newComRepo;



    public ApiResponse checkPhoneNumber( String phoneNumber) {

        if (newComRepo.existsByOwnerPhone(phoneNumber)) {
            Optional<NewCom> optionalNewCom = newComRepo.findByOwnerPhone(phoneNumber);
            NewCom newCom = optionalNewCom.get();
            return new ApiResponse(newCom);
        }else {
            return new ApiResponse("Register", false);
        }
    }

    public ApiResponse register( NewCom newComDto) {

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
        newComRepo.save(newComDto);

        return new ApiResponse("Ro'yxatdan o'tkazish bajarildi. Barcha ma'lumotlar saqlandi.",true);
    }

    public ApiResponse edit( NewCom newComDto, Long id) {

        Optional<NewCom> optionalNewCom = newComRepo.findById(id);
        NewCom newCom = optionalNewCom.get();


        newCom.setOwnerPhone(newComDto.getOwnerPhone());
        newCom.setOwnerName(newComDto.getOwnerName());
        newCom.setCity(newComDto.getCity());
        newCom.setRegion(newComDto.getRegion());

        newCom.setCategory1Oq(newComDto.getCategory1Oq());
        newCom.setCategory1Zal(newComDto.getCategory1Zal());
        newCom.setCategory1Mokko(newComDto.getCategory1Mokko());
        newCom.setCategory1Mokry(newComDto.getCategory1Mokry());

        newCom.setCategory2Oq(newComDto.getCategory2Oq());
        newCom.setCategory2Zal(newComDto.getCategory2Zal());
        newCom.setCategory2Mokko(newComDto.getCategory2Mokko());
        newCom.setCategory2Mokry(newComDto.getCategory2Mokry());

        newCom.setCategory3Oq(newComDto.getCategory3Oq());
        newCom.setCategory3Zal(newComDto.getCategory3Zal());
        newCom.setCategory3Mokko(newComDto.getCategory3Mokko());
        newCom.setCategory3Mokry(newComDto.getCategory3Mokry());

        newCom.setCategory4Oq(newComDto.getCategory4Oq());
        newCom.setCategory4Zal(newComDto.getCategory4Zal());
        newCom.setCategory4Mokko(newComDto.getCategory4Mokko());
        newCom.setCategory4Mokry(newComDto.getCategory4Mokry());

        newCom.setCategory5Oq(newComDto.getCategory5Oq());
        newCom.setCategory5Zal(newComDto.getCategory5Zal());
        newCom.setCategory5Mokko(newComDto.getCategory5Mokko());
        newCom.setCategory5Mokry(newComDto.getCategory5Mokry());

        newCom.setShelf15Oq(newComDto.getShelf15Oq());
        newCom.setShelf20Oq(newComDto.getShelf20Oq());
        newCom.setShelf25Oq(newComDto.getShelf25Oq());
        newCom.setShelf30Oq(newComDto.getShelf30Oq());
        newCom.setShelf35Oq(newComDto.getShelf35Oq());
        newCom.setShelf40Oq(newComDto.getShelf40Oq());
        newCom.setShelf45Oq(newComDto.getShelf45Oq());

        newCom.setShelf15Zal(newComDto.getShelf15Zal());
        newCom.setShelf20Zal(newComDto.getShelf20Zal());
        newCom.setShelf25Zal(newComDto.getShelf25Zal());
        newCom.setShelf30Zal(newComDto.getShelf30Zal());
        newCom.setShelf35Zal(newComDto.getShelf35Zal());
        newCom.setShelf40Zal(newComDto.getShelf40Zal());
        newCom.setShelf45Zal(newComDto.getShelf45Zal());

        newCom.setShelf15Mokko(newComDto.getShelf15Mokko());
        newCom.setShelf20Mokko(newComDto.getShelf20Mokko());
        newCom.setShelf25Mokko(newComDto.getShelf25Mokko());
        newCom.setShelf30Mokko(newComDto.getShelf30Mokko());
        newCom.setShelf35Mokko(newComDto.getShelf35Mokko());
        newCom.setShelf40Mokko(newComDto.getShelf40Mokko());
        newCom.setShelf45Mokko(newComDto.getShelf45Mokko());

        newCom.setShelf15Mokry(newComDto.getShelf15Mokry());
        newCom.setShelf20Mokry(newComDto.getShelf20Mokry());
        newCom.setShelf25Mokry(newComDto.getShelf25Mokry());
        newCom.setShelf30Mokry(newComDto.getShelf30Mokry());
        newCom.setShelf35Mokry(newComDto.getShelf35Mokry());
        newCom.setShelf40Mokry(newComDto.getShelf40Mokry());
        newCom.setShelf45Mokry(newComDto.getShelf45Mokry());

        newCom.setYodOynaPrice(newComDto.getYodOynaPrice());
        newCom.setMexanizmPrice(newComDto.getMexanizmPrice());


        newComRepo.save(newCom);

        return new ApiResponse("Ro'yxatdan o'tkazish bajarildi. Barcha ma'lumotlar saqlandi.",true);
    }

    public ApiResponse delete( String phoneNumber) {
        if (newComRepo.existsByOwnerPhone(phoneNumber)) {
            boolean javob = newComRepo.deleteByOwnerPhone(phoneNumber);
            if (javob) {
                return new ApiResponse("Muvafaqiyatli o'chirildi",true);
            }
            else {
                return new ApiResponse("O'chirishda muammo bor",false);
            }
        }else
        {
            return new ApiResponse("Bunday korxona topilmadi",false);
        }

    }




}
