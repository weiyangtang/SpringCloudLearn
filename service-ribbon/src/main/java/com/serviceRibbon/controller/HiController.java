package com.serviceRibbon.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: tangweiyang
 * @Date: 2019/11/4 15:09
 * @Description:
 */
@RestController
public class HiController {
    @Autowired
    RestTemplate restTemplate;

    /***
     *   @HystrixCommand(fallbackMethod = "hiError")
     *   熔断器，如果提供的服务不可用时，调用hiError 方法
     *
     *
     */

    @GetMapping(value = "/hi")
    @HystrixCommand(fallbackMethod = "hiError")
    public String hi(@RequestParam String name) {
        return restTemplate.getForObject("http://SERVICE-HI/hi?name=" + name, String.class);
    }

    public String hiError(String name) {
        return "hi," + name + ",sorry,error!";
    }
}