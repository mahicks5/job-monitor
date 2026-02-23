package maxwellhicks.dev.job_monitor.repository;

import maxwellhicks.dev.job_monitor.model.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
    Optional<JobPosting> findByExternalId(String externalId);
}
