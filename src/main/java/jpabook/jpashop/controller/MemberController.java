    package jpabook.jpashop.controller;


    import jakarta.validation.Valid;
    import jpabook.jpashop.domain.Address;
    import jpabook.jpashop.domain.Member;
    import jpabook.jpashop.service.MemberService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PostMapping;

    import java.util.List;

    @Controller
    @RequiredArgsConstructor
    public class MemberController {

        private final MemberService memberService;

        // 컨트롤러에서 뷰로 넘어갈때 데이터를 실어서 넘김
        @GetMapping("/members/new")
        public String createForm(Model model) {
            model.addAttribute("memberForm", new MemberForm());
            return "members/createMemberForm";
        }

        @GetMapping("/members")
        public String list(Model model) {
            List<Member> members = memberService.findMembers();
            model.addAttribute("members", members);
            return "members/memberList";

        }

        @PostMapping("/members/new")
        public String create(@Valid MemberForm form, BindingResult result) {

            if (result.hasErrors()) {
                return "members/createMemberForm";
            }

            Address address = new Address(form.getStreet(), form.getStreet(), form.getZipcode());

            Member member = new Member();
            member.setName(form.getName());
            member.setAddress(address);

            memberService.join(member);
            return "redirect:/";
        }
    }
