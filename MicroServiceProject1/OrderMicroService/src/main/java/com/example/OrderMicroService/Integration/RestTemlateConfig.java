package com.example.OrderMicroService.Integration;


import com.example.OrderMicroService.service.CustomInterceptor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class RestTemlateConfig {


//    private final CustomInterceptor customInterceptor;
//
//    public RestTemlateConfig(CustomInterceptor customInterceptor) {
//        this.customInterceptor = customInterceptor;
//
//    }


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate()
    {
        SimpleClientHttpRequestFactory  simpleClientHttpRequestFactory=new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(2000);
        simpleClientHttpRequestFactory.setReadTimeout(2000);

        RestTemplate restTemplate=new RestTemplate(simpleClientHttpRequestFactory);
//        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
//        interceptors.add(customInterceptor);
//        restTemplate.setInterceptors((interceptors);
        return restTemplate;
    }
}
