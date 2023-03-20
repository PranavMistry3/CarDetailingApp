package ca.sheridancollege.billana.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

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
		return null;

	}
}
