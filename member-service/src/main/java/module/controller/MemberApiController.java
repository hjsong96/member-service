package module.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import module.Member;
import module.service.MemberService;
import support.Response;
import support.ResponseCode;

@RestController
@RequestMapping("/api/member")
public class MemberApiController {

    private final MemberService memberService;

    @Autowired
    public MemberApiController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/list")
    public ResponseEntity<Response<List<Member>>> list() {

        List<Member> members = memberService.findAll();

        return ResponseEntity
            .ok(new Response<>(ResponseCode.Ok, ResponseCode.Ok.name(), members));
    }

    @PostMapping
    public ResponseEntity<Response<Void>> add(@RequestBody Member member) {

        if (memberService.findByName(member.getName()).isPresent()) {
            return ResponseEntity
                .ok(new Response<>(ResponseCode.Conflict, ResponseCode.Conflict.name()));
        }

        memberService.add(member.getName(), member.getAge());

        return ResponseEntity
            .ok(new Response<>(ResponseCode.Ok, ResponseCode.Ok.name()));
    }

    @PutMapping("{id}")
    public ResponseEntity<Response<Void>> edit(@PathVariable Long id, @RequestBody Member member) {

        if (memberService.findById(id) == null) {
            return ResponseEntity
                .ok(new Response<>(ResponseCode.NotFound, ResponseCode.NotFound.name()));
        }

        memberService.update(id, member.getName(), member.getAge());

        return ResponseEntity
            .ok(new Response<>(ResponseCode.Ok, ResponseCode.Ok.name()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Response<Void>> delete(@PathVariable Long id) {

        if (memberService.findById(id) == null) {
            return ResponseEntity
                .ok(new Response<>(ResponseCode.NotFound, ResponseCode.NotFound.name()));
        }

        memberService.delete(id);

        return ResponseEntity
            .ok(new Response<>(ResponseCode.Ok, ResponseCode.Ok.name()));
    }

    @GetMapping("{id}")
    public ResponseEntity<Response<Member>> get(@PathVariable Long id) {

        Member member = memberService.findById(id);

        if (member == null) {
            return ResponseEntity
                .ok(new Response<>(ResponseCode.NotFound, ResponseCode.NotFound.name(), member));
        }

        return ResponseEntity
            .ok(new Response<>(ResponseCode.Ok, ResponseCode.Ok.name(), member));
    }
}
