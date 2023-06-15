package lk.ijse.project_wineverse.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetailDTO {
    private String orderId;
    private String custid;
    private String custname;
    private Boolean delivery;
    private String date;
    private String time;
    private Double price;
    private List<PlaceOrderDTO> placeOrderDTOList;

    public OrderDetailDTO(String orderid, String custid, Boolean delivery, String ordpay, List<PlaceOrderDTO> placeOrderList) {
        this.orderId=orderid;
        this.custid=custid;
        this.delivery=delivery;
        this.price= Double.valueOf(ordpay);
        this.placeOrderDTOList=placeOrderList;
    }
}
