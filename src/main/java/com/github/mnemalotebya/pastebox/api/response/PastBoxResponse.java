package com.github.mnemalotebya.pastebox.api.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PastBoxResponse {
    private final String data;
    private final boolean isPublic;
}
