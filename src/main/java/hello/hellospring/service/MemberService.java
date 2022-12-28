package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;




    /**
     * 회원가입
     * @param
     * @return
     */
    public long join(Member member) {

        //중복회원이름 제거
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        // java8 이후 부터 optional로 감싸서 null이 return되므로 ifPresent + 람다식 유형 사용가능 orElseGet....
//        // java8 이후 메소드 및 람다식 공부 필요
//        result.ifPresent(m ->  {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//
//        });
        // ctrl + alt + m : extract method
        validateDuplicateMember(member); // 중복회원검증
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m ->  {
                        throw new IllegalStateException("이미 존재하는 회원입니다.");

                });
    }

    /**
     * 전체회원조회
     * @return
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
