package ru.job4j.forum.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Authority;

import java.util.Optional;

/**
 * DAO interface for Authority models
 */
public interface AuthorityRepository extends CrudRepository<Authority, Integer> {
    /**
     * Finding Authority object by name of authority
     * @param authority - name of authority
     * @return Authority object
     */
    Optional<Authority> findByAuthority(String authority);
}
