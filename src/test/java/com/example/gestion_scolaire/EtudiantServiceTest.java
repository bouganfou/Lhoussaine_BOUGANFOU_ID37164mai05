package com.example.gestion_scolaire;

import com.example.gestion_scolaire.dao.EtudiantRepository;
import com.example.gestion_scolaire.dto.EtudiantDto;
import com.example.gestion_scolaire.entities.Etudiant;
import com.example.gestion_scolaire.service.EtudiantService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EtudiantServiceTest {
    @Autowired
    private EtudiantService etudiantService;
    @Mock
    private EtudiantRepository etudiantRepository;

    @DisplayName("Test list method to get all students")
    @Test
    public void list() {
        // arrange
        Etudiant etudiant = Etudiant.builder()
                .id(1L)
                .nom("bouganfou")
                .prenom("rachid")
                .build();
        List<Etudiant> etudiants = new ArrayList<>(Collections.singletonList(etudiant));

        when(etudiantRepository.findAll()).thenReturn(etudiants);
        Pageable pageable = PageRequest.of(0, 1);
        // act
        List<EtudiantDto> etudResult = etudiantService.findAll(pageable).getContent();
        //asserts
        assertEquals(etudResult.size(), 1);
        assertEquals(etudiants.get(0).getId(), etudResult.get(0).getId());
        assertEquals(etudiants.get(0).getNom(), etudResult.get(0).getNom());
        assertEquals(etudiants.get(0).getPrenom(), etudResult.get(0).getPrenom());
    }

    }
