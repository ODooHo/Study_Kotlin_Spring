package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()
    //이러면 싱글톤이 깨지는게 아닌가? -> 스프링 컨테이너가 해결
    //AppConfig.memberService
    //AppConfig.memberRepository
    //AppConfig.memberRepository
    //AppConfig.orderService
    //AppConfig.memberRepository -> 의도

    //AppConfig.memberService
    //AppConfig.memberRepository
    //AppConfig.orderService -> 실제 (스프링이 싱글톤을 보장하는 이유)
    //@Configuration을 붙이면, 바이트코드를 조작하는 cglib기술을 활용하여 싱글톤을 보장하게된다.(조작 방식은 밑에)
    //@Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고, 스프링 빈이 없으면 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어진다.

    @Bean
    public MemberService memberService(){
        System.out.println("AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){
        System.out.println("AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(),discountPolicy()
        );
    }
    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
