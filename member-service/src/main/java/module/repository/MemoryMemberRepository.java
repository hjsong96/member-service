package module.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import module.Member;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private final static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Member findById(Long id) {
        return store.get(id);
    }

    @Override
    public Optional<Member> findByName(String name) {
        if (!StringUtils.hasText(name)) {
            return null;
        }

        return store.values().stream().filter(f -> name.equals(f.getName())).findAny();
    }

    @Override
    public Long add(String name, Integer age) {
        Member item = new Member(name, age);
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item.getId();
    }

    @Override
    public void update(Long id, String name, Integer age) {
        store.put(id, new Member(id, name, age));
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }

    @Override
    public void clear() {
        store.clear();
    }
}
