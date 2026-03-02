package maxwellhicks.dev.job_monitor.service;

import maxwellhicks.dev.job_monitor.model.JobPosting;
import maxwellhicks.dev.job_monitor.repository.JobPostingRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobSyncService {
    private final GreenhouseClient greenhouseClient;
    private final JobPostingRepository jobPostingRepository;

    public JobSyncService(GreenhouseClient greenhouseClient, JobPostingRepository jobPostingRepository) {
        this.greenhouseClient = greenhouseClient;
        this.jobPostingRepository = jobPostingRepository;
    }

    public void syncCompany(String companySlug) {
        List<GreenhouseClient.JobData> fetchedJobs = greenhouseClient.fetchJobs(companySlug);

        for (GreenhouseClient.JobData jobData : fetchedJobs) {
            boolean alreadyExists = jobPostingRepository
                    .findByExternalId(jobData.externalId)
                    .isPresent();

            if (!alreadyExists) {
                JobPosting newJob = new JobPosting();
                newJob.setCompany(companySlug);
                newJob.setTitle(jobData.title);
                newJob.setLocation(jobData.location);
                newJob.setExternalId(jobData.externalId);
                newJob.setUrl(jobData.url);
                newJob.setFirstSeen(LocalDateTime.now());

                jobPostingRepository.save(newJob);
                System.out.println("New job saved " + jobData.title + " @ " + companySlug);
            }
        }
    }
}
