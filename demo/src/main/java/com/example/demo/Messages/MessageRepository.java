package com.example.demo.Messages;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MessageRepository extends MongoRepository<Message , String> {

}