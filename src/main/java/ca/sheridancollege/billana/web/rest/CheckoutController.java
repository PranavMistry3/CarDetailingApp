package ca.sheridancollege.billana.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import ca.sheridancollege.billana.services.PaypalService;

@Controller
public class CheckoutController {

	@Autowired
	private PaypalService paypalService;

	@GetMapping("/checkout")
	public String checkout(Model model) {
		// Render the checkout page
		return "checkout";
	}

	@PostMapping("/checkout")
	public String pay(Model model) {
		return "success";
		/*
		 * try { // Create a new PayPal payment Payment payment =
		 * paypalService.createPayment(total, currency);
		 * 
		 * // Get the approval URL and redirect the user to PayPal to complete the
		 * payment String approvalUrl = payment.getLinks().stream().filter(link ->
		 * link.getRel().equals("approval_url")) .findFirst().orElseThrow(() -> new
		 * PayPalRESTException("No approval_url found in Payment")) .getHref(); return
		 * "success";
		 * 
		 * } catch (PayPalRESTException e) { model.addAttribute("error",
		 * e.getMessage()); return "error"; }
		 */
	}

	@GetMapping("/success")
	public String success(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId,
			Model model) {
		try {
			// Execute the PayPal payment and get the details
			Payment payment = paypalService.executePayment(paymentId, payerId);
			model.addAttribute("payment", payment);
			return "success";

		} catch (PayPalRESTException e) {
			model.addAttribute("error", e.getMessage());
			return "error";
		}
	}

	@GetMapping("/cancel")
	public String cancel() {
		// Render the cancel page
		return "cancel";
	}
}