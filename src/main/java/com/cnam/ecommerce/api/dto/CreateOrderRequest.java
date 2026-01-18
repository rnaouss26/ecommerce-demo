package com.cnam.ecommerce.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class CreateOrderRequest {

    @NotBlank
    private String customerName;

    @Email
    @NotBlank
    private String customerEmail;

    @NotEmpty
    private List<@Valid CreateOrderItem> items;

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public List<CreateOrderItem> getItems() { return items; }
    public void setItems(List<CreateOrderItem> items) { this.items = items; }
}
