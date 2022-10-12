package com.example.exampmd4.service.nation;

import com.example.exampmd4.model.Nation;
import com.example.exampmd4.repository.NationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NationServiceImpl implements NationService {
    @Autowired
    private NationRepository nationRepository;
    @Override
    public Iterable<Nation> findAll() {
        return nationRepository.findAll();
    }

    @Override
    public Optional<Nation> findById(Long id) {
        return nationRepository.findById(id);
    }

    @Override
    public Nation save(Nation nation) {
        return nationRepository.save(nation);
    }

    @Override
    public void remove(Long id) {
        nationRepository.deleteById(id);
    }

    @Override
    public boolean existsByUserName(String name) {
        return existsByUserName(name);
    }
}
