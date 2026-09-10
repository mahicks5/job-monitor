package maxwellhicks.dev.job_monitor.controller;

import maxwellhicks.dev.job_monitor.repository.JobPostingRepository;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JobController {
    private final JobPostingRepository jobPostingRepository;

    public JobController(JobPostingRepository jobPostingRepository) {
        this.jobPostingRepository = jobPostingRepository;
    }

    @GetMapping("/")
    public String index(@RequestParam(required = false) String company, Model model) {
        if (company != null && !company.isBlank()) {
            model.addAttribute("jobs", jobPostingRepository.findByCompany(company));
        } else {
            model.addAttribute("jobs", jobPostingRepository.findAll());
        }
        return "index";
    }

    @PostMapping("/admin/clear")
    public String clearJobs() {
        jobPostingRepository.deleteAll();
        return "redirect:/";
    }
}
