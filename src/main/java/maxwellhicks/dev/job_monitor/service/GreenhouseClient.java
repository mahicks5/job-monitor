package maxwellhicks.dev.job_monitor.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GreenhouseClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL = "https://boards-api.greenhouse.io/v1/boards/{slug}/jobs";

    public List<JobData> fetchJobs(String companySlug) {
        String url = BASE_URL.replace("{slug}", companySlug);
        JsonNode response = restTemplate.getForObject(url, JsonNode.class);

        List<JobData> jobs = new ArrayList<>();
        if (response != null && response.has("jobs")) {
            for (JsonNode node : response.get("jobs")) {
                JobData job = new JobData();
                job.externalId = node.get("id").asText();
                job.title = node.get("title").asText();
                job.url = node.get("absolute_url").asText();
                job.location = node.get("location").path("name").asText();
                jobs.add(job);
            }
        }

        return jobs;
    }

    public static class JobData {
        public String externalId;
        public String title;
        public String location;
        public String url;
    }
}
