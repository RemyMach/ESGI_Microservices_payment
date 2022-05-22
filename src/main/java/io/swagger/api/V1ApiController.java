package io.swagger.api;

import io.swagger.application.CreatePayment;
import io.swagger.application.CreatePaymentCommandHandler;
import io.swagger.domain.PaymentProof;
import io.swagger.domain.PaymentProofs;
import io.swagger.infrastructure.PaymentProofMapper;
import io.swagger.model.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.model.PaymentProofResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-05-21T20:07:15.167Z")

@RestController
public class V1ApiController implements V1Api {

    private static final Logger log = LoggerFactory.getLogger(V1ApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final CreatePaymentCommandHandler createPaymentCommandHandler;

    @org.springframework.beans.factory.annotation.Autowired
    public V1ApiController(CreatePaymentCommandHandler createPaymentCommandHandler, ObjectMapper objectMapper, HttpServletRequest request) {
        this.createPaymentCommandHandler = createPaymentCommandHandler;
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<PaymentProofResponse> addPayment(@ApiParam(value = "Payment object to create"  )  @Valid @RequestBody Payment body) throws InterruptedException {
        PaymentProof paymentProof = this.createPaymentCommandHandler.handle(new CreatePayment(body.getId().toString(), body.getBuyerInfo(), body.getAmount(), body.getCurrency(), body.getDate().toString(), body.getSellerAccount()));
        PaymentProofResponse paymentProofResponse = PaymentProofMapper.mapPaymentProofToPaymentProofResponse(paymentProof);
        System.out.println("paymentProofResponse dans le controller");
        System.out.println(paymentProofResponse.toString());
        //return ResponseEntity.status(HttpStatus.CREATED).body(message);
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(paymentProofResponse);
        //return new ResponseEntity<PaymentProofResponse>(paymentProofResponse, HttpStatus.valueOf(201));
    }

}
