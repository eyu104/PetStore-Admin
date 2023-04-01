package com.csu.petstoreadmin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@TableName("orders")
public class Order implements Serializable {
    private static final long serialVersionUID = 6321792448424424931L;


    @TableId
    private int orderId;

    private String userId;

    private Date orderDate;

    private String shipAddr1;
    private String shipAddr2;
    private String shipCity;
    private String shipState;
    private String shipZip;
    private String shipCountry;

    private String billAddr1;
    private String billAddr2;
    private String billCity;
    private String billState;
    private String billZip;
    private String billCountry;
    private String courier;
    private BigDecimal totalPrice;
    private String billToFirstName;
    private String billToLastName;
    private String shipToFirstName;
    private String shipToLastName;
    private String creditCard;
    private String exprDate;
    private String cardType;
    private String locale;
    @TableField(exist = false)
    private String status;
    @TableField(exist = false)
    private List<LineItem> lineItems = new ArrayList<LineItem>();

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getOrderDate() {
        if (orderDate == null){
            orderDate = new Date();
        }
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getShipAddr1() {
        return shipAddr1;
    }

    public void setShipAddr1(String shipAddr1) {
        this.shipAddr1 = shipAddr1;
    }

    public String getShipAddr2() {
        return shipAddr2;
    }

    public void setShipAddr2(String shipAddr2) {
        this.shipAddr2 = shipAddr2;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipState() {
        return shipState;
    }

    public void setShipState(String shipState) {
        this.shipState = shipState;
    }

    public String getShipZip() {
        return shipZip;
    }

    public void setShipZip(String shipZip) {
        this.shipZip = shipZip;
    }

    public String getShipCountry() {
        return shipCountry;
    }

    public void setShipCountry(String shipCountry) {
        this.shipCountry = shipCountry;
    }

    public String getBillAddr1() {
        return billAddr1;
    }

    public void setBillAddr1(String billAddr1) {
        this.billAddr1 = billAddr1;
    }

    public String getBillAddr2() {
        return billAddr2;
    }

    public void setBillAddr2(String billAddr2) {
        this.billAddr2 = billAddr2;
    }

    public String getBillCity() {
        return billCity;
    }

    public void setBillCity(String billCity) {
        this.billCity = billCity;
    }

    public String getBillState() {
        return billState;
    }

    public void setBillState(String billState) {
        this.billState = billState;
    }

    public String getBillZip() {
        return billZip;
    }

    public void setBillZip(String billZip) {
        this.billZip = billZip;
    }

    public String getBillCountry() {
        return billCountry;
    }

    public void setBillCountry(String billCountry) {
        this.billCountry = billCountry;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getBillToFirstName() {
        return billToFirstName;
    }

    public void setBillToFirstName(String billToFirstName) {
        this.billToFirstName = billToFirstName;
    }

    public String getBillToLastName() {
        return billToLastName;
    }

    public void setBillToLastName(String billToLastName) {
        this.billToLastName = billToLastName;
    }

    public String getShipToFirstName() {
        return shipToFirstName;
    }

    public void setShipToFirstName(String shipFoFirstName) {
        this.shipToFirstName = shipFoFirstName;
    }

    public String getShipToLastName() {
        return shipToLastName;
    }

    public void setShipToLastName(String shipToLastName) {
        this.shipToLastName = shipToLastName;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getExprDate() {
        return exprDate;
    }

    public void setExprDate(String exprDate) {
        this.exprDate = exprDate;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void initOrder(Account account, Cart cart) {

        userId = account.getUsername();
        orderDate = new Date();

        shipToFirstName = account.getFirstName();
        shipToLastName = account.getLastName();
        shipAddr1 = account.getAddress1();
        shipAddr2 = account.getAddress2();
        shipCity = account.getCity();
        shipState = account.getState();
        shipZip = account.getZip();
        shipCountry = account.getCountry();

        billToFirstName = account.getFirstName();
        billToLastName = account.getLastName();
        billAddr1 = account.getAddress1();
        billAddr2 = account.getAddress2();
        billCity = account.getCity();
        billState = account.getState();
        billZip = account.getZip();
        billCountry = account.getCountry();

        totalPrice = cart.getSubTotal();

        creditCard = "999 9999 9999 9999";
        exprDate = "12/03";
        cardType = "Visa";
        courier = "UPS";
        locale = "CA";
        status = "P";

        Iterator<CartItem> i = cart.getAllCartItems();
        while (i.hasNext()) {
            CartItem cartItem = (CartItem) i.next();
            addLineItem(cartItem);
        }

    }

    public void addLineItem(CartItem cartItem) {
        LineItem lineItem = new LineItem(lineItems.size() + 1, cartItem);
        addLineItem(lineItem);
    }

    public void addLineItem(LineItem lineItem) {
        lineItems.add(lineItem);
    }

    public void getTotal(){
        this.totalPrice = BigDecimal.ZERO;
        for (LineItem i :
                this.lineItems) {
            this.totalPrice.add(i.getTotal());
        }

    }
}
