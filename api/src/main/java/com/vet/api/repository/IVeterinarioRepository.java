package com.vet.api.repository;

import com.vet.api.veterinario.Veterinario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IVeterinarioRepository extends JpaRepository<Veterinario, Integer> {
    Page<Veterinario> findAllByAtivoTrue(Pageable paginacao);
}
