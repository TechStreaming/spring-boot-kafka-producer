package com.techstreams.springbootkafkaproducer.action;


import com.techstreams.springbootkafkaproducer.model.BaseResponse;
import com.techstreams.springbootkafkaproducer.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserAction {

    private static final String TOPIC_NAME = "TECHSTREAMS";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping("/publish/{message}")
    public BaseResponse publishMessage(@PathVariable("message") final String message) {
        try {
            kafkaTemplate.send(TOPIC_NAME, message);
            return new BaseResponse("OK", " Message Successfully Published to kafka ");
        } catch (Exception e) {
            return new BaseResponse("ERROR", " Error while publishing message to kafka ");
        }
    }


    @PostMapping("/publish/uderdata")
    public BaseResponse publishJson(@RequestBody UserDetails userDetails) {
        try {
            kafkaTemplate.send(TOPIC_NAME, userDetails);
            return new BaseResponse("OK", " UserDetails Successfully Published to kafka ");
        } catch (Exception e) {
            return new BaseResponse("ERROR", " Error while publishing UserDetails to kafka ");
        }
    }
}
