package com.kafkaproducer.SpringBootProducerApplication;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka/")
public class KafkaController {
    @Autowired
    KafkaProducer kafkaProducer;
    @GetMapping(value = "/publishmessage")
    public String producer(@RequestParam("message") String message){
        kafkaProducer.send(message);
        return "Messages send to kafka Topic Kafka_Micro successfully";
    }

}