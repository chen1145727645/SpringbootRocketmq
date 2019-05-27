package com.suoron.springboot.service;

public interface ProducerService {
    public String send(String topic, String tags, String msg) ;
}

