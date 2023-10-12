package module.controller;

import module.Member;
import module.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("members", memberService.findAll());
        return "/members";
    }

    @GetMapping("/add")
    public String addForm() {
        return "/addForm";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("member", memberService.findById(id));
        return "/editForm";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable long id, Model model) {
        model.addAttribute("member", memberService.findById(id));
        return "/member";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Member member, RedirectAttributes attributes) {
        Long id = memberService.add(member.getName(), member.getAge());
        attributes.addAttribute("memberId", id);
        attributes.addAttribute("status", true);
        return "redirect:/member/{memberId}";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute Member member, RedirectAttributes attributes) {
        memberService.update(id, member.getName(), member.getAge());
        attributes.addAttribute("memberId", id);
        attributes.addAttribute("status", true);
        return "redirect:/member/{memberId}";
    }
}
