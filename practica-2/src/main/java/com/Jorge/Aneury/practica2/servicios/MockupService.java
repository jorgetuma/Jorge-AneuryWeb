package com.Jorge.Aneury.practica2.servicios;

import com.Jorge.Aneury.practica2.repositorios.MockupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MockupService {

    private final MockupRepository mockupRepository;

    @Autowired
    public MockupService(MockupRepository mockupRepository) {
        this.mockupRepository = mockupRepository;
    }
}
