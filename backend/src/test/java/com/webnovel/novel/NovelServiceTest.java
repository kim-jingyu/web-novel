package com.webnovel.novel;

import com.webnovel.genre.domain.service.GenreService;
import com.webnovel.member.domain.Member;
import com.webnovel.member.domain.Role;
import com.webnovel.member.domain.repository.MemberRepository;
import com.webnovel.novel.domain.Novel;
import com.webnovel.novel.domain.dto.CreateNovelDto;
import com.webnovel.novel.domain.dto.ModifyContentDto;
import com.webnovel.novel.domain.dto.SubscribeAndViewDto;
import com.webnovel.novel.domain.service.NovelService;
import com.webnovel.recommend.domain.RecommendStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
public class NovelServiceTest {
    @Autowired
    NovelService novelService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void CreateTest(){
        Member member = Member.builder()
                .email("user@email.com")
                .password("1234")
                .role(Role.USER)
                .money(1000)
                .imageUrl("asd")
                .nickName("tlqkf")
                .recommendStatus(RecommendStatus.ALREADY)
                .build();
        Member savedMember = memberRepository.save(member);

        Novel novel = new Novel(savedMember,"aaa","eeee");
        CreateNovelDto createNovelDto = CreateNovelDto.builder()
                .memberId(novel.getMember().getMemberId())
                .cover(novel.getCover())
                .content(novel.getContent())
                .build();

        Novel novel1= novelService.createNovel(createNovelDto);

    }

    @Test
    void ModifyNovelTest(){
        Novel byNovelId = novelService.findByNovelId(1L);
        ModifyContentDto modifyContentDto = ModifyContentDto.builder()
                .novelId(byNovelId.getNovelId())
                .memberId(byNovelId.getMember().getMemberId())
                .content(byNovelId.getContent())
                .cover(byNovelId.getCover())
                .build();


        novelService.modifyContent(modifyContentDto,"hello");
    }

    @Test
    void SubscribeTest(){
        SubscribeAndViewDto subscribeAndViewDto = SubscribeAndViewDto.builder()
                .novelId(1L)
                .build();

        novelService.plusSubscribe(subscribeAndViewDto);
        //novelService.minusSubscribe(subscribeAndViewDto);
    }

    @Test
    void PlusViewTest(){
        SubscribeAndViewDto subscribeAndViewDto = SubscribeAndViewDto.builder()
                .novelId(1L)
                .build();
        novelService.plusView(subscribeAndViewDto);
    }

    @Test
    void Sorted() {
        Sort sort = Sort.by(Sort.Order.desc("view"));
        Pageable pageable = PageRequest.of(0,4,sort);
        Page<Novel> allSorted = novelService.findAllSorted(pageable);
        System.out.println("allSorted.getContent() = " + allSorted.getContent());
        allSorted.forEach(a -> System.out.println("allSorted = " + a.getNovelId()));
    }
}
