package com.websockets.springboot_websockets_demo.utils;

public class GlobalData {
    private static GlobalData instance = new GlobalData();
    private int usersCount = 0;

    private GlobalData() {
    }

    public static GlobalData getInstance() {
        return instance;
    }

    public static void setInstance(GlobalData instance) {
        GlobalData.instance = instance;
    }

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }
}
