package appsembly.appsembly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import appsembly.appsembly.entities.AssemblyEntity;

public interface AssemblyRepository extends JpaRepository<AssemblyEntity, Long> {

}
