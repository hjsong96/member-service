package module.repository;

import java.util.List;
import java.util.Optional;

import module.Member;

public interface MemberRepository {

    List<Member> findAll();

    Member findById(Long id);

    Optional<Member> findByName(String name);

    Long add(String name, Integer age);

    void update(Long id, String name, Integer age);

    void delete(Long id);

    void clear();
}