package com.prs.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prs.business.purchaserequest.PurchaseRequestLineItem;
import com.prs.business.purchaserequest.PurchaseRequestLineItemRepository;

@Controller
@RequestMapping("/PurchaseRequestLineItem")
public class PurchaseRequestLineItemController {

	@Autowired
	private PurchaseRequestLineItemRepository purchaseRequestLineItemRepository;

	@GetMapping("/List")
	public @ResponseBody Iterable<PurchaseRequestLineItem> getAllPurchaseRequestLineItem() {
		Iterable<PurchaseRequestLineItem> purchaseRequestLineItems = purchaseRequestLineItemRepository.findAll();
		return purchaseRequestLineItems;
	}

	@GetMapping("/Get")
	public @ResponseBody Optional<PurchaseRequestLineItem> getPurchaseRequestLineItem(@RequestParam int id) {
		Optional<PurchaseRequestLineItem> purchaseRequestLineItem = purchaseRequestLineItemRepository.findById(id);
		return purchaseRequestLineItem;
	}

	@PostMapping("/Add")
	public @ResponseBody PurchaseRequestLineItem addPurchaseRequestLineItem(
			@RequestBody PurchaseRequestLineItem purchaseRequestLineItem) {
		purchaseRequestLineItemRepository.save(purchaseRequestLineItem);
		return purchaseRequestLineItem;
	}

	@PostMapping("/Change")
	public @ResponseBody PurchaseRequestLineItem updatePurchaseRequestLineItem(
			@RequestBody PurchaseRequestLineItem purchaseRequestLineItem) {
		purchaseRequestLineItemRepository.save(purchaseRequestLineItem);
		return purchaseRequestLineItem;
	}

	@PostMapping("/Remove")
	public @ResponseBody String removePurchaseRequestLineItem(
			@RequestBody PurchaseRequestLineItem purchaseRequestLineItem) {
		purchaseRequestLineItemRepository.delete(purchaseRequestLineItem);
		return "purchaseRequestLineItem deleted";
	}

}
