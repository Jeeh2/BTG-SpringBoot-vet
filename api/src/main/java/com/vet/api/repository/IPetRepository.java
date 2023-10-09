package com.vet.api.repository;

import com.vet.api.pet.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPetRepository extends JpaRepository<Pet, Integer> {
    Page<Pet> findAllByAtivoTrue(Pageable paginacao);
}
