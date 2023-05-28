package com.ticket.concert.controller;

import com.ticket.concert.vo.BookVo;
import com.ticket.concert.vo.TicketCustomerVo;
import com.ticket.concert.vo.TicketVo;
import com.ticket.concert.service.CustomerService;
import com.ticket.concert.service.OrderService;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
public class OrderController {
  
    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

   

    @PostMapping("/ticket")
    public ResponseEntity<Object> bookTicket(@Valid @RequestBody BookVo bookReq){  
        TicketCustomerVo dataResp= orderService.bookTicket(bookReq);
           
          return ResponseEntity.ok().body(dataResp);
       
    }

}
