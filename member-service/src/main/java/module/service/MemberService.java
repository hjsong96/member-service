package module.service;

import module.Member;
import lombok.RequiredArgsConstructor;
import module.repository.MemberRepository;

import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findById(Long id) {
        return memberRepository.findById(id);
    }

    public Optional<Member> findByName(String name) {
        return memberRepository.findByName(name);
    }

    public Long add(String name, Integer age) {
        checkDuplicateName(name);
        return memberRepository.add(name, age);
    }

    private void checkDuplicateName(String name) {
        findByName(name).ifPresent(i -> {
            throw new IllegalArgumentException("동일한 이름으로 저장된 회원이 이미 존재합니다.");
        });
    }

    public void update(Long id, String name, Integer age) {
        memberRepository.update(id, name, age);
    }

    public void delete(Long id) {
        memberRepository.delete(id);
    }

}
