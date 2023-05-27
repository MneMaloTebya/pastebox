package com.github.mnemalotebya.pastebox.repository;

import com.github.mnemalotebya.pastebox.model.PastBoxEntity;

import java.util.List;

public interface PastBoxRepository {
    PastBoxEntity getByHash(String hash);
    List<PastBoxEntity> getListOfPublicAndAlive(int amount);
    void add(PastBoxEntity pastBoxEntity);
}
