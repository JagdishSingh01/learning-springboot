package restjagdishapi.journal.app.repository;

//controller --> service -->repository

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import restjagdishapi.journal.app.entity.JournalEntry;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId>{

}
