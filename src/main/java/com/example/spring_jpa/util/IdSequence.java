package com.example.spring_jpa.util;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "id_sequences")
public class IdSequence {

    @Id
    @Column(name = "sequence_name")
    private String sequenceName; // e.g., "CART", "ORDER"

    @Column(name = "next_val")
    private Long nextVal;

    // Standard Getters and Setters
    public IdSequence() {}

    public IdSequence(String sequenceName, Long nextVal) {
        this.sequenceName = sequenceName;
        this.nextVal = nextVal;
    }

    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    public Long getNextVal() {
        return nextVal;
    }

    public void setNextVal(Long nextVal) {
        this.nextVal = nextVal;
    }
}