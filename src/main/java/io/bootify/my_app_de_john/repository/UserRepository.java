package io.bootify.myappdejohn.repository;

import io.bootify.myappdejohn.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
