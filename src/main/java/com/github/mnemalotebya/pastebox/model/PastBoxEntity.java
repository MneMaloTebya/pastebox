package com.github.mnemalotebya.pastebox.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PastBoxEntity {
    private int id;
    private String data;
    private String hash;
    private LocalDateTime lifeTime;
    private boolean isPublic;
}
