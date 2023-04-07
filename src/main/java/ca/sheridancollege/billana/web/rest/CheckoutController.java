package ca.sheridancollege.billana.web.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import ca.sheridancollege.billana.domain.ChargeForm;
import ca.sheridancollege.billana.services.PaypalService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CheckoutController {
	private final PaypalService paypalService;

	@Autowired
	public CheckoutController(PaypalService paypalService) {
		this.paypalService = paypalService;
	}

	@GetMapping("/checkout")
	public String showCheckout() {
		return "checkout";
	}

	@PostMapping("/pay")
	public String pay(@RequestParam("amount") Double amount, @RequestParam("currency") String currency,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String cancelUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/cancel";
		String successUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/success";

		try {
			Payment payment = paypalService.createPayment(amount, currency, cancelUrl, successUrl);
			for (Links links : payment.getLinks()) {
				if (links.getRel().equals("approval_url")) {
					return "redirect:" + links.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
		}
		return "success";
	}

	@GetMapping("/success")
	public String success(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if (payment.getState().equals("approved")) {
				redirectAttributes.addFlashAttribute("success", "Payment successful!");
				return "redirect:/checkout";
			}
		} catch (PayPalRESTException e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
		}
		redirectAttributes.addFlashAttribute("error", "Payment failed!");
		return "success.html";
	}

	@GetMapping("/cancel")
	public String cancel(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("error", "Payment cancelled!");
		return "redirect:/checkout";
	}

	@GetMapping("/charge")
	public String showChargeForm(Model model) {
		model.addAttribute("chargeForm", new ChargeForm()); // add a new instance of ChargeForm to the model
		return "StripePayment"; // this returns a view named "charge" (charge.html) in your templates folder
	}

	@PostMapping("/charge")
	public String charge(@RequestParam("stripeToken") String token, @RequestParam("amount") Integer amount) {
		Stripe.apiKey = "pk_test_51MqLGWFaowb4ZdNZbc4A6lurjSrNbhULZFbhjrLJiV3n5tTlttidTDUuANdOpuL1eB12mzTsTTpK6ZBZHbCmCWaG00bTSgeju1\r\n";

		Map<String, Object> params = new HashMap<>();
		params.put("amount", amount);
		params.put("currency", "usd");
		params.put("source", token);

		return "success";
	}
}
