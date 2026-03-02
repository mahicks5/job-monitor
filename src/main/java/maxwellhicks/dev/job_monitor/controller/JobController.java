package maxwellhicks.dev.job_monitor.controller;

import maxwellhicks.dev.job_monitor.repository.JobPostingRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JobController {
    private final JobPostingRepository jobPostingRepository;

    public JobController(JobPostingRepository jobPostingRepository) {
        this.jobPostingRepository = jobPostingRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("jobs", jobPostingRepository.findAll());
        return "index";
    }

    @PostMapping("/admin/clear")
    public String clearJobs() {
        jobPostingRepository.deleteAll();
        return "redirect:/";
    }
}
