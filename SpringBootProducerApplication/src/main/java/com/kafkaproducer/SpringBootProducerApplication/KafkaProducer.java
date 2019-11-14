package com.kafkaproducer.SpringBootProducerApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer{
    @Autowired
    private KafkaTemplate<String,String>  KafkaTemplate;

    String KafkaTopic = "MyFirstTopic";


    public void send(String message){
        KafkaTemplate.send(KafkaTopic,message);

    }
}