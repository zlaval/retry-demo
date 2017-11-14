package com.zlrx.retry.demo.service;

import com.zlrx.retry.demo.domain.Person;
import com.zlrx.retry.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicInteger;

@Transactional
@Service
public class RetryServiceImpl implements RetryService {

    @Autowired
    private PersonRepository repository;

    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void save() {

        Person person = new Person();
        person.setName("name_1_round" + counter.get());
        repository.save(person);

        if (counter.getAndIncrement() < 5) {
            System.out.println("Throw an error");
            throw new RuntimeException("OOps");
        }

        Person anotherPerson = new Person();
        anotherPerson.setName("name_2_round" + (counter.get() - 1));
        repository.save(anotherPerson);

    }

    @Override
    public void recover(RuntimeException e) {
        Person person = new Person();
        person.setName("recover person");
        repository.save(person);
    }

    @Override
    public void read() {
        System.out.println("if(counter<2) : Repo should contains two normal entity");
        System.out.println("if(counter<5) : Repo should contains one recover entity");
        repository.findAll().forEach(System.out::println);
    }


}
