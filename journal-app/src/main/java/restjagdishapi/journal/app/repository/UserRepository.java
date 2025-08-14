package restjagdishapi.journal.app.repository;

//controller --> service -->repository

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import restjagdishapi.journal.app.entity.User;

public interface UserRepository extends MongoRepository<User, ObjectId>{
    User findByUserName(String userName);

    void deleteByUserName(String userName);
}
