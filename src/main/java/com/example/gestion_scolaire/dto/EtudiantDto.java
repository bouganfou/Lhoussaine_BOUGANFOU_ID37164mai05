package com.example.gestion_scolaire.dto;


import com.example.gestion_scolaire.entities.Etudiant;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class EtudiantDto {

    private Long id;
    private String nom;
    private String prenom;
    private ClasseDto classe;
    public static EtudiantDto fromEntity(Etudiant etudiant) {
        if (etudiant == null) return null;
        return EtudiantDto.builder()
                .id(etudiant.getId())
                .nom(etudiant.getNom())
                .prenom(etudiant.getPrenom())
                .classe(ClasseDto.fromEntity(etudiant.getClasse()))
                .build();
    }

    public static Etudiant toEntity(EtudiantDto dto) {
        if (dto == null) return null;
        Etudiant etudiant=new Etudiant();
        etudiant.setId(dto.getId());
        etudiant.setPrenom(dto.getPrenom());
        etudiant.setNom(dto.getNom());
        etudiant.setClasse(ClasseDto.toEntity(dto.getClasse()));
        return etudiant;
    }

    public static Page<EtudiantDto> fromPageEtudiantToPageEtudiantDto(Page<Etudiant> etudiants,Pageable pageable){
        List<EtudiantDto> etudiantDtos = etudiants.stream()
                .map(EtudiantDto::fromEntity)
                .collect(Collectors.toList());
        long totalEtudiants = etudiantDtos.stream().count();
        Pageable pageableEtudiants = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        return new PageImpl<>(etudiantDtos, pageableEtudiants, totalEtudiants);
    }

}
