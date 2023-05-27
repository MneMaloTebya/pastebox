package com.github.mnemalotebya.pastebox.service;

import com.github.mnemalotebya.pastebox.api.AppConfig;
import com.github.mnemalotebya.pastebox.api.PublicStatus;
import com.github.mnemalotebya.pastebox.api.request.PastBoxRequest;
import com.github.mnemalotebya.pastebox.api.response.PastBoxResponse;
import com.github.mnemalotebya.pastebox.api.response.PastBoxUrlResponse;
import com.github.mnemalotebya.pastebox.model.PastBoxEntity;
import com.github.mnemalotebya.pastebox.repository.PastBoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PastBoxServiceImpl implements PastBoxService {

    private final AppConfig appConfig;
    private final PastBoxRepository repository;
    private AtomicInteger idGenerator = new AtomicInteger(0);

    @Override
    public PastBoxResponse getByHash(String hash) {
        PastBoxEntity pastBoxEntity = repository.getByHash(hash);

        return new PastBoxResponse(pastBoxEntity.getData(), pastBoxEntity.isPublic());
    }

    @Override
    public List<PastBoxResponse> getFirstPublicPasteBox() {
        List<PastBoxEntity> list = repository.getListOfPublicAndAlive(appConfig.getPublicListSize());
        return list.stream().map(pastBoxEntity ->
                new PastBoxResponse(pastBoxEntity.getData(), pastBoxEntity.isPublic())).collect(Collectors.toList());
    }

    @Override
    public PastBoxUrlResponse create(PastBoxRequest request) {
        int hash = generateId();
        PastBoxEntity pastBoxEntity = new PastBoxEntity();
        pastBoxEntity.setData(request.getData());
        pastBoxEntity.setId(hash);
        pastBoxEntity.setHash(Integer.toHexString(hash));
        pastBoxEntity.setPublic(request.getPublicStatus() == PublicStatus.PUBLIC);
        pastBoxEntity.setLifeTime(LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()));
        repository.add(pastBoxEntity);
        return new PastBoxUrlResponse(appConfig.getHost() + "/" + pastBoxEntity.getHash());
    }

    private int generateId() {
        return idGenerator.getAndIncrement();
    }
}
