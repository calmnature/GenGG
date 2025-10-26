package geng.your.gg.geng.main.model;

import lombok.Data;

@Data
// 플레이어 정보 DTO (티어, 랭크 포인트, 승리 수, 패배 수)
public class LeagueEntryDto {
    private String leagueId;
    private String puuid;
    private String queueType;
    private String tier;
    private String rank;
    private Integer leaguePoints;
    private Integer wins;
    private Integer losses;
    private Boolean hotStreak;
    private Boolean veteran;
    private Boolean freshBlood;
    private Boolean inactive;
    private Boolean miniSeries;
}
