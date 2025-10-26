package geng.your.gg.geng.main.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import geng.your.gg.geng.main.model.AccountDto;
import geng.your.gg.geng.main.model.LeagueEntryDto;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MainServiceImpl implements MainService{
    // 네비게이션의 경로와 이름을 만들어주는 메서드
    public List<Map<String, String>> createNavList(String[] href, String[] title) {
        // JSON 형태로 전송
        List<Map<String, String>> navList = new ArrayList<>();

        for(int i = 0; i < href.length; i++) {
            Map<String, String> element = new HashMap<>();
            element.put("href", href[i]);
            element.put("title", title[i]);

            navList.add(element);
        }
        return navList;
    }

    public AccountDto requestApiAccount(String gameName, String tagLine, String country) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("https://asia.api.riotgames.com/riot/account/v1/accounts/by-riot-id/");
        sb.append(gameName);
        sb.append("/");
        sb.append(tagLine);
        sb.append("?api_key=").append("RGAPI-979b0468-887c-46b8-bfa1-1a5894f834b8");

        URL url = new URL(sb.toString());
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));

        String inputLine;
        sb = new StringBuilder();
        while((inputLine = in.readLine()) != null) {
            sb.append(inputLine);
        }
        in.close();

        ObjectMapper objectMapper = new ObjectMapper();
        AccountDto accountDto = objectMapper.readValue(sb.toString(), AccountDto.class);

        return accountDto;
    }

    public LeagueEntryDto requestApiLeagueEntry() {
        return null;
    }
}
