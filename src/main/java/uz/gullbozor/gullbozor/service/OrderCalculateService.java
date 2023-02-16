package uz.gullbozor.gullbozor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.dto.ComPrice;
import uz.gullbozor.gullbozor.dto.GetPriceList;
import uz.gullbozor.gullbozor.dto.NewOrderParam;
import uz.gullbozor.gullbozor.dto.PriceCalDto;
import uz.gullbozor.gullbozor.entity.*;
import uz.gullbozor.gullbozor.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
public class OrderCalculateService {

    @Autowired
    private OtherPriceRepo otherPriceRepo;

    @Autowired
    private GlassRepo glassRepo;

    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private OrderBodyPvhWinRepo orderBodyPvhWinRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private  NewComRepo newComRepo;
    @Autowired
    private SimpleJpaRepository simpleJpaRepository;
    @Autowired
    private SmsRepo smsRepo;

    public Integer getPvhL(Integer width, Integer height) {
        return (width + height + 12) * 2;
    }

    public Double getShelfPrice(Byte size, Integer length, Long companyId, Byte colorNum) {

        Optional<OthersPrice> byCompanyId = otherPriceRepo.findByCompanyId(companyId);
        OthersPrice othersPrice = byCompanyId.get();

        if (colorNum == 1) {

            switch (size) {
                case 15: {
                    Double shelf15Price = othersPrice.getShelf15Price();
                    shelf15Price = (shelf15Price * length);
                    return shelf15Price;
                }
                case 20: {
                    Double shelf20Price = othersPrice.getShelf20Price();
                    shelf20Price = (shelf20Price * length);
                    return shelf20Price;
                }
                case 25: {
                    Double shelf25Price = othersPrice.getShelf25Price();
                    shelf25Price = (shelf25Price * length);
                    return shelf25Price;
                }
                case 30: {
                    Double shelf30Price = othersPrice.getShelf30Price();
                    shelf30Price = (shelf30Price * length);
                    return shelf30Price;
                }
                case 35: {
                    Double shelf35Price = othersPrice.getShelf35Price();
                    shelf35Price = (shelf35Price * length);
                    return shelf35Price;
                }
                case 40: {
                    Double shelf40Price = othersPrice.getShelf40Price();
                    shelf40Price = (shelf40Price * length);
                    return shelf40Price;
                }
                case 45: {
                    Double shelf45Price = othersPrice.getShelf45Price();
                    shelf45Price = (shelf45Price * length);
                    return shelf45Price;
                }
                default: {
                    return 0d;
                }
            }
        } else {
            switch (size) {
                case 15: {
                    Double shelf15Price = othersPrice.getShelf15PriceColor();
                    shelf15Price = (shelf15Price * length);
                    return shelf15Price;
                }
                case 20: {
                    Double shelf20Price = othersPrice.getShelf20PriceColor();
                    shelf20Price = (shelf20Price * length);
                    return shelf20Price;
                }
                case 25: {
                    Double shelf25Price = othersPrice.getShelf25PriceColor();
                    shelf25Price = (shelf25Price * length);
                    return shelf25Price;
                }
                case 30: {
                    Double shelf30Price = othersPrice.getShelf30PriceColor();
                    shelf30Price = (shelf30Price * length);
                    return shelf30Price;
                }
                case 35: {
                    Double shelf35Price = othersPrice.getShelf35PriceColor();
                    shelf35Price = (shelf35Price * length);
                    return shelf35Price;
                }
                case 40: {
                    Double shelf40Price = othersPrice.getShelf40PriceColor();
                    shelf40Price = (shelf40Price * length);
                    return shelf40Price;
                }
                case 45: {
                    Double shelf45Price = othersPrice.getShelf45PriceColor();
                    shelf45Price = (shelf45Price * length);
                    return shelf45Price;
                }
                default: {
                    return 0d;
                }
            }


        }


    }

    public Double getGlassPrice(Double kvadrat, Byte glassNum, Long companyId) {

        Optional<OthersPrice> byCompanyId = otherPriceRepo.findByCompanyId(companyId);
        OthersPrice othersPrice = byCompanyId.get();


        switch (glassNum) {
            case 1: {
                return (kvadrat * othersPrice.getOyna1Price());
            }
            case 2: {
                return (kvadrat * othersPrice.getOyna2Price());
            }
            case 3: {
                return (kvadrat * othersPrice.getOyna3Price());
            }
            case 4: {
                return (kvadrat * othersPrice.getOyna4Price());
            }
            default: {
                return 0d;
            }

        }

    }

    public ApiResponse completed(List<NewOrderParam> newOrderParamList) {
        int index = 0;
        Double cost = null;
        Double lastTotalPrice = null;
        Double totalPrice = null;
        Double partOfCompany = null;
        NewOrderParam newOrderParam1 = newOrderParamList.get(0);
        Order order = new Order();
        Long id = order.getId();

        while (!newOrderParamList.isEmpty()) {

            NewOrderParam newOrderParam = newOrderParamList.get(index);
            ApiResponse apiResponse = calculateOrder(newOrderParam);
            ProductHead productHead = (ProductHead) apiResponse.getObject();
            productHead.setOrderId(id);
            cost += productHead.getCost();
            lastTotalPrice += productHead.getLastTotalPrice();
            totalPrice += productHead.getTotalPrice();
            partOfCompany += productHead.getPartOfCompany();
            index++;
        }

        order.setOrderHolderName(newOrderParam1.getOrderHolderName());
        order.setOrderHolderPhone(newOrderParam1.getOrderHolderPhone());

        order.setStatus((byte) 0);
        order.setTotalPrice(totalPrice);
        order.setCost(cost);
        order.setLastTotalPrice(lastTotalPrice);
        order.setPartOfCompany(partOfCompany);

        return new ApiResponse(order);

    }

