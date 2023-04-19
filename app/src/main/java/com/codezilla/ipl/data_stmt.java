package com.codezilla.ipl;

public class data_stmt {
    String stmt;
    int stmt_id;
    int league_id;
    int points;
    int coins;

    public void setStmt(String stmt) {
        this.stmt = stmt;
    }

    public void setStmt_id(int stmt_id) {
        this.stmt_id = stmt_id;
    }

    public void setLeague_id(int league_id) {
        this.league_id = league_id;
    }

    public data_stmt(String stmt, int stmt_id, int league_id) {
        this.stmt = stmt;
        this.stmt_id = stmt_id;
        this.league_id = league_id;
    }
    public data_stmt(String stmt, int stmt_id, int league_id,int points) {
        this.stmt = stmt;
        this.points=points;
        this.stmt_id = stmt_id;
        this.league_id = league_id;
    }
    public data_stmt(String stmt, int stmt_id, int league_id,int points,int coins) {
        this.stmt = stmt;
        this.points=points;
        this.coins=coins;
        this.stmt_id = stmt_id;
        this.league_id = league_id;
    }

    public String getStmt() {
        return stmt;
    }

    public int getStmt_id() {
        return stmt_id;
    }

    public int getLeague_id() {
        return league_id;
    }
}
