package geng.your.gg.geng.main.controller;

import geng.your.gg.geng.main.model.AccountDto;
import geng.your.gg.geng.main.service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {
    private final MainService mainService;
    // 네비게이션 메뉴바 추가될 경우 아래 hrefList, titleList 추가
    private final String[] hrefList = {"#", "#", "#", "#"};
    private final String[] titleList = {"커뮤니티", "AI 코칭 <span class=\"new-badge\">NEW</span>", "듀오찾기", "멀티서치 AI"};
    // 검색 조건에 검색 나라 셀렉트 옵션
    private final String[] countryList = {"KR", "ME", "NA", "BR", "EUNE", "EUW", "LAN",
            "LAS", "OCE", "RU", "TR", "JP", "SG", "TW", "VN"};

    private static AccountDto accountDto;

    @GetMapping("/")
    public String redirect(Model model) {
        return "redirect:/ko/kr/home";
    }

    @GetMapping("/ko/{country}/home")
    public String home(Model model, @PathVariable String country) {
        String selectCountry = country.toUpperCase();

        boolean countryValidation = Arrays.asList(countryList).contains(selectCountry);

        log.info("Validation Check = {}", countryValidation);
        if(!countryValidation) {
            return "redirect:/ko/kr/home";
        }

        model.addAttribute("navList", mainService.createNavList(hrefList, titleList));
        model.addAttribute("countryList", countryList);
        model.addAttribute("selectedCountry", selectCountry);
        return "main/index";
    }

    @GetMapping("/ko/{country}/profile/{id}")
    public String searchDetail(Model model, @PathVariable String country, @PathVariable String id) {
        log.info("Country : {} / UserId : {}", country, id);
        try{
            // id 입력받은 '#' 기준으로 나눔
            String[] arr = id.split("#");
            String gameName = arr[0];
            String tagLine = arr[1];

            accountDto = mainService.requestApiAccount(gameName, tagLine, country);

            log.info("Account 정보 = {}", accountDto);

            // accountDto의 puuid를 이용하여 LeagueEntryDto를 가져와서 model에 담아야 함
            // https://kr.api.riotgames.com/lol/league/v4/entries/by-puuid/{puuid}?api_key={apiKey}
        }catch(Exception e) {
            log.info("Account Request Error : {}", e.getMessage());
        }

        return "main/detail";
    }
}