    public List<GetPriceList> getPriceLists(NewOrderParam newOrderParam, Integer cityId) {

        GetPriceList getPriceList = new GetPriceList();
        List<GetPriceList> getPriceLists = new ArrayList<>();
        int a = 0;

        for (CompanyEntity companyEntity : companyRepo.findAllByCityId(cityId)) {


            Long companyId = companyEntity.getId();

            newOrderParam.setCompanyId(companyId);
            newOrderParam.setResponseToCompany(false);

            ApiResponse apiResponse = calculateOrder(newOrderParam);
            OrderHead orderHead = (OrderHead) apiResponse.getObject();

            getPriceList.setTotalPrice(orderHead.getTotalPrice());
            getPriceList.setLocation(companyEntity.getLocation());
            getPriceList.setTgLink(companyEntity.getTgLink());
            getPriceList.setStars(companyEntity.getStars());
            getPriceList.setCompanyName(companyEntity.getCompanyName());

            getPriceLists.add(getPriceList);

            a++;
            if (a==15) return getPriceLists;


        }
        return getPriceLists;
    }

    public ApiResponse calculateOrder(NewOrderParam newOrderParam) {

        Integer category = newOrderParam.getCategory();
        Optional<OthersPrice> byyCompanyId = otherPriceRepo.findByCompanyId(newOrderParam.getCompanyId());
        OthersPrice othersPrice = byyCompanyId.get();

        OrderBodyPvhWin orderBodyPvhWin = new OrderBodyPvhWin();
        ProductHead productHead = new ProductHead();

        Optional<CompanyEntity> optionalCompany = companyRepo.findById(newOrderParam.getCompanyId());
        CompanyEntity companyEntity = optionalCompany.get();

        Double totalPrice = null;

        Integer height = newOrderParam.getHeight();
        Integer width = newOrderParam.getWidth();

        orderBodyPvhWin.setColor(newOrderParam.getColorNumber());

        Integer L = null;
        Integer T = null;
        Integer Z = null;
        Integer shtapik = null;
        Integer shtapik2 = null;
        Integer chitLength = null;
        Integer rezinaPvhLength = null;
        Integer part = null;
        Integer widthMini = null;

        Glass glass1 = new Glass();
        Glass glass2 = new Glass();
        Glass glass3 = new Glass();
        Glass glass4 = new Glass();
        Glass glass5 = new Glass();
        double kvadrat = Double.parseDouble(null);
        Double partOfWindow = null;

        int middle = 0;

        switch (category) {

            case 1:
            case 2:
            case 3:
            case 4:


//                |------|------|
//                |      |      |
//                |      |      |
//                |      |      |
//                |      |      |
//                |______|______|





                if (category > 2) {
                    orderBodyPvhWin.setIkkitalikMexanizm(othersPrice.getIkkitalikMexanizmPrice());
                }
                // Profillar
                L = getPvhL(width, height);

                T = (height - 96);

                Z = (2 * (T + 21));

                Z = (Z + ((width - 96) / 2) + 2);

                shtapik = (Z - 224);

                //Shtapiklar
                shtapik = (shtapik + (T * 2) + (((width - 96) / 2) - 19) * 2);

                //Oynalar


                glass1.setHeight(height - 111);
                glass1.setWidth((((width - 96) / 2) - 34));
                glass1.setCount((byte) 2);
                Glass save = glassRepo.save(glass1);

                orderBodyPvhWin.setGlass1(save.getId());


                glass2.setHeight(((T - 81)));
                glass2.setWidth((((width - 96) / 2) - 125));
                glass2.setCount((byte) 2);
                Glass save1 = glassRepo.save(glass2);

                orderBodyPvhWin.setGlass2(save1.getId());


                //Chit
                chitLength = (2 * (glass1.getHeight() + glass1.getWidth()) + 2 * (glass2.getWidth() + glass2.getHeight()) - 80);
                rezinaPvhLength = (chitLength + 200);


                if (newOrderParam.getColorNumber() == 1) {

                    orderBodyPvhWin.setPvhLSum((L / 1000) * othersPrice.getPvh60QvtLPrice());
                    orderBodyPvhWin.setPvhTSum((T / 1000) * othersPrice.getPvh60TQvtPrice());
                    orderBodyPvhWin.setPvhZSum((Z / 1000) * othersPrice.getPvh60ZQvtPrice());

                    orderBodyPvhWin.setShtapikSum((shtapik / 1000) * othersPrice.getPvh60TrioShtapikTwoPrice());
                    totalPrice = (orderBodyPvhWin.getPvhLSum() + orderBodyPvhWin.getPvhTSum() + orderBodyPvhWin.getPvhZSum() + orderBodyPvhWin.getShelfSum());

                } else {

                    orderBodyPvhWin.setPvhLSum((L / 1000) * othersPrice.getPvh60QvtLPriceColor());
                    orderBodyPvhWin.setPvhTSum((T / 1000) * othersPrice.getPvh60TQvtPriceColor());
                    orderBodyPvhWin.setPvhZSum((Z / 1000) * othersPrice.getPvh60ZQvtPriceColor());

                    orderBodyPvhWin.setShtapikSum((shtapik / 1000) * othersPrice.getPvh60TrioShtapikTwoPriceColor());

                    totalPrice += (orderBodyPvhWin.getPvhLSum() + orderBodyPvhWin.getPvhTSum() + orderBodyPvhWin.getPvhZSum() + orderBodyPvhWin.getShelfSum());

                }


                //Narxlarni qo'shish *************************************************************************************

                orderBodyPvhWin.setPvhL(L);
                orderBodyPvhWin.setPvhT(T);
                orderBodyPvhWin.setPvhZ(Z);

                orderBodyPvhWin.setShtapik(shtapik);
                orderBodyPvhWin.setShtapikSum((shtapik / 1000) * othersPrice.getAluShtapik2Price());

                orderBodyPvhWin.setChit(Double.valueOf(chitLength));
                orderBodyPvhWin.setChitSum((orderBodyPvhWin.getChit() / 1000) * othersPrice.getChitPrice());

                orderBodyPvhWin.setShelfSum(getShelfPrice(newOrderParam.getShelfSize(), (width + 100), newOrderParam.getCompanyId(), newOrderParam.getColorNumber()));
                totalPrice = (totalPrice + orderBodyPvhWin.getShelfSum());
                orderBodyPvhWin.setShelf_size(newOrderParam.getShelfSize());
                orderBodyPvhWin.setShelf_length(width + 100);

                kvadrat = (glass1.getWidth() * glass1.getHeight() + (glass2.getWidth() * glass2.getHeight()));
                orderBodyPvhWin.setGlassKvadrat(kvadrat);
                orderBodyPvhWin.setGlassSum(getGlassPrice(kvadrat, newOrderParam.getGlassNumber(), newOrderParam.getCompanyId()));

                orderBodyPvhWin.setQoraBurchak((byte) 8);
                orderBodyPvhWin.setSariqBurchak((byte) 2);

                orderBodyPvhWin.setSariqBurchakSum(2 * othersPrice.getSariqBurchakPrice());
                orderBodyPvhWin.setQoraBurchakSum(8 * othersPrice.getQoraBurchakPrice());

                orderBodyPvhWin.setSaydinitel((byte) 2);
                orderBodyPvhWin.setSaydinitelSum(2 * othersPrice.getSaydinitelPrice());

                totalPrice = (totalPrice + orderBodyPvhWin.getGlassSum() + orderBodyPvhWin.getSariqBurchakSum() + orderBodyPvhWin.getQoraBurchakSum());
                if (height > 1490) {
                    orderBodyPvhWin.setPetlya((byte) 3);
                    orderBodyPvhWin.setPetlyaSum(othersPrice.getPetlyaPrice() * 3);
                    orderBodyPvhWin.setSamarez((short) 51);
                    orderBodyPvhWin.setSamarezSum((othersPrice.getSamarezPrice() / 1000) * 24);// Togirlash kere***********************

                    totalPrice = (totalPrice + orderBodyPvhWin.getPetlyaSum() + orderBodyPvhWin.getSamarezSum());
                } else {
                    orderBodyPvhWin.setPetlya((byte) 2);
                    orderBodyPvhWin.setPetlyaSum(othersPrice.getPetlyaPrice() * 3);
                    orderBodyPvhWin.setSamarez((short) 24);
                    orderBodyPvhWin.setSamarezSum((othersPrice.getSamarezPrice() / 1000) * 24);

                    totalPrice = (totalPrice + orderBodyPvhWin.getPetlyaSum() + orderBodyPvhWin.getSamarezSum());
                }


                orderBodyPvhWin.setTotalPrice(totalPrice);
                partOfWindow = companyEntity.getPartOfWindow();
                partOfWindow = ((totalPrice / 100) * partOfWindow);


                orderBodyPvhWin.setOrderCost(totalPrice-partOfWindow);

                orderBodyPvhWin.setCompanyPartPrice(partOfWindow);




                productHead.setPartOfCompany(partOfWindow);
                productHead.setCategory(orderBodyPvhWin.getCategoryNum());
                productHead.setCompanyId(newOrderParam.getCompanyId());
                productHead.setCost(orderBodyPvhWin.getOrderCost());
                productHead.setDiscount(orderBodyPvhWin.getSalePrice());
                productHead.setColorNumber(orderBodyPvhWin.getColor());
                productHead.setGlassNumber(productHead.getGlassNumber());
                productHead.setShelfSize(productHead.getShelfSize());
                productHead.setRuchkaNumber(newOrderParam.getRuchka());
                productHead.setLastTotalPrice(orderBodyPvhWin.getTotalPrice());
                productHead.setCount(newOrderParam.getCount());
                productHead.setHeight(newOrderParam.getHeight());
                productHead.setWidth(newOrderParam.getWidth());


                if (newOrderParam.isResponseToCompany()){
                    return new ApiResponse(orderBodyPvhWin);
                }else {
                    return new ApiResponse(productHead);
                }


            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:

//                |----------|---------|
//                |          |         |
//                |__________|_________|
//                |      |      |      |
//                |      |      |      |
//                |      |      |      |
//                |      |      |      |
//                |______|______|______|



                if ((category == 8) || (category == 9)) {
                    orderBodyPvhWin.setIkkitalikMexanizm(othersPrice.getIkkitalikMexanizmPrice());
                } else if (category == 10) {
                    orderBodyPvhWin.setIkkitalikMexanizm(othersPrice.getIkkitalikMexanizmPrice() * 2);
                }
                // Profillar
                L = getPvhL(width, height);

                T = (height - 96);

                Z = (2 * (T + 21));

                Z = (Z + ((width - 96) / 2) + 2);

                shtapik = (Z - 224);

                //Shtapiklar
                shtapik = (shtapik + (T * 2) + (((width - 96) / 2) - 19) * 2);

                //Oynalar


                glass1.setHeight(height - 111);
                glass1.setWidth((((width - 96) / 2) - 34));
                glass1.setCount((byte) 2);


                glass2.setHeight(((T - 81)));
                glass2.setWidth((((width - 96) / 2) - 125));
                glass2.setCount((byte) 2);

                glassRepo.save(glass1);
                glassRepo.save(glass2);
                //Chit
                chitLength = (2 * (glass1.getHeight() + glass1.getWidth()) + 2 * (glass2.getWidth() + glass2.getHeight()) - 80);
                rezinaPvhLength = (chitLength + 200);


                if (newOrderParam.getColorNumber() == 1) {

                    orderBodyPvhWin.setPvhLSum((L / 1000) * othersPrice.getPvh60QvtLPrice());
                    orderBodyPvhWin.setPvhTSum((T / 1000) * othersPrice.getPvh60TQvtPrice());
                    orderBodyPvhWin.setPvhZSum((Z / 1000) * othersPrice.getPvh60ZQvtPrice());

                    orderBodyPvhWin.setShtapikSum((shtapik / 1000) * othersPrice.getPvh60TrioShtapikTwoPrice());
                    totalPrice = (orderBodyPvhWin.getPvhLSum() + orderBodyPvhWin.getPvhTSum() + orderBodyPvhWin.getPvhZSum() + orderBodyPvhWin.getShelfSum());

                } else {

                    orderBodyPvhWin.setPvhLSum((L / 1000) * othersPrice.getPvh60QvtLPriceColor());
                    orderBodyPvhWin.setPvhTSum((T / 1000) * othersPrice.getPvh60TQvtPriceColor());
                    orderBodyPvhWin.setPvhZSum((Z / 1000) * othersPrice.getPvh60ZQvtPriceColor());

                    orderBodyPvhWin.setShtapikSum((shtapik / 1000) * othersPrice.getPvh60TrioShtapikTwoPriceColor());

                    totalPrice += (orderBodyPvhWin.getPvhLSum() + orderBodyPvhWin.getPvhTSum() + orderBodyPvhWin.getPvhZSum() + orderBodyPvhWin.getShelfSum());

                }


                //Narxlarni qo'shish *************************************************************************************

                orderBodyPvhWin.setPvhL(L);
                orderBodyPvhWin.setPvhT(T);
                orderBodyPvhWin.setPvhZ(Z);

                orderBodyPvhWin.setShtapik(shtapik);
                orderBodyPvhWin.setShtapikSum((shtapik / 1000) * othersPrice.getAluShtapik2Price());

                orderBodyPvhWin.setChit(Double.valueOf(chitLength));
                orderBodyPvhWin.setChitSum((orderBodyPvhWin.getChit() / 1000) * othersPrice.getChitPrice());

                orderBodyPvhWin.setShelfSum(getShelfPrice(newOrderParam.getShelfSize(), (width + 100), newOrderParam.getCompanyId(), newOrderParam.getColorNumber()));
                totalPrice = (totalPrice + orderBodyPvhWin.getShelfSum());
                orderBodyPvhWin.setShelf_size(newOrderParam.getShelfSize());
                orderBodyPvhWin.setShelf_length(width + 100);

                kvadrat = (glass1.getWidth() * glass1.getHeight() + (glass2.getWidth() * glass2.getHeight()));
                orderBodyPvhWin.setGlassKvadrat(kvadrat);
                orderBodyPvhWin.setGlassSum(getGlassPrice(kvadrat, newOrderParam.getGlassNumber(), newOrderParam.getCompanyId()));

                orderBodyPvhWin.setQoraBurchak((byte) 8);
                orderBodyPvhWin.setSariqBurchak((byte) 2);

                orderBodyPvhWin.setSariqBurchakSum(2 * othersPrice.getSariqBurchakPrice());
                orderBodyPvhWin.setQoraBurchakSum(8 * othersPrice.getQoraBurchakPrice());

                orderBodyPvhWin.setSaydinitel((byte) 2);
                orderBodyPvhWin.setSaydinitelSum(2 * othersPrice.getSaydinitelPrice());

                totalPrice = (totalPrice + orderBodyPvhWin.getGlassSum() + orderBodyPvhWin.getSariqBurchakSum() + orderBodyPvhWin.getQoraBurchakSum());
                if (height > 1490) {
                    orderBodyPvhWin.setPetlya((byte) 3);
                    orderBodyPvhWin.setPetlyaSum(othersPrice.getPetlyaPrice() * 3);
                    orderBodyPvhWin.setSamarez((short) 51);
                    orderBodyPvhWin.setSamarezSum((othersPrice.getSamarezPrice() / 1000) * 24);// Togirlash kere***********************

                    totalPrice = (totalPrice + orderBodyPvhWin.getPetlyaSum() + orderBodyPvhWin.getSamarezSum());
                } else {
                    orderBodyPvhWin.setPetlya((byte) 2);
                    orderBodyPvhWin.setPetlyaSum(othersPrice.getPetlyaPrice() * 3);
                    orderBodyPvhWin.setSamarez((short) 24);
                    orderBodyPvhWin.setSamarezSum((othersPrice.getSamarezPrice() / 1000) * 24);

                    totalPrice = (totalPrice + orderBodyPvhWin.getPetlyaSum() + orderBodyPvhWin.getSamarezSum());
                }


                orderBodyPvhWin.setTotalPrice(totalPrice);

                partOfWindow = companyEntity.getPartOfWindow();

                partOfWindow = ((totalPrice / 100) * partOfWindow);


                orderBodyPvhWin.setCompanyPartPrice(partOfWindow);

                productHead.setPartOfCompany(partOfWindow);
                productHead.setCategory(orderBodyPvhWin.getCategoryNum());
                productHead.setCompanyId(newOrderParam.getCompanyId());
                productHead.setCost(orderBodyPvhWin.getOrderCost());
                productHead.setDiscount(orderBodyPvhWin.getSalePrice());
                productHead.setColorNumber(orderBodyPvhWin.getColor());
                productHead.setGlassNumber(productHead.getGlassNumber());
                productHead.setShelfSize(productHead.getShelfSize());
                productHead.setRuchkaNumber(newOrderParam.getRuchka());
                productHead.setLastTotalPrice(orderBodyPvhWin.getTotalPrice());
                productHead.setCount(newOrderParam.getCount());
                productHead.setHeight(newOrderParam.getHeight());
                productHead.setWidth(newOrderParam.getWidth());

                if (newOrderParam.isResponseToCompany()){
                    return new ApiResponse(orderBodyPvhWin);
                }else {
                    return new ApiResponse(productHead);
                }


            case 11:
            case 12:
            case 13:
            case 14:


//                |------|------|------|
//                |      |      |      |
//                |      |      |      |
//                |      |      |      |
//                |      |      |      |
//                |______|______|______|


                if ((category == 13) || category == 14) {
                    orderBodyPvhWin.setIkkitalikMexanizm(othersPrice.getIkkitalikMexanizmPrice());
                } else {
                    orderBodyPvhWin.setIkkitalikMexanizm(0d);
                }

                // Profillar
                L = getPvhL(width, height);

                T = ((height - 96) * 2);

                part = ((width - 270) / 3);

                widthMini = (part + 67);

                orderBodyPvhWin.setWidthMini(widthMini);

                middle = ((width - 172) - (part * 2));

                Z = (((height + 21) * 2) + ((middle + 21) * 2));

                shtapik = ((Z - 224) + (widthMini * 4) + (height - 96) * 4);


                glass1.setHeight(height - 111);
                glass1.setWidth(part - 15);
                glass1.setCount((byte) 4);

                glass2.setHeight(T - 82);
                glass2.setWidth(middle - 82);
                glass2.setCount((byte) 2);

                glassRepo.save(glass1);
                glassRepo.save(glass2);


                //Chit
                chitLength = (2 * (2 * (glass1.getHeight() + glass1.getWidth())) + (glass2.getWidth() + glass2.getHeight()) * 2 - 120);
                rezinaPvhLength = (chitLength + 200);


                if (newOrderParam.getColorNumber() == 1) {

                    orderBodyPvhWin.setPvhLSum((L / 1000) * othersPrice.getPvh60QvtLPrice());
                    orderBodyPvhWin.setPvhTSum((T / 1000) * othersPrice.getPvh60TQvtPrice());
                    orderBodyPvhWin.setPvhZSum((Z / 1000) * othersPrice.getPvh60ZQvtPrice());

                    orderBodyPvhWin.setShtapikSum((shtapik / 1000) * othersPrice.getPvh60TrioShtapikTwoPrice());
                    totalPrice = (orderBodyPvhWin.getPvhLSum() + orderBodyPvhWin.getPvhTSum() + orderBodyPvhWin.getPvhZSum() + orderBodyPvhWin.getShelfSum());

                } else {

                    orderBodyPvhWin.setPvhLSum((L / 1000) * othersPrice.getPvh60QvtLPriceColor());
                    orderBodyPvhWin.setPvhTSum((T / 1000) * othersPrice.getPvh60TQvtPriceColor());
                    orderBodyPvhWin.setPvhZSum((Z / 1000) * othersPrice.getPvh60ZQvtPriceColor());

                    orderBodyPvhWin.setShtapikSum((shtapik / 1000) * othersPrice.getPvh60TrioShtapikTwoPriceColor());

                    totalPrice += (orderBodyPvhWin.getPvhLSum() + orderBodyPvhWin.getPvhTSum() + orderBodyPvhWin.getPvhZSum() + orderBodyPvhWin.getShelfSum());

                }


                //Narxlarni qo'shish *************************************************************************************

                orderBodyPvhWin.setPvhL(L);
                orderBodyPvhWin.setPvhT(T);
                orderBodyPvhWin.setPvhZ(Z);


                orderBodyPvhWin.setShtapik(shtapik);

                orderBodyPvhWin.setShelfSum(getShelfPrice(newOrderParam.getShelfSize(), (width + 100), newOrderParam.getCompanyId(), newOrderParam.getColorNumber()));
                totalPrice = (totalPrice + orderBodyPvhWin.getShelfSum());
                orderBodyPvhWin.setShelf_size(newOrderParam.getShelfSize());
                orderBodyPvhWin.setShelf_length(width + 100);

                kvadrat = (glass1.getWidth() * glass1.getHeight() + (glass2.getWidth() * glass2.getHeight()));
                orderBodyPvhWin.setGlassKvadrat(kvadrat);
                orderBodyPvhWin.setGlassSum(getGlassPrice(kvadrat, newOrderParam.getGlassNumber(), newOrderParam.getCompanyId()));


                orderBodyPvhWin.setQoraBurchak((byte) 12);
                orderBodyPvhWin.setSariqBurchak((byte) 2);

                orderBodyPvhWin.setSariqBurchakSum(orderBodyPvhWin.getQoraBurchak() * othersPrice.getSariqBurchakPrice());
                orderBodyPvhWin.setQoraBurchakSum(orderBodyPvhWin.getSariqBurchak() * othersPrice.getQoraBurchakPrice());

                orderBodyPvhWin.setSaydinitel((byte) 4);
                orderBodyPvhWin.setSaydinitelSum(orderBodyPvhWin.getSaydinitel() * othersPrice.getSaydinitelPrice());

                totalPrice = (totalPrice + orderBodyPvhWin.getGlassSum() + orderBodyPvhWin.getSariqBurchakSum() + orderBodyPvhWin.getQoraBurchakSum());
                if (height > 1490) {
                    orderBodyPvhWin.setPetlya((byte) 3);
                    orderBodyPvhWin.setPetlyaSum(othersPrice.getPetlyaPrice() * orderBodyPvhWin.getPetlya());
                    orderBodyPvhWin.setSamarez((short) 51);
                    orderBodyPvhWin.setSamarezSum((othersPrice.getSamarezPrice() / 1000) * 24);// Togirlash kere***********************

                    totalPrice = (totalPrice + orderBodyPvhWin.getPetlyaSum() + orderBodyPvhWin.getSamarezSum());
                } else {
                    orderBodyPvhWin.setPetlya((byte) 2);
                    orderBodyPvhWin.setPetlyaSum(othersPrice.getPetlyaPrice() * 3);
                    orderBodyPvhWin.setSamarez((short) 24);
                    orderBodyPvhWin.setSamarezSum((othersPrice.getSamarezPrice() / 1000) * 24);

                    totalPrice = (totalPrice + orderBodyPvhWin.getPetlyaSum() + orderBodyPvhWin.getSamarezSum());
                }


                orderBodyPvhWin.setTotalPrice(totalPrice);

                partOfWindow = companyEntity.getPartOfWindow();


                partOfWindow = ((totalPrice / 100) * partOfWindow);


                orderBodyPvhWin.setCompanyPartPrice(partOfWindow);

                productHead.setPartOfCompany(partOfWindow);
                productHead.setCategory(orderBodyPvhWin.getCategoryNum());
                productHead.setCompanyId(newOrderParam.getCompanyId());
                productHead.setCost(orderBodyPvhWin.getOrderCost());
                productHead.setDiscount(orderBodyPvhWin.getSalePrice());
                productHead.setColorNumber(orderBodyPvhWin.getColor());
                productHead.setGlassNumber(productHead.getGlassNumber());
                productHead.setShelfSize(productHead.getShelfSize());
                productHead.setRuchkaNumber(newOrderParam.getRuchka());
                productHead.setLastTotalPrice(orderBodyPvhWin.getTotalPrice());
                productHead.setCount(newOrderParam.getCount());
                productHead.setHeight(newOrderParam.getHeight());
                productHead.setWidth(newOrderParam.getWidth());

                if (newOrderParam.isResponseToCompany()){
                    return new ApiResponse(orderBodyPvhWin);
                }else {
                    return new ApiResponse(productHead);
                }


            case 15:
            case 16:
            case 17:
            case 18:

//                |------|------|------|
//                |      |      |      |
//                |      |      |      |
//                |      |      |      |
//                |      |      |      |
//                |______|______|______|


                if ((category == 16) || category == 17) {
                    orderBodyPvhWin.setIkkitalikMexanizm(othersPrice.getIkkitalikMexanizmPrice());
                } else if (category == 18) {
                    orderBodyPvhWin.setIkkitalikMexanizm(othersPrice.getIkkitalikMexanizmPrice() * 2);
                }

                // Profillar
                L = getPvhL(width, height);

                T = ((height - 96) * 2);

                part = ((width - 270) / 3);

                widthMini = (part + 67);

                orderBodyPvhWin.setWidthMini(widthMini);

                Z = 4 * (part + 42 + T);

                shtapik = (Z - 448);

                shtapik = (shtapik + ((T * 2) + ((part) * 2)));

                glass1.setHeight(height - 111);
                glass1.setWidth(part - 15);
                glass1.setCount((byte) 2);

                glass2.setHeight(T - 112);
                glass2.setWidth(part - 112);
                glass2.setCount((byte) 4);

                glassRepo.save(glass1);
                glassRepo.save(glass2);

                //Chit
                chitLength = (2 * (glass1.getHeight() + glass1.getWidth()) - 120);
                rezinaPvhLength = (shtapik);


                if (newOrderParam.getColorNumber() == 1) {

                    orderBodyPvhWin.setPvhLSum((L / 1000) * othersPrice.getPvh60QvtLPrice());
                    orderBodyPvhWin.setPvhTSum((T / 1000) * othersPrice.getPvh60TQvtPrice());
                    orderBodyPvhWin.setPvhZSum((Z / 1000) * othersPrice.getPvh60ZQvtPrice());

                    orderBodyPvhWin.setShtapikSum((shtapik / 1000) * othersPrice.getPvh60TrioShtapikTwoPrice());
                    totalPrice = (orderBodyPvhWin.getPvhLSum() + orderBodyPvhWin.getPvhTSum() + orderBodyPvhWin.getPvhZSum() + orderBodyPvhWin.getShelfSum());

                } else {

                    orderBodyPvhWin.setPvhLSum((L / 1000) * othersPrice.getPvh60QvtLPriceColor());
                    orderBodyPvhWin.setPvhTSum((T / 1000) * othersPrice.getPvh60TQvtPriceColor());
                    orderBodyPvhWin.setPvhZSum((Z / 1000) * othersPrice.getPvh60ZQvtPriceColor());

                    orderBodyPvhWin.setShtapikSum((shtapik / 1000) * othersPrice.getPvh60TrioShtapikTwoPriceColor());

                    totalPrice += (orderBodyPvhWin.getPvhLSum() + orderBodyPvhWin.getPvhTSum() + orderBodyPvhWin.getPvhZSum() + orderBodyPvhWin.getShelfSum());

                }


                //Narxlarni qo'shish *************************************************************************************

                orderBodyPvhWin.setPvhL(L);
                orderBodyPvhWin.setPvhT(T);
                orderBodyPvhWin.setPvhZ(Z);


                orderBodyPvhWin.setShtapik(shtapik);

                orderBodyPvhWin.setShelfSum(getShelfPrice(newOrderParam.getShelfSize(), (width + 100), newOrderParam.getCompanyId(), newOrderParam.getColorNumber()));
                totalPrice = (totalPrice + orderBodyPvhWin.getShelfSum());
                orderBodyPvhWin.setShelf_size(newOrderParam.getShelfSize());
                orderBodyPvhWin.setShelf_length(width + 100);

                kvadrat = (glass1.getWidth() * glass1.getHeight() + (glass2.getWidth() * glass2.getHeight()));
                orderBodyPvhWin.setGlassKvadrat(kvadrat);
                orderBodyPvhWin.setGlassSum(getGlassPrice(kvadrat, newOrderParam.getGlassNumber(), newOrderParam.getCompanyId()));

                orderBodyPvhWin.setQoraBurchak((byte) 12);
                orderBodyPvhWin.setSariqBurchak((byte) 2);

                orderBodyPvhWin.setSariqBurchakSum(orderBodyPvhWin.getQoraBurchak() * othersPrice.getSariqBurchakPrice());
                orderBodyPvhWin.setQoraBurchakSum(orderBodyPvhWin.getSariqBurchak() * othersPrice.getQoraBurchakPrice());

                orderBodyPvhWin.setSaydinitel((byte) 4);
                orderBodyPvhWin.setSaydinitelSum(orderBodyPvhWin.getSaydinitel() * othersPrice.getSaydinitelPrice());

                totalPrice = (totalPrice + orderBodyPvhWin.getGlassSum() + orderBodyPvhWin.getSariqBurchakSum() + orderBodyPvhWin.getQoraBurchakSum());
                if (height > 1490) {
                    orderBodyPvhWin.setPetlya((byte) 3);
                    orderBodyPvhWin.setPetlyaSum(othersPrice.getPetlyaPrice() * orderBodyPvhWin.getPetlya());
                    orderBodyPvhWin.setSamarez((short) 51);
                    orderBodyPvhWin.setSamarezSum((othersPrice.getSamarezPrice() / 1000) * 24);// Togirlash kere***********************

                    totalPrice = (totalPrice + orderBodyPvhWin.getPetlyaSum() + orderBodyPvhWin.getSamarezSum());
                } else {
                    orderBodyPvhWin.setPetlya((byte) 2);
                    orderBodyPvhWin.setPetlyaSum(othersPrice.getPetlyaPrice() * 3);
                    orderBodyPvhWin.setSamarez((short) 24);
                    orderBodyPvhWin.setSamarezSum((othersPrice.getSamarezPrice() / 1000) * 24);

                    totalPrice = (totalPrice + orderBodyPvhWin.getPetlyaSum() + orderBodyPvhWin.getSamarezSum());
                }


                orderBodyPvhWin.setTotalPrice(totalPrice);

                partOfWindow = companyEntity.getPartOfWindow();
                partOfWindow = ((totalPrice / 100) * partOfWindow);


                orderBodyPvhWin.setCompanyPartPrice(partOfWindow);

                productHead.setPartOfCompany(partOfWindow);
                productHead.setCategory(orderBodyPvhWin.getCategoryNum());
                productHead.setCompanyId(newOrderParam.getCompanyId());
                productHead.setCost(orderBodyPvhWin.getOrderCost());
                productHead.setDiscount(orderBodyPvhWin.getSalePrice());
                productHead.setColorNumber(orderBodyPvhWin.getColor());
                productHead.setGlassNumber(productHead.getGlassNumber());
                productHead.setShelfSize(productHead.getShelfSize());
                productHead.setRuchkaNumber(newOrderParam.getRuchka());
                productHead.setLastTotalPrice(orderBodyPvhWin.getTotalPrice());
                productHead.setCount(newOrderParam.getCount());
                productHead.setHeight(newOrderParam.getHeight());
                productHead.setWidth(newOrderParam.getWidth());

                if (newOrderParam.isResponseToCompany()){
                    return new ApiResponse(orderBodyPvhWin);
                }else {
                    return new ApiResponse(productHead);
                }

            case 19:
            case 20:

//  Eshik
//               |-------------|
//               |             |
//               |             |
//               |             |
//               |             |
//               |             |
//               |             |
//               |-------------|
//               |  |  |  |  | |
//               |  |  |  |  | |
//               |  |  |  |  | |
//               |__|__|__|__|_|




                // Profillar
                L = getPvhL(width, height);

                T = ((height - 96) * 2);

                part = ((width - 270) / 3);

                widthMini = (part + 67);

                orderBodyPvhWin.setWidthMini(widthMini);

                Z = 4 * (part + 42 + T);

                shtapik = (Z - 448);

                shtapik = (shtapik + ((T * 2) + ((part) * 2)));

                glass1.setHeight(height - 111);
                glass1.setWidth(part - 15);
                glass1.setCount((byte) 2);

                glass2.setHeight(T - 112);
                glass2.setWidth(part - 112);
                glass2.setCount((byte) 4);

                glassRepo.save(glass1);
                glassRepo.save(glass2);

                //Chit
                chitLength = (2 * (glass1.getHeight() + glass1.getWidth()) - 120);
                rezinaPvhLength = (shtapik);


                if (newOrderParam.getColorNumber() == 1) {

                    orderBodyPvhWin.setPvhLSum((L / 1000) * othersPrice.getPvh60QvtLPrice());
                    orderBodyPvhWin.setPvhTSum((T / 1000) * othersPrice.getPvh60TQvtPrice());
                    orderBodyPvhWin.setPvhZSum((Z / 1000) * othersPrice.getPvh60ZQvtPrice());

                    orderBodyPvhWin.setShtapikSum((shtapik / 1000) * othersPrice.getPvh60TrioShtapikTwoPrice());
                    totalPrice = (orderBodyPvhWin.getPvhLSum() + orderBodyPvhWin.getPvhTSum() + orderBodyPvhWin.getPvhZSum() + orderBodyPvhWin.getShelfSum());

                } else {

                    orderBodyPvhWin.setPvhLSum((L / 1000) * othersPrice.getPvh60QvtLPriceColor());
                    orderBodyPvhWin.setPvhTSum((T / 1000) * othersPrice.getPvh60TQvtPriceColor());
                    orderBodyPvhWin.setPvhZSum((Z / 1000) * othersPrice.getPvh60ZQvtPriceColor());

                    orderBodyPvhWin.setShtapikSum((shtapik / 1000) * othersPrice.getPvh60TrioShtapikTwoPriceColor());

                    totalPrice += (orderBodyPvhWin.getPvhLSum() + orderBodyPvhWin.getPvhTSum() + orderBodyPvhWin.getPvhZSum() + orderBodyPvhWin.getShelfSum());

                }


                //Narxlarni qo'shish *************************************************************************************

                orderBodyPvhWin.setPvhL(L);
                orderBodyPvhWin.setPvhT(T);
                orderBodyPvhWin.setPvhZ(Z);


                orderBodyPvhWin.setShtapik(shtapik);

                orderBodyPvhWin.setShelfSum(getShelfPrice(newOrderParam.getShelfSize(), (width + 100), newOrderParam.getCompanyId(), newOrderParam.getColorNumber()));
                totalPrice = (totalPrice + orderBodyPvhWin.getShelfSum());
                orderBodyPvhWin.setShelf_size(newOrderParam.getShelfSize());
                orderBodyPvhWin.setShelf_length(width + 100);

                kvadrat = (glass1.getWidth() * glass1.getHeight() + (glass2.getWidth() * glass2.getHeight()));
                orderBodyPvhWin.setGlassKvadrat(kvadrat);
                orderBodyPvhWin.setGlassSum(getGlassPrice(kvadrat, newOrderParam.getGlassNumber(), newOrderParam.getCompanyId()));

                orderBodyPvhWin.setQoraBurchak((byte) 12);
                orderBodyPvhWin.setSariqBurchak((byte) 2);

                orderBodyPvhWin.setSariqBurchakSum(orderBodyPvhWin.getQoraBurchak() * othersPrice.getSariqBurchakPrice());
                orderBodyPvhWin.setQoraBurchakSum(orderBodyPvhWin.getSariqBurchak() * othersPrice.getQoraBurchakPrice());

                orderBodyPvhWin.setSaydinitel((byte) 4);
                orderBodyPvhWin.setSaydinitelSum(orderBodyPvhWin.getSaydinitel() * othersPrice.getSaydinitelPrice());

                totalPrice = (totalPrice + orderBodyPvhWin.getGlassSum() + orderBodyPvhWin.getSariqBurchakSum() + orderBodyPvhWin.getQoraBurchakSum());
                if (height > 1490) {
                    orderBodyPvhWin.setPetlya((byte) 3);
                    orderBodyPvhWin.setPetlyaSum(othersPrice.getPetlyaPrice() * orderBodyPvhWin.getPetlya());
                    orderBodyPvhWin.setSamarez((short) 51);
                    orderBodyPvhWin.setSamarezSum((othersPrice.getSamarezPrice() / 1000) * 24);// Togirlash kere***********************

                    totalPrice = (totalPrice + orderBodyPvhWin.getPetlyaSum() + orderBodyPvhWin.getSamarezSum());
                } else {
                    orderBodyPvhWin.setPetlya((byte) 2);
                    orderBodyPvhWin.setPetlyaSum(othersPrice.getPetlyaPrice() * 3);
                    orderBodyPvhWin.setSamarez((short) 24);
                    orderBodyPvhWin.setSamarezSum((othersPrice.getSamarezPrice() / 1000) * 24);

                    totalPrice = (totalPrice + orderBodyPvhWin.getPetlyaSum() + orderBodyPvhWin.getSamarezSum());
                }


                orderBodyPvhWin.setTotalPrice(totalPrice);

                partOfWindow = companyEntity.getPartOfWindow();


                partOfWindow = ((totalPrice / 100) * partOfWindow);


                orderBodyPvhWin.setCompanyPartPrice(partOfWindow);

                productHead.setPartOfCompany(partOfWindow);
                productHead.setCategory(orderBodyPvhWin.getCategoryNum());
                productHead.setCompanyId(newOrderParam.getCompanyId());
                productHead.setCost(orderBodyPvhWin.getOrderCost());
                productHead.setDiscount(orderBodyPvhWin.getSalePrice());
                productHead.setColorNumber(orderBodyPvhWin.getColor());
                productHead.setGlassNumber(productHead.getGlassNumber());
                productHead.setShelfSize(productHead.getShelfSize());
                productHead.setRuchkaNumber(newOrderParam.getRuchka());
                productHead.setLastTotalPrice(orderBodyPvhWin.getTotalPrice());
                productHead.setCount(newOrderParam.getCount());
                productHead.setHeight(newOrderParam.getHeight());
                productHead.setWidth(newOrderParam.getWidth());

                if (newOrderParam.isResponseToCompany()){
                    return new ApiResponse(orderBodyPvhWin);
                }else {
                    return new ApiResponse(productHead);
                }


            case 21:
                break;
            case 22:
                break;
            case 23:
                break;
            case 24:
                break;
            case 25:
                break;
            case 26:
                break;
            case 27:
                break;
            case 28:
                break;
            case 29:
                break;
            case 30:
                break;
            case 31:
                break;
            case 32:
                break;

            default:
                throw new IllegalStateException("Mavjud bo'lmagan kategorya raqami: " + category);
        }
        return new ApiResponse("Xatolik", false);
    }

