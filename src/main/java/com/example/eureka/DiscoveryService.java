package com.example.eureka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Component
@RestController
public class DiscoveryService {

    @Autowired
    private DiscoveryClient discoveryClient;

    //    @RequestMapping("/discovery")
//    public String doDiscoveryService(){
//        StringBuilder buf = new StringBuilder();
//        List<String> serviceIds = discoveryClient.getServices();
//        if(!CollectionUtils.isEmpty(serviceIds)){
//            for(String s : serviceIds){
//                System.out.println("serviceId:" + s);
//                List<ServiceInstance> serviceInstances =  discoveryClient.getInstances(s);
//                if(!CollectionUtils.isEmpty(serviceInstances)){
//                    for(ServiceInstance si:serviceInstances){
//                        buf.append("["+si.getServiceId() +" host=" +si.getHost()+" port="+si.getPort()+" uri="+si.getUri()+"]");
//                    }
//                }else{
//                    buf.append("no service.");
//                }
//            }
//        }
//
//
//        return buf.toString();
//    }
    @RequestMapping("/registered")
    public String getRegistered() {
        List<ServiceInstance> list = discoveryClient.getInstances("STORES");
        System.out.println(discoveryClient.getLocalServiceInstance());
        System.out.println("discoveryClient.getServices().size() = " + discoveryClient.getServices().size());

        for (String s : discoveryClient.getServices()) {
            System.out.println("services " + s);
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(s);
            for (ServiceInstance si : serviceInstances) {
                System.out.println("    services:" + s + ":getHost()=" + si.getHost());
                System.out.println("    services:" + s + ":getPort()=" + si.getPort());
                System.out.println("    services:" + s + ":getServiceId()=" + si.getServiceId());
                System.out.println("    services:" + s + ":getUri()=" + si.getUri());
                System.out.println("    services:" + s + ":getMetadata()=" + si.getMetadata());
            }

        }

        System.out.println(list.size());
        if (list != null && list.size() > 0) {
            System.out.println(list.get(0).getUri());
        }
        return null;
    }

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }

    @RequestMapping("/say")
    public String sayhello() {
        return "hello";
    }

}