package com.github.mnemalotebya.pastebox.comtroller;

import com.github.mnemalotebya.pastebox.api.request.PastBoxRequest;
import com.github.mnemalotebya.pastebox.api.response.PastBoxResponse;
import com.github.mnemalotebya.pastebox.api.response.PastBoxUrlResponse;
import com.github.mnemalotebya.pastebox.service.PastBoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequiredArgsConstructor
public class PastBoxController {

    private final PastBoxService pastBoxService;

    @GetMapping("/{hash}")
    public PastBoxResponse getByHash(@PathVariable String hash) {
        return pastBoxService.getByHash(hash);
    }

    @GetMapping("/")
    public Collection<PastBoxResponse> getPublicPasteList() {
        return pastBoxService.getFirstPublicPasteBox();
    }

    @PostMapping("/")
    public PastBoxUrlResponse add(@RequestBody PastBoxRequest request) {
        return pastBoxService.create(request);
    }
}
