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

import com.prs.business.purchaserequest.PurchaseRequest;
import com.prs.business.purchaserequest.PurchaseRequestRepository;

@Controller
@RequestMapping("/PurchaseRequest")
public class PurchaseRequestController {
	
	@Autowired
	private PurchaseRequestRepository purchaseRequestRepository;
	
	@GetMapping("/List")
	public @ResponseBody Iterable<PurchaseRequest> getAllPurchaseRequest() {
		Iterable<PurchaseRequest> purchaseRequests = purchaseRequestRepository.findAll();
		return purchaseRequests;
	}
	
	@GetMapping("/Get")
	public @ResponseBody Optional<PurchaseRequest> getPurchaseRequest(@RequestParam int id) {
		Optional<PurchaseRequest>purchaseRequest = purchaseRequestRepository.findById(id);
		return purchaseRequest;
	}
	
	@PostMapping("/Add")
	public @ResponseBody PurchaseRequest addPurchaseRequest(@RequestBody PurchaseRequest purchaseRequest) {
		purchaseRequestRepository.save(purchaseRequest);
		return purchaseRequest;
	}
	
	@PostMapping("/Change")
	public @ResponseBody PurchaseRequest updatePurchaseRequest(@RequestBody PurchaseRequest purchaseRequest) {
		purchaseRequestRepository.save(purchaseRequest);
		return purchaseRequest;
	}
	
	@PostMapping("/Remove")
	public @ResponseBody String removePurchaseRequest(@RequestBody PurchaseRequest purchaseRequest) {
		purchaseRequestRepository.delete(purchaseRequest);
		return "purchaseRequest deleted";
	}

}
