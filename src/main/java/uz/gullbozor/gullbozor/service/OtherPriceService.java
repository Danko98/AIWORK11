package uz.gullbozor.gullbozor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.entity.OthersPrice;
import uz.gullbozor.gullbozor.repository.OtherPriceRepo;

import java.util.Optional;


@Service
public class OtherPriceService {

    @Autowired
    private OtherPriceRepo otherPriceRepo;


    public ApiResponse addOtherPrice(OthersPrice othersPriceDto) {

        if (!otherPriceRepo.existsByCompanyId(othersPriceDto.getCompanyId())) {

            OthersPrice othersPrice = othersPriceDto;

            otherPriceRepo.save(othersPrice);
            return new ApiResponse("Successfuly add", true);
        }
        return new ApiResponse("Narx qo'shib bo'lingan uni faqat edit qilish mumkin",false);

    }

    public ApiResponse getByIdOtherPrice(Long otherPriceId) {
        if (otherPriceRepo.existsById(otherPriceId)){
            Optional<OthersPrice> optionalOthersPrice = otherPriceRepo.findById(otherPriceId);
            return new ApiResponse(optionalOthersPrice.get());
        }else
            return new ApiResponse("Bunday id lik ro'yxat topilmadi",false);

    }

    public ApiResponse editOtherPrice(OthersPrice othersPriceDto, Long otherPriceId) {

        if (otherPriceRepo.existsById(otherPriceId)){
            Optional<OthersPrice> otherPriceById = otherPriceRepo.findById(otherPriceId);
            OthersPrice othersPrice = otherPriceById.get();

            othersPrice.setPvh60QvtLPriceColor(othersPriceDto.getPvh60QvtLPriceColor());
            othersPrice.setPvh60TQvtPriceColor(othersPriceDto.getPvh60TQvtPriceColor());
            othersPrice.setPvh60ZQvtPriceColor(othersPriceDto.getPvh60ZQvtPriceColor());
            othersPrice.setPvh60TrioShtapikTwoPriceColor(othersPriceDto.getPvh60TrioShtapikTwoPriceColor());
            othersPrice.setShelf15PriceColor(othersPriceDto.getShelf15PriceColor());
            othersPrice.setShelf20PriceColor(othersPriceDto.getShelf20PriceColor());
            othersPrice.setShelf25PriceColor(othersPriceDto.getShelf25PriceColor());
            othersPrice.setShelf30PriceColor(othersPriceDto.getShelf30PriceColor());
            othersPrice.setShelf35PriceColor(othersPriceDto.getShelf35PriceColor());
            othersPrice.setShelf45PriceColor(othersPriceDto.getShelf45PriceColor());
            othersPrice.setAluLPriceColor(othersPriceDto.getAluLPriceColor());
            othersPrice.setAluTPriceColor(othersPriceDto.getAluTPriceColor());
            othersPrice.setAluZPriceColor(othersPriceDto.getAluZPriceColor());
            othersPrice.setAluShtapik1PriceColor(othersPriceDto.getAluShtapik1PriceColor());
            othersPrice.setAluShtapik2PriceColor(othersPriceDto.getAluShtapik2PriceColor());

            othersPrice.setLambriPvhPrice(othersPriceDto.getLambriPvhPrice());
            othersPrice.setLambriPvhPriceColor(othersPriceDto.getLambriPvhPriceColor());
            othersPrice.setLambriAluPrice(othersPriceDto.getLambriAluPrice());
            othersPrice.setLambriAluPriceColor(othersPriceDto.getLambriAluPriceColor());

            othersPrice.setOyna1Price(othersPrice.getOyna1Price());
            othersPrice.setOyna2Price(othersPrice.getOyna2Price());
            othersPrice.setOyna3Price(othersPrice.getOyna3Price());
            othersPrice.setOyna4Price(othersPrice.getOyna4Price());

            othersPrice.setPvh60QvtLPrice(othersPriceDto.getPvh60QvtLPrice());
            othersPrice.setPvh60TQvtPrice(othersPriceDto.getPvh60TQvtPrice());
            othersPrice.setPvh60ZQvtPrice(othersPriceDto.getPvh60ZQvtPrice());
            othersPrice.setPvh60TrioShtapikTwoPrice(othersPriceDto.getPvh60TrioShtapikTwoPrice());
            othersPrice.setShelf15Price(othersPriceDto.getShelf15Price());
            othersPrice.setShelf20Price(othersPriceDto.getShelf20Price());
            othersPrice.setShelf25Price(othersPriceDto.getShelf25Price());
            othersPrice.setShelf30Price(othersPriceDto.getShelf30Price());
            othersPrice.setShelf35Price(othersPriceDto.getShelf35Price());
            othersPrice.setShelf45Price(othersPriceDto.getShelf45Price());
            othersPrice.setAluLPrice(othersPriceDto.getAluLPrice());
            othersPrice.setAluTPrice(othersPriceDto.getAluTPrice());
            othersPrice.setAluZPrice(othersPriceDto.getAluZPrice());
            othersPrice.setAluShtapik1Price(othersPriceDto.getAluShtapik1Price());
            othersPrice.setAluShtapik2Price(othersPriceDto.getAluShtapik2Price());


            othersPrice.setRuchka155Price(othersPriceDto.getRuchka155Price());
            othersPrice.setRuchka153Price(othersPriceDto.getRuchka153Price());
            othersPrice.setRuchka155PriceColor(othersPriceDto.getRuchka155PriceColor());
            othersPrice.setRuchka153PriceColor(othersPriceDto.getRuchka153PriceColor());
            othersPrice.setPetlyaPrice(othersPriceDto.getPetlyaPrice());
            othersPrice.setPetlyaPriceColor(othersPriceDto.getPetlyaPriceColor());
            othersPrice.setSaydinitelPrice(othersPriceDto.getSaydinitelPrice());
            othersPrice.setSaydinitelAluPrice(othersPriceDto.getSaydinitelAluPrice());
            othersPrice.setChitPrice(othersPriceDto.getChitPrice());
            othersPrice.setQoraBurchakPrice(othersPriceDto.getQoraBurchakPrice());
            othersPrice.setSariqBurchakPrice(othersPriceDto.getSariqBurchakPrice());
            othersPrice.setPoroshokPrice(othersPriceDto.getPoroshokPrice());
            othersPrice.setTikolPrice(othersPriceDto.getTikolPrice());
            othersPrice.setRezinaPvhPrice(othersPriceDto.getRezinaPvhPrice());
            othersPrice.setRezinaBosmaPrice(othersPriceDto.getRezinaBosmaPrice());
            othersPrice.setRezinaYuPrice(othersPriceDto.getRezinaYuPrice());
            othersPrice.setSamarezPrice(othersPriceDto.getSamarezPrice());
            othersPrice.setIspandiletPrice(othersPriceDto.getIspandiletPrice());
            othersPrice.setSulchikPrice(othersPriceDto.getSulchikPrice());
            othersPrice.setSulchikPriceColor(othersPriceDto.getSulchikPriceColor());
            othersPrice.setIlmoqPrice(othersPriceDto.getIlmoqPrice());
            othersPrice.setIlmoqPriceColor(othersPriceDto.getIlmoqPriceColor());
            othersPrice.setZamok155MiniPrice(othersPriceDto.getZamok155MiniPrice());
            othersPrice.setZamok155MaxPrice(othersPriceDto.getZamok155MaxPrice());
            othersPrice.setZamok153Price(othersPriceDto.getZamok153Price());
            othersPrice.setArchaMiniPrice(othersPriceDto.getArchaMiniPrice());
            othersPrice.setArchaBalkonPrice(othersPriceDto.getArchaBalkonPrice());


            otherPriceRepo.save(othersPrice);
            return new ApiResponse("Yangi narxlar qo'shildi",true);
        }else
            return new ApiResponse("Bunday id lik ro'yxat topilmadi",false);



    }

    public ApiResponse deleteById(Long otherPriceId) {
        if (otherPriceRepo.existsById(otherPriceId)){
            otherPriceRepo.deleteById(otherPriceId);
            return new ApiResponse("Ro'yxat o'chirildi.",true);
        }else
            return new ApiResponse("Bunday id lik ro'yxat topilmadi",false);

    }



}
