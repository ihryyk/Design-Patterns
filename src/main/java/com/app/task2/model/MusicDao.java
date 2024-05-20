package com.app.task2.model;

import com.app.util.FileUtil;
import com.app.util.JsonObjectMapper;
import com.app.task2.model.entity.Music;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class MusicDao {

    private static final String FILE_PATH = "./src/main/resources/musics.txt";

    private final List<Music> musicList;

    public MusicDao() {
        try {
            String payload = FileUtil.readFromFile(Paths.get(FILE_PATH));
            Music[] musics = JsonObjectMapper.fromJson(payload, Music[].class);
            musicList = Arrays.stream(musics).toList();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file", e);
        }
    }

    public Music getById(int id) {
        return musicList.get(id);
    }

    public int getNumberOfMusic() {
        return musicList.size();
    }

}
