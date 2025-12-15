package com.example.spring_jpa.util;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IdSequenceRepo extends JpaRepository<IdSequence,String>{
}
