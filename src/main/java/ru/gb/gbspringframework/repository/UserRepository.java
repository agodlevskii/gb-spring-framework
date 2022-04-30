package ru.gb.gbspringframework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.gbspringframework.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    List<User> findAll();

    @Override
    <S extends User> S save(S entity);

    User findByName(String name);
}
