package com.prs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.prs.business.purchaserequest.PurchaseRequest;
import com.prs.business.purchaserequest.PurchaseRequestRepository;
import com.prs.business.user.User;
import com.prs.business.user.UserRepository;



//@RunWith(SpringRunner.class)
//public class PRSPurchaseRequestTest extends PrsWebApplicationTests {
//	@Autowired
//	private PurchaseRequestRepository purchaseRequestRepository;
//	
//	@Autowired
//	private UserRepository userRepository;
//	
//	@Test
//	public void testPurchaseRequestCrudFuncionts() {
//		Iterable<PurchaseRequest> purchaseRequests = purchaseRequestRepository.findAll();
//		assertNotNull(purchaseRequests);
//		
//		Optional<User> u = userRepository.findById(1);		
//		PurchaseRequest p1 = new PurchaseRequest(u.get(), "description", "justification", LocalDateTime.now(), "deliveryMode", "status", 100.00, LocalDateTime.now(), "reasonForRejection");
//		assertNotNull(purchaseRequestRepository.save(p1));
//		int id = p1.getId();
//		
//		Optional<PurchaseRequest> p2 = purchaseRequestRepository.findById(id);
//		assertEquals(p2.get().getDescription(),"description");
//		
//		p2.get().setDescription("newPurchaseRequestName");
//		assertNotNull(purchaseRequestRepository.save(p2.get()));
//		
//		purchaseRequestRepository.delete(p2.get());
//		assertThat(!(purchaseRequestRepository.findById(id)).isPresent());
//	}
//}
