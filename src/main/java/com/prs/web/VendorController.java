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
import org.springframework.web.bind.annotation.ResponseBody;

import com.prs.business.vendor.Vendor;
import com.prs.business.vendor.VendorRepository;
import com.prs.util.JsonResponse;
@CrossOrigin
@Controller
@RequestMapping("/Vendors")
public class VendorController {

	@Autowired
	private VendorRepository vendorRepository;

	@GetMapping("/List")
	public @ResponseBody JsonResponse getAllVendors() {
		try {
			return JsonResponse.getInstance(vendorRepository.findAll());
		}
		catch (Exception e) {
			return JsonResponse.getErrorInstance("Vendor list failure:"+e.getMessage(), e);
		}
	}

	@GetMapping("/Get/{id}")
	public @ResponseBody JsonResponse getVendor(@PathVariable int id) {
		try {
			Optional<Vendor> vendor = vendorRepository.findById(id);
			if (vendor.isPresent())
				return JsonResponse.getInstance(vendor.get());
			else
				return JsonResponse.getErrorInstance("Vendor not found for id:"+id, null);
		}
		catch (Exception e) {
			return JsonResponse.getErrorInstance("Error getting user: "+e.getMessage(), null);
		}
	}

	@PostMapping("/Add")
	public @ResponseBody JsonResponse addVendor(@RequestBody Vendor vendor) {
		return saveVendor(vendor);
	}

	@PostMapping("/Change")
	public @ResponseBody JsonResponse updateVendor(@RequestBody Vendor vendor) {
		return saveVendor(vendor);
	}
	private @ResponseBody JsonResponse saveVendor(@RequestBody Vendor vendor) {
		try {
			vendorRepository.save(vendor);
			return JsonResponse.getInstance(vendor);
		}
		catch (DataIntegrityViolationException ex) {
			return JsonResponse.getErrorInstance(ex.getRootCause().toString(), ex);
		}
		catch (Exception ex) {
			return JsonResponse.getErrorInstance(ex.getMessage(), ex);
		}
	}

	@PostMapping("/Remove")
	public @ResponseBody JsonResponse removeVendor(@RequestBody Vendor vendor) {
		try {
			vendorRepository.delete(vendor);
			return JsonResponse.getInstance(vendor);
		}
		catch (Exception ex) {
			return JsonResponse.getErrorInstance(ex.getMessage(), ex);
		}
	}
}
