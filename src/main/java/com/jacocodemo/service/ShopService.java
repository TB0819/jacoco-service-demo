package com.jacocodemo.service;

import com.jacocodemo.bean.Shop;

import java.util.List;

public interface ShopService {
    public Shop getShop(String id);

    public List<Shop> getShopList();

    public void addShop(String name, String address, String mobile);
}
