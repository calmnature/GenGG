package geng.your.gg.geng.main.service;

import org.springframework.stereotype.Service;

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
}
