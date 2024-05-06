package appsembly.appsembly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import appsembly.appsembly.entities.VoteEntity;

public interface VoteRepository extends JpaRepository<VoteEntity, Long> {

}
