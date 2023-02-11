package com.project3.orderservice.dto;

 lombok.AllArgsConstructor;
import com.project3.orderservice.model.OrderLineItems;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private List<OrderLineItemsDto> orderLineItemsDtoList;
}
}
