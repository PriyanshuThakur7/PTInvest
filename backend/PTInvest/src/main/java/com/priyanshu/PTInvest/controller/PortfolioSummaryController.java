package com.priyanshu.PTInvest.controller;

import com.priyanshu.PTInvest.dto.response.PortfolioSummaryResponse;
import com.priyanshu.PTInvest.service.PortfolioSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/portfolio")
@RequiredArgsConstructor
public class PortfolioSummaryController {

    private final PortfolioSummaryService portfolioSummaryService;

    @GetMapping("/summary")
    public PortfolioSummaryResponse getPortfolioSummary() {
        return portfolioSummaryService.getPortfolioSummary();
    }
}
