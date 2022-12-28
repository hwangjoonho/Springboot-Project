package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

    class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    /**
     *  junit은 테스트시 메소드 순서를 보장할 수 없다.
     *  순서와 상관없이 동작하도록 테스트를 진행한다.
     *
     *  Assertions는 만약 성공하지 않으면 테스트를 실패처리를 하기 위해서 사용
     */

    @AfterEach
    public void afterEach(){ // 해당 부분은 인터셉터 느낌으로 메소드 끝날때마다 실행된다.
        repository.clearStore(); // 메소드 끝날때마다 도메인 데이터 지워주는것

    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        System.out.println(member.toString());

        Member result = repository.findById(member.getId()).get();

        assertThat(member).isEqualTo(result);
//        Assertions.assertEquals(member, "ahffk");
    }


    @Test
    public void findByName(){

        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

    }
    
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
