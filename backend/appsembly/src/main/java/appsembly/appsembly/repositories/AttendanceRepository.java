package appsembly.appsembly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import appsembly.appsembly.entities.AttendanceEntity;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {

}
