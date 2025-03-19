package edu.wctc.stockpurchase.controller;

import com.github.fge.jsonpatch.JsonPatch;
import edu.wctc.stockpurchase.entity.StockPurchase;
import edu.wctc.stockpurchase.service.StockPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stockpurchases")
public class StockPurchaseController {

    private final StockPurchaseService stockPurchaseService;

    @Autowired
    public StockPurchaseController(StockPurchaseService stockPurchaseService) {
        this.stockPurchaseService = stockPurchaseService;
    }

    @GetMapping
    public List<StockPurchase> getPurchases() {
        return stockPurchaseService.findAll();
    }

    @GetMapping("/{purchaseId}")
    public StockPurchase getPurchase(@PathVariable int purchaseId) {
        return stockPurchaseService.findById(purchaseId);
    }

    @PostMapping
    public StockPurchase createPurchase(@RequestBody StockPurchase purchase) {
        return stockPurchaseService.createPurchase(purchase);
    }

    @PutMapping
    public StockPurchase updatePurchase(@RequestBody StockPurchase purchase) {
        return stockPurchaseService.updatePurchase(purchase);
    }

    @PatchMapping("/{purchaseId}")
    public StockPurchase patchPurchase(
            @PathVariable int purchaseId,
            @RequestBody JsonPatch patch) {
        return stockPurchaseService.patchPurchase(purchaseId, patch);
    }

    @DeleteMapping("/{purchaseId}")
    public String deletePurchase(@PathVariable int purchaseId) {
        stockPurchaseService.deletePurchase(purchaseId);
        return "Successfully deleted stock purchase with ID " + purchaseId;
    }
}
