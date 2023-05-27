package com.github.mnemalotebya.pastebox.api.request;

import com.github.mnemalotebya.pastebox.api.PublicStatus;
import lombok.Data;

@Data
public class PastBoxRequest {
    private String data;
    private long expirationTimeSeconds;
    private PublicStatus publicStatus;
}
