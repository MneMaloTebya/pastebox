package com.github.mnemalotebya.pastebox;

import com.github.mnemalotebya.pastebox.api.response.PastBoxResponse;
import com.github.mnemalotebya.pastebox.exeption.NotFoundEntityException;
import com.github.mnemalotebya.pastebox.model.PastBoxEntity;
import com.github.mnemalotebya.pastebox.repository.PastBoxRepository;
import com.github.mnemalotebya.pastebox.service.PastBoxService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PastBoxServiceTest {

    @Autowired
    private PastBoxService pastBoxService;

    @MockBean
    private PastBoxRepository pastBoxRepository;

    @Test
    public void notExistHash() {
        when(pastBoxRepository.getByHash(anyString())).thenThrow(NotFoundEntityException.class);
        assertThrows(NotFoundEntityException.class, () -> pastBoxService.getByHash("Test"));
    }

    @Test
    public void getExistHash() {
        PastBoxEntity entity = new PastBoxEntity();
        entity.setHash("1");
        entity.setData("New Paste");
        entity.setPublic(true);
        when(pastBoxRepository.getByHash("1")).thenReturn(entity);
        PastBoxResponse expected = new PastBoxResponse("New Paste", true);
        PastBoxResponse actual = pastBoxService.getByHash("1");
        assertEquals(expected, actual);
    }


}
