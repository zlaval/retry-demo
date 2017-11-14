package com.zlrx.retry.demo.repository;

import com.zlrx.retry.demo.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
