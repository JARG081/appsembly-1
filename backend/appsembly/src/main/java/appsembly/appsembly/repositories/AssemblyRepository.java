package appsembly.appsembly.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import appsembly.appsembly.entities.AssemblyEntity;

public interface AssemblyRepository extends JpaRepository<AssemblyEntity, UUID> {

}
