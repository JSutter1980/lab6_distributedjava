package edu.wctc.stockpurchase.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import edu.wctc.stockpurchase.entity.StockPurchase;
import edu.wctc.stockpurchase.exception.InvalidRequest;
import edu.wctc.stockpurchase.exception.ResourceNotFoundException;
import edu.wctc.stockpurchase.repo.StockPurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class StockPurchaseService {
    private final StockPurchaseRepository stockPurchaseRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public StockPurchaseService(StockPurchaseRepository stockPurchaseRepository, ObjectMapper objectMapper) {
        this.stockPurchaseRepository = stockPurchaseRepository;
        this.objectMapper = objectMapper;
    }

    public List<StockPurchase> findAll() {
        List<StockPurchase> list = new ArrayList<>();
        stockPurchaseRepository.findAll().forEach(list::add);
        return list;
    }

    public StockPurchase findById(int id) {
        return stockPurchaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StockPurchase",id));

    }

    public StockPurchase createPurchase(StockPurchase purchase) {
        purchase.setId(0);
        return stockPurchaseRepository.save(purchase);
    }

    public StockPurchase updatePurchase(StockPurchase purchase) {
        return stockPurchaseRepository.save(purchase);
    }

    public StockPurchase patchPurchase(int id, JsonPatch patch) {
        StockPurchase existingPurchase = findById(id);

        try {
            JsonNode patched = patch.apply(objectMapper.convertValue(existingPurchase, JsonNode.class));

            StockPurchase patchedPurchase = objectMapper.treeToValue(patched, StockPurchase.class);

            stockPurchaseRepository.save(patchedPurchase);

            return patchedPurchase;
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new InvalidRequest("Unable to update Stock Purchase");
        }
    }

    public void deletePurchase(int id) {
        stockPurchaseRepository.deleteById(id);
    }
}


