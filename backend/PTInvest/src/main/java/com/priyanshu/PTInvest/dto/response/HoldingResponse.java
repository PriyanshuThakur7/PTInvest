package com.priyanshu.PTInvest.dto.response;

import com.priyanshu.PTInvest.entity.AssetType;
import com.priyanshu.PTInvest.entity.Holding;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class HoldingResponse {

    private String symbol;

    private AssetType assetType;

    private BigDecimal quantity;

    private BigDecimal buyPrice;

    private BigDecimal currentPrice;

    private LocalDate purchaseDate;

    private BigDecimal currentValue;

    private BigDecimal profitLoss;

    private BigDecimal gainLossPct;

    public HoldingResponse(Holding holding) {
        this.symbol=holding.getSymbol();
        this.assetType=holding.getAssetType();
        this.quantity=holding.getQuantity();
        this.buyPrice=holding.getBuyPrice();
        this.currentPrice=holding.getCurrentPrice();
        this.purchaseDate=holding.getPurchaseDate();
        this.currentValue=holding.getCurrentPrice().multiply(holding.getQuantity());
        this.profitLoss=this.currentValue.subtract(holding.getBuyPrice().multiply(holding.getQuantity()));
        BigDecimal investedValue = holding.getBuyPrice().multiply(holding.getQuantity());
        this.gainLossPct = investedValue.signum() == 0
                ? BigDecimal.ZERO
                : this.profitLoss.multiply(BigDecimal.valueOf(100))
                        .divide(investedValue, 4, java.math.RoundingMode.HALF_UP);
    }

}
