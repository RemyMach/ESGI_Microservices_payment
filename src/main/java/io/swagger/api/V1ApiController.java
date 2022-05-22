package io.swagger.api;

import io.swagger.application.CreatePayment;
import io.swagger.application.CreatePaymentCommandHandler;
import io.swagger.model.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-05-21T20:07:15.167Z")

@Controller
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

    public ResponseEntity<Void> addPayment(@ApiParam(value = "Payment object to create"  )  @Valid @RequestBody Payment body) throws InterruptedException {
        String accept = request.getHeader("Accept");
        this.createPaymentCommandHandler.handle(new CreatePayment(body.getId().toString(), body.getBuyerInfo(), body.getAmount(), body.getCurrency(), body.getDate().toString(), body.getSellerAccount()));

        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
