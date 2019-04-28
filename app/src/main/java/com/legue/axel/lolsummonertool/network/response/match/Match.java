package com.legue.axel.lolsummonertool.network.response.match;


import android.os.Parcel;
import android.os.Parcelable;

public class Match implements Parcelable {

    private int gameId;
    private String lane;
    private int champion;
    private String platformId;
    private long timestamp;
    private int queue;
    private String role;
    private int season;

    public Match() {
    }

    public Match(String lane, int gameId, int champion, String platformId, long timestamp, int queue, String role, int season) {
        this.lane = lane;
        this.gameId = gameId;
        this.champion = champion;
        this.platformId = platformId;
        this.timestamp = timestamp;
        this.queue = queue;
        this.role = role;
        this.season = season;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.gameId);
        dest.writeString(this.lane);
        dest.writeInt(this.champion);
        dest.writeString(this.platformId);
        dest.writeLong(this.timestamp);
        dest.writeInt(this.queue);
        dest.writeString(this.role);
        dest.writeInt(this.season);
    }

    protected Match(Parcel in) {
        this.gameId = in.readInt();
        this.lane = in.readString();
        this.champion = in.readInt();
        this.platformId = in.readString();
        this.timestamp = in.readLong();
        this.queue = in.readInt();
        this.role = in.readString();
        this.season = in.readInt();
    }

    public static final Parcelable.Creator<Match> CREATOR = new Parcelable.Creator<Match>() {
        @Override
        public Match createFromParcel(Parcel source) {
            return new Match(source);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };


    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public int getChampion() {
        return champion;
    }

    public void setChampion(int champion) {
        this.champion = champion;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getQueue() {
        return queue;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

}