    public ApiResponse narxHisoblash(NewOrderParam newOrderParam) {

        List<ComPrice> comPriceList = new ArrayList<>();

        for (NewCom newCom : newComRepo.findAllByRegion(newOrderParam.getRegion())) {












        }
    }

    public Long romNarxiYodOynali(Long winPrice, Long glassPrice, Integer height, Integer width) {
        long sum = (height * width * glassPrice);
        sum = sum + (winPrice * height * width);

        return sum;
    }

    public Long romNarxiYodOynaliBirMexanizmli(Long mexanizmPrice, Long winPrice, Long glassPrice, Integer height, Integer width) {
        long sum = (height * width * glassPrice);
        sum = sum + (winPrice * height * width);
        sum = (sum + mexanizmPrice);

        return sum;
    }

    public Long romNarxiYodOynaliIkkiMexanizmli(Long mexanizmPrice, Long winPrice, Long glassPrice, Integer height, Integer width) {

        long sum = (height * width * glassPrice);
        sum = sum + (winPrice * height * width);
        sum = (sum + (mexanizmPrice * 2));

        return sum;
    }


    public void oq(NewOrderParam newOrderParam, Long yodOynaNarxi) {

        PriceCalDto priceCalDto = new PriceCalDto();

        Integer category = newOrderParam.getCategory();

        switch (category) {

            case 1:
            case 2:
                break;
            case 3:
            case 4:
                break;
            case 5:
            case 6:
                break;
            case 7:
            case 8:
                break;



            case 9:
            case 10:
                break;
            case 11:
            case 12:
            case 13:
            case 14:
                break;
            case 15:
            case 16:
                break;
            case 17:
            case 18:
                break;
            case 19:
                break;
            case 20:
                break;
            case 21:
                break;
            case 22:
                break;
            case 23:
                break;
            case 24:
                break;
            case 25:
                break;
            case 26:
                break;
            case 27:
                break;
            case 28:
                break;
            case 29:
                break;
            case 30:
                break;
            case 31:
                break;
            case 32:
                break;
            case 33:
                break;
            case 34:
                break;
            case 35:
                break;
            case 36:
                break;
            case 37:
                break;
            case 38:
                break;
            case 39:
                break;
            case 40:
                break;
            case 41:
                break;
            case 42:
                break;
            case 43:
                break;
            case 44:
                break;
            case 45:
                break;
            case 46:
                break;
            case 47:
                break;
            case 48:
                break;
            case 49:
                break;
            case 50:
                break;
            case 51:
                break;
            case 52:
                break;
            case 53:
                break;
            case 54:
                break;
            case 55:
                break;
            case 56:
                break;
            case 57:
                break;
            case 58:
                break;
            case 59:
                break;
            case 60:
                break;
            case 61:
                break;
            case 62:
                break;
            case 63:
                break;
            case 64:
                break;
            case 65:
                break;
            case 66:
                break;
            case 67:
                break;
            case 68:
                break;
            case 69:
                break;
            case 70:
                break;
            case 71:
                break;
            case 72:
                break;
            case 73:
                break;
            case 74:
                break;
            case 75:
                break;
            case 76:
                break;
            case 77:
                break;
            case 78:
                break;
            case 79:
                break;
            case 80:
                break;
            case 81:
                break;
            case 82:
                break;
            case 83:
                break;
            case 84:
                break;
            case 85:
                break;
            case 86:
                break;
            case 87:
                break;
            case 88:
                break;
            case 89:
                break;
            case 90:
                break;
            case 91:
                break;
            case 92:
                break;
            case 93:
                break;
            case 94:
                break;
            case 95:
                break;
            case 96:
                break;
            case 97:
                break;
            case 98:
                break;
            case 99:
                break;
            case 100:
                break;
            case 101:
                break;
            case 102:
                break;
            case 103:
                break;
            case 104:
                break;
            case 105:
                break;
            case 106:
                break;
            case 107:
                break;
            case 108:
                break;
            case 109:
                break;
            case 110:
                break;
            case 111:
                break;
            case 112:
                break;
            case 113:
                break;
            case 114:
                break;
            case 115:
                break;
            case 116:
                break;
            case 117:
                break;
            case 118:
                break;
            case 119:
                break;
            case 120:
                break;
            case 121:
                break;
            case 122:
                break;
            case 123:
                break;
            case 124:
                break;
            case 125:
                break;
            case 126:
                break;
            case 127:
                break;
            case 128:
                break;


        }


    }
}

























