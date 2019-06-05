package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    @RequestMapping(value = "/results")
    public String displayResults(Model model,
                                 @RequestParam String searchType,
                                 @RequestParam String searchTerm) {
        ArrayList<HashMap<String, String>> jobs;

        if (searchType.equals("all")) {
            jobs = JobData.findByValue(searchTerm);
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        int numJobs = jobs.size();
        String numResults = "Your Search Has Returned " + (numJobs) + " Result(s)";
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("jobs", jobs);
        model.addAttribute("numResults", numResults);

        if(jobs.isEmpty()) {
            String noJobs = "There were no jobs that matched your search, \"" + searchTerm + "\". Please try another query.";
            model.addAttribute("noJobs", noJobs);
        }
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

}
