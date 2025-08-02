package restjagdishapi.journal.app.controller;

import org.springframework.web.bind.annotation.*;
import restjagdishapi.journal.app.entity.JournalEntry;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;



@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private Map<Long, JournalEntry> journalEntries = new HashMap();

    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        journalEntries.put(myEntry.getId(), myEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable long myId){
        return journalEntries.get(myId);

    }

    @DeleteMapping("id/{myId}")
    public JournalEntry deleteEntryById(@PathVariable long myId){
        return journalEntries.remove(myId);

    }

    @PutMapping("id/{id}")
    public JournalEntry updateJournalById(@PathVariable long id, @RequestBody JournalEntry myEntry){
        return journalEntries.put(id, myEntry);

    }

}
