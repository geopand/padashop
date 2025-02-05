package gr.padashop.models;

import java.util.List;

public class Order {

    private Long id;
    private String userId;
    private String ccName;
    private String ccType;
    private String ccNumber;
    private String ccExpiration;
    private List<OrderDetail> details;


}
