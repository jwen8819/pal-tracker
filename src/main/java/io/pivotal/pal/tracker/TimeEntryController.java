package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {
    private final TimeEntryRepository timeEntryRepository;
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping()
    public ResponseEntity<TimeEntry> create(@RequestBody  TimeEntry timeEntryToCreate) {
        TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);
        return created(null).body(timeEntry);
    }

    @GetMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        var timeEntryFound = timeEntryRepository.find(timeEntryId);
        return timeEntryFound == null ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(timeEntryFound);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {

        return ResponseEntity.ok(timeEntryRepository.list());
    }

    @PutMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId, @RequestBody TimeEntry timeEntryToUpdate) {
        var timeEntryUpdate = timeEntryRepository.update(timeEntryId,timeEntryToUpdate);
        return timeEntryUpdate == null ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(timeEntryUpdate);
    }

    @DeleteMapping("{timeEntryId}")
    public ResponseEntity<Void> delete(@PathVariable long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return ResponseEntity.noContent().build();
    }
}
