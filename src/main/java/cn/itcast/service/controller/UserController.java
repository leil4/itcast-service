package cn.itcast.service.controller;

import cn.itcast.service.pojo.User;
import com.netflix.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;

/**
 * @author leilei
 * @date 2020-12-22 16:08
 */
@Controller
@RequestMapping("consumer/user")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient; //包含了拉取的所有服务信息

    @GetMapping
    @ResponseBody
    private User queryUserById(@PathParam("id")Long id){
        discoveryClient.getInstances();
        return this.restTemplate.getForObject("http://localhost:8082/user/"+id, User.class);
    }
}
