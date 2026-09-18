package com.priyanshu.PTInvest.entity;

import com.priyanshu.PTInvest.dto.request.HoldingRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "holdings")
@Getter
@Setter
@NoArgsConstructor
public class Holding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 32)
    private String symbol;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AssetType assetType;

    @Column(nullable = false, precision = 19, scale = 8)
    private BigDecimal quantity;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal buyPrice;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal currentPrice;

    @Column(nullable = false)
    private LocalDate purchaseDate;

    public Holding (HoldingRequest holdingRequest) {
        this.symbol = holdingRequest.getSymbol();
        this.assetType = holdingRequest.getAssetType();
        this.quantity = holdingRequest.getQuantity();
        this.buyPrice = holdingRequest.getBuyPrice();
        this.currentPrice = holdingRequest.getCurrentPrice();
        this.purchaseDate = holdingRequest.getPurchaseDate();
    }
}
