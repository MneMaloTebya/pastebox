package com.github.mnemalotebya.pastebox.repository;

import com.github.mnemalotebya.pastebox.exeption.NotFoundEntityException;
import com.github.mnemalotebya.pastebox.model.PastBoxEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PastBoxRepositoryMap implements PastBoxRepository{

    private final Map<String, PastBoxEntity> vault = new HashMap<>();

    @Override
    public PastBoxEntity getByHash(String hash) {
        PastBoxEntity pastBoxEntity = vault.get(hash);
        if (pastBoxEntity == null) {
            throw new NotFoundEntityException("PasteBox not found with hash = " + hash);
        }
        return pastBoxEntity;
    }

    @Override
    public List<PastBoxEntity> getListOfPublicAndAlive(int amount) {
        LocalDateTime now = LocalDateTime.now();
        return vault.values().stream()
                .filter(PastBoxEntity::isPublic)
                .filter(pastBoxEntity -> pastBoxEntity.getLifeTime().isAfter(now))
                .sorted(Comparator.comparing(PastBoxEntity::getId).reversed())
                .limit(amount)
                .collect(Collectors.toList());
    }

    @Override
    public void add(PastBoxEntity pastBoxEntity) {
       vault.put(pastBoxEntity.getHash(), pastBoxEntity);
    }
}
