package com.priyanshu.PTInvest.service;

import com.priyanshu.PTInvest.dto.request.HoldingRequest;
import com.priyanshu.PTInvest.dto.response.HoldingResponse;
import com.priyanshu.PTInvest.entity.Holding;
import com.priyanshu.PTInvest.repository.HoldingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HoldingService {

    private final HoldingRepository holdingRepository;

    public HoldingResponse addHolding(HoldingRequest holdingRequest) {
        Holding holding = new Holding(holdingRequest);
        return new HoldingResponse(holdingRepository.save(holding));
    }

    public List<HoldingResponse> getAllHoldings() {
        List<Holding> holdings= holdingRepository.findAll();
        return holdings.stream().map(HoldingResponse::new).toList();
    }

    public Optional<HoldingResponse> updateHolding(Long id, HoldingRequest holdingRequest) {
        return holdingRepository.findById(id)
                .map(holding -> {
                    applyRequest(holding, holdingRequest);
                    return new HoldingResponse(holdingRepository.save(holding));
                });
    }

    public boolean deleteHolding(Long id) {
        if (!holdingRepository.existsById(id)) {
            return false;
        }
        holdingRepository.deleteById(id);
        return true;
    }

    private void applyRequest(Holding holding, HoldingRequest holdingRequest) {
        holding.setSymbol(holdingRequest.getSymbol());
        holding.setAssetType(holdingRequest.getAssetType());
        holding.setQuantity(holdingRequest.getQuantity());
        holding.setBuyPrice(holdingRequest.getBuyPrice());
        holding.setCurrentPrice(holdingRequest.getCurrentPrice());
        holding.setPurchaseDate(holdingRequest.getPurchaseDate());
    }


}
