package com.Jorge.Aneury.practica2.servicios;

import com.Jorge.Aneury.practica2.entidades.Mockup;
import com.Jorge.Aneury.practica2.repositorios.MockupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MockupService {

    private final MockupRepository mockupRepository;

    @Autowired
    public MockupService(MockupRepository mockupRepository) {
        this.mockupRepository = mockupRepository;
    }

    public List<Mockup> getAllMockups() {
        return mockupRepository.findAll();
    }

    public void save(Mockup mockup) {
        mockupRepository.save(mockup);
    }

    public Mockup getMockupById(UUID id) {
        if (mockupRepository.findById(id).isPresent()) {
            return mockupRepository.findById(id).get();
        } else {
            return null;
        }
    }
}
