package com.fisc.declpanneau.web.rest;

import com.fisc.declpanneau.service.DeclPanneauKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/decl-panneau-kafka")
public class DeclPanneauKafkaResource {

    private final Logger log = LoggerFactory.getLogger(DeclPanneauKafkaResource.class);

    private DeclPanneauKafkaProducer kafkaProducer;

    public DeclPanneauKafkaResource(DeclPanneauKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        log.debug("REST request to send to Kafka topic the message : {}", message);
        this.kafkaProducer.send(message);
    }
}
