package com.codezilla.ipl;

public class leagueList {

    String str;
    int league_id;

    public leagueList(String str, int league_id) {
        this.str = str;
        this.league_id = league_id;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getLeague_id() {
        return league_id;
    }

    public void setLeague_id(int league_id) {
        this.league_id = league_id;
    }
}
