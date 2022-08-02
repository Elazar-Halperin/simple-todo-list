package com.example.todoapp;

public class MissionModel {
    int id;
    boolean isCompleted;
    String missionDescription;

    public MissionModel(int id, boolean isCompleted, String missionDescription) {
        this.id = id;
        this.isCompleted = isCompleted;
        this.missionDescription = missionDescription;
    }

    public int getId() {
        return id;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getMissionDescription() {
        return missionDescription;
    }

    public void setMissionDescription(String missionDescription) {
        this.missionDescription = missionDescription;
    }
}
