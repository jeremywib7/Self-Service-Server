package com.j23.server.controllers;

import com.j23.server.models.Member;
import com.j23.server.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/member")
public class MemberResourceController {

    @Autowired
    private final MemberService memberService;

    public MemberResourceController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/all/_page={pageNo}&_limit={pageSize}")
    public List<Member> getAllMembers(@PathVariable int pageNo, @PathVariable int pageSize) {
//        List<Member> members = memberService.findAllMembers();
        return memberService.findAllMembers(pageNo, pageSize);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Member> getMembersById(@PathVariable("id") Long id) {
        Member member = memberService.findMemberById(id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        Member newMember = memberService.addMember(member);
        return new ResponseEntity<>(newMember, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Member> updateMember(@RequestBody Member member) {
        Member updateMember = memberService.updateMember(member);
        return new ResponseEntity<>(updateMember, HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable("id") Long id) {
        memberService.deleteMember(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
