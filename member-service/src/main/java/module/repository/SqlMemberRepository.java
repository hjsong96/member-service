package module.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import module.Member;

@Primary
@Repository
public class SqlMemberRepository implements MemberRepository {

    private final SqlSession sqlSession;

    @Autowired
    public SqlMemberRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<Member> findAll() {
        return sqlSession.selectList("member.findAll");
    }

    @Override
    public Member findById(Long id) {
        return sqlSession.selectOne("member.findById", id);
    }

    @Override
    public Optional<Member> findByName(String name) {
        if (!StringUtils.hasText(name)) {
            return null;
        }

        return Optional.ofNullable(sqlSession.selectOne("member.findByName", name));
    }

    @Override
    public Long add(String name, Integer age) {
        Member member = new Member(name, age);
        sqlSession.insert("add", member);
        return member.getId();
    }

    @Override
    public void update(Long id, String name, Integer age) {
        sqlSession.update("member.update", new Member(id, name, age));
    }

    @Override
    public void delete(Long id) {
        sqlSession.delete("member.delete", id);
    }

    @Override
    public void clear() {
        sqlSession.delete("member.clear");
    }
}
