package maxwellhicks.dev.job_monitor.scheduler;

import maxwellhicks.dev.job_monitor.service.JobSyncService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobScheduler {
    private final JobSyncService jobSyncService;

    public JobScheduler(JobSyncService jobSyncService) {
        this.jobSyncService = jobSyncService;
    }

    // list of company slugs to monitor
    private static final List<String> COMPANIES = List.of(
            "stripe",
            "airbnb",
            "checkr",
            "anthropic"
    );

    @Scheduled(fixedDelay = 36000000)
    public void syncAllCompanies() {
        System.out.println("Starting job sync ...");

        for (String company : COMPANIES) {
            jobSyncService.syncCompany(company);
        }

        System.out.println("Job sync complete");
    }
}
