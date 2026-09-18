package com.priyanshu.PTInvest.controller;

import com.priyanshu.PTInvest.dto.request.HoldingRequest;
import com.priyanshu.PTInvest.dto.response.HoldingResponse;
import com.priyanshu.PTInvest.service.HoldingService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/holdings")
@RequiredArgsConstructor
public class HoldingController {

    private final HoldingService holdingService;

    @PostMapping
    public ResponseEntity<HoldingResponse> createHolding(@RequestBody HoldingRequest holdingRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(holdingService.addHolding(holdingRequest));
    }

    @GetMapping
    public List<HoldingResponse> getAllHoldings() {
        return holdingService.getAllHoldings();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HoldingResponse> updateHolding(
            @PathVariable Long id, @RequestBody HoldingRequest holdingRequest) {
        return holdingService.updateHolding(id, holdingRequest)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHolding(@PathVariable Long id) {
        return holdingService.deleteHolding(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
