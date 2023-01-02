package uz.gullbozor.gullbozor.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.entity.Order;
import uz.gullbozor.gullbozor.repository.OrderRepo;

import java.util.List;


@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    public ApiResponse addOrder(Order order) {
        orderRepo.save(order);
        return new ApiResponse("Buyurtma saqlandi",true);
    }

    public ApiResponse editOrder(Order orderDto) {
        orderRepo.save(orderDto);
        return new ApiResponse("Buyurtma saqlandi",true);
    }

    public List<Order> getByStatusId(Byte statusId, Long companyId) {

        List<Order> orderList = orderRepo.findAllByCompanyIdAndStatus(companyId,statusId);

        return orderList;

    }

    public List<Order> getAllByCompanyId(Long companyId) {
        return orderRepo.findAllByCompanyId(companyId);
    }

    public ApiResponse deleteById(Long orderId) {
        if (orderRepo.existsById(orderId)) {
            orderRepo.deleteById(orderId);
            return new ApiResponse("Buyurtma o'chirildi",true);

        }else {
            return new ApiResponse("Buyurtma topilamdi",false);

        }
    }

}
