package com.priyanshu.PTInvest.service;

import com.priyanshu.PTInvest.dto.response.HoldingResponse;
import com.priyanshu.PTInvest.dto.response.PortfolioSummaryResponse;
import com.priyanshu.PTInvest.entity.AssetType;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortfolioSummaryService {

    private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

    private final HoldingService holdingService;

    public PortfolioSummaryResponse getPortfolioSummary() {
        List<HoldingResponse> holdings = holdingService.getAllHoldings();
        BigDecimal totalInvested = calculateTotalInvested(holdings);
        BigDecimal totalCurrentValue = calculateTotalCurrentValue(holdings);
        BigDecimal totalGainLoss = totalCurrentValue.subtract(totalInvested);

        PortfolioSummaryResponse summary = new PortfolioSummaryResponse();
        summary.setTotalInvested(totalInvested);
        summary.setTotalCurrentValue(totalCurrentValue);
        summary.setTotalGainLoss(totalGainLoss);
        summary.setTotalGainLossPct(calculateGainLossPct(totalGainLoss, totalInvested));
        summary.setAllocationByAssetType(calculateAllocationByAssetType(holdings, totalCurrentValue));
        summary.setHoldingsCount(holdings.size());
        summary.setTopGainer(toHoldingPerformance(findTopGainer(holdings)));
        summary.setTopLoser(toHoldingPerformance(findTopLoser(holdings)));
        return summary;
    }

    private BigDecimal calculateTotalInvested(List<HoldingResponse> holdings) {
        return holdings.stream()
                .map(holding -> holding.getQuantity().multiply(holding.getBuyPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateTotalCurrentValue(List<HoldingResponse> holdings) {
        return holdings.stream()
                .map(HoldingResponse::getCurrentValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateGainLossPct(BigDecimal totalGainLoss, BigDecimal totalInvested) {
        return totalInvested.signum() == 0
                ? BigDecimal.ZERO
                : totalGainLoss.multiply(ONE_HUNDRED).divide(totalInvested, 2, RoundingMode.HALF_UP);
    }

    private Map<AssetType, BigDecimal> calculateAllocationByAssetType(
            List<HoldingResponse> holdings, BigDecimal totalCurrentValue) {
        Map<AssetType, BigDecimal> allocation = new EnumMap<>(AssetType.class);
        if (totalCurrentValue.signum() == 0) {
            return allocation;
        }

        for (HoldingResponse holding : holdings) {
            allocation.merge(holding.getAssetType(), holding.getCurrentValue(), BigDecimal::add);
        }
        allocation.replaceAll((assetType, currentValue) -> currentValue.multiply(ONE_HUNDRED)
                .divide(totalCurrentValue, 2, RoundingMode.HALF_UP));
        return allocation;
    }

    private HoldingResponse findTopGainer(List<HoldingResponse> holdings) {
        return holdings.stream().max(Comparator.comparing(HoldingResponse::getGainLossPct)).orElse(null);
    }

    private HoldingResponse findTopLoser(List<HoldingResponse> holdings) {
        return holdings.stream().min(Comparator.comparing(HoldingResponse::getGainLossPct)).orElse(null);
    }

    private PortfolioSummaryResponse.HoldingPerformance toHoldingPerformance(HoldingResponse holding) {
        return holding == null
                ? null
                : new PortfolioSummaryResponse.HoldingPerformance(holding.getSymbol(), holding.getGainLossPct());
    }
}
