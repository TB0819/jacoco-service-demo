package com.jacocodemo.serviceimp;

import com.jacocodemo.bean.Shop;
import com.jacocodemo.service.ShopService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ShopServiceImp implements ShopService {
    private static List<Shop> shops = new ArrayList<>();

    @Override
    public Shop getShop(String id) {
        for (Shop shop: shops) {
            if (shop.getId().equals(id)) {
                return shop;
            }
        }
        return null;
    }

    @Override
    public List<Shop> getShopList() {
        return shops;
    }

    @Override
    public void addShop(String name, String address, String mobile) {
        Shop shop = new Shop();
        shop.setAddress(address);
        shop.setMobile(mobile);
        shop.setName(name);
        shop.setId(String.valueOf(UUID.randomUUID()));
    }
}
