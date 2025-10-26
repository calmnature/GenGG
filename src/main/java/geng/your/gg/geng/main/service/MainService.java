package geng.your.gg.geng.main.service;

import geng.your.gg.geng.main.model.AccountDto;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface MainService {
    List<Map<String, String>> createNavList(String[] href, String[] title);
    AccountDto requestApiAccount(String gameName, String tagLine, String country) throws IOException;
}
