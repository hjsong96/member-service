package module.service;

import module.Member;
import module.repository.MemberRepository;
import module.repository.MemoryMemberRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class MemberServiceTest {

    private final MemberRepository repository = new MemoryMemberRepository();
    private final MemberService service = new MemberService(repository);

    @AfterEach
    public void afterEach(){
        repository.clear();
    }

    @Test
    void 전체조회() {
        Long test1 = service.add("test1", 1000);
        Long test2 = service.add("test2", 2000);

        List<Long> list = service.findAll()
            .stream().map(Member::getId).collect(Collectors.toList());

        assertThat(list.size()).isEqualTo(2);
        assertThat(list).contains(test1, test2);
    }

    @Test
    void 아이디찾기() {
        Long test = service.add("test", 1000);

        Member result = service.findById(test);

        assertThat(result.getId()).isEqualTo(test);
    }

    @Test
    void 이름찾기() {
        String name = "test";

        service.add(name, 1000);
        Member result = service.findByName(name).get();

        assertThat(result.getName()).isEqualTo(name);
    }

    @Test
    void 가입시중복검사() {
        service.add("test", 1000);

        List<Member> result = service.findAll();
        assertThat(result.size()).isEqualTo(1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.add("test", 1000));
    }

    @Test
    void 수정() {
        Long test = service.add("test", 1000);

        Member member = service.findById(test);

        member.setName("change");
        member.setAge(2000);
        service.update(member.getId(), member.getName(), member.getAge());

        Member result = service.findById(member.getId());
        assertThat(result.getName()).isEqualTo(member.getName());
        assertThat(result.getAge()).isEqualTo(member.getAge());
    }
}