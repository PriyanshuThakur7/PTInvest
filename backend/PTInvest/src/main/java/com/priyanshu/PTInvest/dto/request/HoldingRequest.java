package com.priyanshu.PTInvest.dto.request;

import com.priyanshu.PTInvest.entity.AssetType;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HoldingRequest {

    private String symbol;

    private AssetType assetType;

    private BigDecimal quantity;

    private BigDecimal buyPrice;

    private BigDecimal currentPrice;

    private LocalDate purchaseDate;

}
