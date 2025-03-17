package com.meetime.hubspot_integrator.infrastructure.persistence;

import com.meetime.hubspot_integrator.domain.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoEntity, Long> {

}
