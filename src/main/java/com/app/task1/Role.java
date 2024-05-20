package com.app.task1;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    JUNIOR("A"),
    MIDDLE("B"),
    SENIOR("C");

    private final String accessLevel;

}
