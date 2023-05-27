package com.github.mnemalotebya.pastebox.service;

import com.github.mnemalotebya.pastebox.api.request.PastBoxRequest;
import com.github.mnemalotebya.pastebox.api.response.PastBoxResponse;
import com.github.mnemalotebya.pastebox.api.response.PastBoxUrlResponse;

import java.util.List;

public interface PastBoxService {
    PastBoxResponse getByHash(String hash);
    List<PastBoxResponse> getFirstPublicPasteBox();
    PastBoxUrlResponse create(PastBoxRequest request);


}
