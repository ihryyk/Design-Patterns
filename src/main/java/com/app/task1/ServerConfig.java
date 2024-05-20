package com.app.task1;

import com.app.util.FileUtil;
import com.app.util.JsonObjectMapper;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ServerConfig {

    private final List<User> userAccessLevelMap;

    private static final String FILE_PATH = "./src/main/resources/users.txt";

    private static class LazyHolder {

        static final ServerConfig INSTANCE = new ServerConfig();

    }

    public static ServerConfig getInstance() {
        return LazyHolder.INSTANCE;
    }

    private ServerConfig() {
        try {
            String payload = FileUtil.readFromFile(Paths.get(FILE_PATH));
            User[] users = JsonObjectMapper.fromJson(payload, User[].class);
            userAccessLevelMap = Arrays.stream(users).toList();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file", e);
        }
    }

    public String getAccessLevel(User u) {
        return u.role().getAccessLevel();
    }

    public User getUser(String name) {
        return userAccessLevelMap.stream()
                .filter(user -> name.equals(user.name()))
                .findAny()
                .orElse(null);
    }

}
