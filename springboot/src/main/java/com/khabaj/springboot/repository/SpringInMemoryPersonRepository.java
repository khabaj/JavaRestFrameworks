package com.khabaj.springboot.repository;

import com.khabaj.common.repository.InMemoryPersonRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class SpringInMemoryPersonRepository extends InMemoryPersonRepository {
}
