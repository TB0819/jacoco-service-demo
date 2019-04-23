package com.jacocodemo.controller;

import com.jacocodemo.bean.Shop;
import com.jacocodemo.service.ShopService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {
    @Resource
    ShopService shopService;

    @ResponseBody
    @RequestMapping(value = "/get_shops",  method = {RequestMethod.POST,RequestMethod.GET})
    public List<Shop> getShops(){
        return shopService.getShopList();
    }

    @ResponseBody
    @RequestMapping(value = "/get_shop",  method = {RequestMethod.POST,RequestMethod.GET})
    public Shop getShop(String id){
        return shopService.getShop(id);
    }

    @ResponseBody
    @RequestMapping(value = "/add_shop",  method = {RequestMethod.POST,RequestMethod.GET})
    public void addShop(String name, String address, String mobile){
        shopService.addShop(name, address, mobile);
    }
}
