package com.app.task1;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Session {

    private User user;
    private String level;

}
