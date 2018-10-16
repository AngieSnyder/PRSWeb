package com.prs.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prs.business.purchaserequest.PurchaseRequest;
import com.prs.business.purchaserequest.PurchaseRequestLineItem;
import com.prs.business.purchaserequest.PurchaseRequestLineItemRepository;
import com.prs.util.JsonResponse;
@CrossOrigin
@Controller
@RequestMapping("/PurchaseRequestLineItem")
public class PurchaseRequestLineItemController {

	@Autowired
	private PurchaseRequestLineItemRepository purchaseRequestLineItemRepository;

	@GetMapping("/List")
	public @ResponseBody JsonResponse getAllPurchaseRequestLineItems() {
		try {
			return JsonResponse.getInstance(purchaseRequestLineItemRepository.findAll());
		}
		catch (Exception e) {
		return JsonResponse.getErrorInstance("Purchase Request list failure: "+e.getMessage(), e);
		}
	}

	@GetMapping("/Get/{id}")
	public @ResponseBody JsonResponse getPurchaseRequestLineItem(@PathVariable int id) {
		try {
			Optional<PurchaseRequestLineItem> purchaseRequestLineItem = purchaseRequestLineItemRepository.findById(id);
			if (purchaseRequestLineItem.isPresent())
				return JsonResponse.getInstance(purchaseRequestLineItem.get());
			else
				return JsonResponse.getErrorInstance("Product not found for id: "+id, null);
		}
		catch (Exception e) {		
			return JsonResponse.getErrorInstance("Error getting product: "+e.getMessage(), null);
		}
	}

	@PostMapping("/Add")
	public @ResponseBody JsonResponse addPurchaseRequestLineItem(@RequestBody PurchaseRequestLineItem purchaseRequestLineItem) {
		return savePurchaseRequestLineItem(purchaseRequestLineItem);
	}

	@PostMapping("/Change")
	public @ResponseBody JsonResponse updatePurchaseRequestLineItem(@RequestBody PurchaseRequestLineItem purchaseRequestLineItem) {
		return savePurchaseRequestLineItem (purchaseRequestLineItem);
	}
	private @ResponseBody JsonResponse savePurchaseRequestLineItem(@RequestBody PurchaseRequestLineItem purchaseRequestLineItem) {
		try {
			purchaseRequestLineItemRepository.save(purchaseRequestLineItem);
			return JsonResponse.getInstance(purchaseRequestLineItem);
		}
		catch (DataIntegrityViolationException ex) {
			return JsonResponse.getErrorInstance(ex.getRootCause().toString(), ex);
		}
		catch (Exception ex) {
			return JsonResponse.getErrorInstance(ex.getMessage(), ex);
		}
	}

	@PostMapping("/Remove")
	public @ResponseBody JsonResponse removePurchaseRequestLineItem(@RequestBody PurchaseRequestLineItem purchaseRequestLineItem) {
		try {
			purchaseRequestLineItemRepository.delete(purchaseRequestLineItem);
			return JsonResponse.getInstance(purchaseRequestLineItem);
		}
		catch (Exception ex) {
			return JsonResponse.getErrorInstance(ex.getMessage(), ex);
		}
	}

}
