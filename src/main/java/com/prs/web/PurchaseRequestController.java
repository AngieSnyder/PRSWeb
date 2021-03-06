package com.prs.web;

import java.time.LocalDateTime;
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
import com.prs.business.purchaserequest.PurchaseRequestRepository;
import com.prs.util.JsonResponse;
@CrossOrigin
@Controller
@RequestMapping("/PurchaseRequest")
public class PurchaseRequestController {

	@Autowired
	private PurchaseRequestRepository purchaseRequestRepository;

	@GetMapping("/List")
	public @ResponseBody JsonResponse getAllPurchaseRequests() {
		try {
			return JsonResponse.getInstance(purchaseRequestRepository.findAll());
		}
		catch (Exception e) {
		return JsonResponse.getErrorInstance("Purchase Request list failure: "+e.getMessage(), e);
		}
	}
	@GetMapping("/ListReview")
	public @ResponseBody JsonResponse getAllPurchaseRequestForReview(@RequestParam int id) {
		try {
			return JsonResponse.getInstance(purchaseRequestRepository.findAllByUserIdNotAndStatus(id, "Review"));
		}
		catch (Exception e) {
			return JsonResponse.getErrorInstance("Purchase request list failure: "+e.getMessage(), e);
		}		
	}

	@GetMapping("/Get/{id}")
	public @ResponseBody JsonResponse getPurchaseRequest(@PathVariable int id) {
		try {
			Optional<PurchaseRequest> purchaseRequest = purchaseRequestRepository.findById(id);
			if (purchaseRequest.isPresent())
				return JsonResponse.getInstance(purchaseRequest.get());
			else
				return JsonResponse.getErrorInstance("Product not found for id: "+id);
		}
		catch (Exception e) {		
			return JsonResponse.getErrorInstance("Error getting product: "+e.getMessage());
		}
	}

	@PostMapping("/Add")
	public @ResponseBody JsonResponse addPurchaseRequest(@RequestBody PurchaseRequest purchaseRequest) {
		return savePurchaseRequest(purchaseRequest);
	}

	@PostMapping("/Change")
	public @ResponseBody JsonResponse updatePurchaseRequest(@RequestBody PurchaseRequest purchaseRequest) {
		return savePurchaseRequest (purchaseRequest);
	}
	private @ResponseBody JsonResponse savePurchaseRequest(@RequestBody PurchaseRequest purchaseRequest) {
		try {
			purchaseRequestRepository.save(purchaseRequest);
			return JsonResponse.getInstance(purchaseRequest);
		}
		catch (DataIntegrityViolationException ex) {
			return JsonResponse.getErrorInstance(ex.getRootCause().toString(), ex);
		}
		catch (Exception ex) {
			return JsonResponse.getErrorInstance(ex.getMessage(), ex);
		}
	}

	@PostMapping("/Remove")
	public @ResponseBody JsonResponse removePurchaseRequest(@RequestBody PurchaseRequest purchaseRequest) {
		try {
			purchaseRequestRepository.delete(purchaseRequest);
			return JsonResponse.getInstance(purchaseRequest);
		}
		catch (Exception ex) {
			return JsonResponse.getErrorInstance(ex.getMessage(), ex);
		}
	}
	  
	@PostMapping("/SubmitForReview")
	public @ResponseBody JsonResponse submitForReview (@RequestBody PurchaseRequest purchaseRequest) {
		if (purchaseRequest.getTotal()<=51)
			purchaseRequest.setStatus(PurchaseRequest.STATUS_APPROVED);
		else
			purchaseRequest.setStatus(PurchaseRequest.STATUS_REVIEW);
		purchaseRequest.setSubmittedDate(LocalDateTime.now());
		return savePurchaseRequest(purchaseRequest);
	}
	@PostMapping("/ApprovePR")
	public @ResponseBody JsonResponse approvePurchaseRequest (@RequestBody PurchaseRequest purchaseRequest) {
		purchaseRequest.setStatus(PurchaseRequest.STATUS_APPROVED);
		return savePurchaseRequest(purchaseRequest);
	}
	@PostMapping("/RejectedPR")
	public @ResponseBody JsonResponse rejectPurchaseRequest (@RequestBody PurchaseRequest purchaseRequest) {
		purchaseRequest.setStatus(PurchaseRequest.STATUS_REJECTED);
		return savePurchaseRequest(purchaseRequest);
	}
}
