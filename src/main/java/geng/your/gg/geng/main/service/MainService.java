package geng.your.gg.geng.main.service;

import java.util.List;
import java.util.Map;

public interface MainService {
    List<Map<String, String>> createNavList(String[] href, String[] title);
}
