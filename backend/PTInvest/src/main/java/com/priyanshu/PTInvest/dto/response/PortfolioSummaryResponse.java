package com.priyanshu.PTInvest.dto.response;

import com.priyanshu.PTInvest.entity.AssetType;
import java.math.BigDecimal;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioSummaryResponse {

    private BigDecimal totalInvested;

    private BigDecimal totalCurrentValue;

    private BigDecimal totalGainLoss;

    private BigDecimal totalGainLossPct;

    /** Current-value allocation percentages, keyed by asset type. */
    private Map<AssetType, BigDecimal> allocationByAssetType;

    private long holdingsCount;

    private HoldingPerformance topGainer;

    private HoldingPerformance topLoser;

    /** Compact performance data for a highlighted holding. */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HoldingPerformance {

        private String symbol;

        private BigDecimal gainLossPct;
    }
}
