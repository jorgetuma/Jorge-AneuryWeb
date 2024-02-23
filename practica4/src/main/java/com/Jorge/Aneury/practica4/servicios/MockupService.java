package com.Jorge.Aneury.practica4.servicios;

import com.Jorge.Aneury.practica4.entidades.Mockup;
import com.Jorge.Aneury.practica4.repositorios.MockupRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public void  modify(UUID id,Mockup modMock) {
        Mockup m = getMockupById(id);
        m.setName(modMock.getName());
        m.setHttpMethod(modMock.getHttpMethod());
        m.setContentType(modMock.getContentType());
        m.setHeaders(modMock.getHeaders());
        m.setProject(modMock.getProject());
        m.setExpirationDate(modMock.getExpirationDate());
        m.setResponseBody(modMock.getResponseBody());
        m.setResponseDelay(modMock.getResponseDelay());
        m.setResponseCode(modMock.getResponseCode());
        m.setJwtEnabled(modMock.isJwtEnabled());
    }
    public void delete(UUID id) {
        Mockup m = getMockupById(id);
        mockupRepository.delete(m);
    }

    public Mockup getMockupById(UUID id) {
        if (mockupRepository.findById(id).isPresent()) {
            return mockupRepository.findById(id).get();
        } else {
            return null;
        }
    }
}
