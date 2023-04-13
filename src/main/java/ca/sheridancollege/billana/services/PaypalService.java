package ca.sheridancollege.billana.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@Service
public class PaypalService {
	private static final String CLIENT_ID = "AcFnwUIpOFwyjLz3DbvQOJvZcmN-1uFQE2qVVMFtNJNBcpbui_MYGXPdpwmFXQNK4UVaFTSoh8WDv-X_";
	private static final String CLIENT_SECRET = "ED6gBCnn0SxiMr7UhwfxamwYxkjnZTUWFGBepcygxv9Nhz_pmJIyp-v6edt4WI_0oJvAd9FSrmRekhlA";
	private static final String MODE = "sandbox"; // or "live" for production

	private final APIContext apiContext;

	@Autowired
	public PaypalService() {
		apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
	}

	public Payment createPayment(Double total, String currency)
			throws PayPalRESTException {
		// Set payment details
		Amount amount = new Amount();
		amount.setCurrency(currency);
		amount.setTotal(String.format("%.2f", total));

		Transaction transaction = new Transaction();
		transaction.setDescription("Payment description");
		transaction.setAmount(amount);

		List<Transaction> transactions = new ArrayList<>();
		transactions.add(transaction);

		// Set redirect URLs
		RedirectUrls redirectUrls = new RedirectUrls();

		// Set payment details
		Payment payment = new Payment();
		payment.setIntent("sale");
		payment.setPayer(new Payer());
		payment.setRedirectUrls(redirectUrls);
		payment.setTransactions(transactions);

		// Create payment
		return payment.create(apiContext);
	}

	public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
		// Create payment execution details
		PaymentExecution paymentExecution = new PaymentExecution();
		paymentExecution.setPayerId(payerId);

		// Execute payment
		Payment payment = new Payment();
		payment.setId(paymentId);
		return payment.execute(apiContext, paymentExecution);
	}

}
