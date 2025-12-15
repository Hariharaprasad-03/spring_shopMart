package com.example.spring_jpa.util;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class IdGeneratorService {

    @Autowired
    private IdSequenceRepo repo;


    public String generateId(String prefix) {
        // 1. Fetch the sequence record, or create if missing
        IdSequence sequence = repo.findById(prefix)
                .orElse(new IdSequence(prefix, 1L));

        // 2. Get the current value
        Long val = sequence.getNextVal();

        // 3. Increment for next time
        sequence.setNextVal(val + 1);
        repo.save(sequence);

        // 4. Format: PREFIX + 00001
        return prefix + String.format("%05d", val);
    }
}