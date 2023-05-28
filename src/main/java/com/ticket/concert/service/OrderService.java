package com.ticket.concert.service;

import com.ticket.concert.domain.Concert;
import com.ticket.concert.domain.Customer;
import com.ticket.concert.domain.CustomerOrder;
import com.ticket.concert.domain.OrderTicket;
import com.ticket.concert.domain.Ticket;
import com.ticket.concert.domain.TicketCategory;
import com.ticket.concert.exception.MessageException;
import com.ticket.concert.exception.ResourceNotFoundException;
import com.ticket.concert.repository.CustomerOrderRepository;
import com.ticket.concert.repository.CustomerRepository;
import com.ticket.concert.repository.OrderRepository;
import com.ticket.concert.repository.TicketCategoryRepository;
import com.ticket.concert.repository.TicketRepository;
import com.ticket.concert.vo.BookVo;
import com.ticket.concert.vo.ConcertVo;
import com.ticket.concert.vo.TicketCustomerVo;
import com.ticket.concert.vo.TicketVo;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository custRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ConcertService concertService;

    @Autowired
    private TicketCategoryRepository ticketCategoryRepository;

    @Autowired
    private CustomerOrderRepository custOrderRepository;

    @Transactional
    public TicketCustomerVo bookTicket(BookVo bookReq) {
       
            CustomerOrder dataCustomer = new CustomerOrder();
            dataCustomer.setOrderTime(new Date());
            dataCustomer.setDeliveryEmailAddress(bookReq.getDeliveryEmailAddress());
            dataCustomer.setTimePaid(new Date());
            dataCustomer.setTimeSent(new Date());
            dataCustomer.setTotalPrice(bookReq.getTotalPrice());
            dataCustomer.setDiscount(bookReq.getDiscount());
            dataCustomer.setFinalPrice(bookReq.getFinalPrice());

            Optional<Customer> customer = custRepository.findById(bookReq.getCustId());
            if (!customer.isPresent()) {
                throw new MessageException("Sorry, customer not found");
            }
            dataCustomer.setCustomer(customer.get());

            CustomerOrder customerID = custOrderRepository.saveAndFlush(dataCustomer);
        

            TicketCategory ticketCat = ticketCategoryRepository.findById(bookReq.getConcertId()).orElseThrow(() -> new  MessageException("Ticket Category not found"));
            if (null==ticketCat) {
                throw new MessageException("Sorry, ticket category not found");
            }
            
            if(ticketCat.getRemainingTicket()>0){
                ticketCat.setRemainingTicket(ticketCat.getRemainingTicket()-1);
            }else{
                throw new MessageException("Sorry,Ticket not available");
            }

            Ticket dataTicket=new Ticket();
            dataTicket.setConcert(ticketCat.getConcert());
            dataTicket.setPurchaseDate(new Date());
            dataTicket.setSeat(bookReq.getSeat());
            dataTicket.setTicketCategory(ticketCat);
            Long tm=new Timestamp(System.currentTimeMillis()).getTime();
            String sn=ticketCat.getConcert().getId().toString().concat(tm.toString());
            dataTicket.setSerialNumber(sn);
            Ticket ticketID = ticketRepository.saveAndFlush(dataTicket);
            OrderTicket dataOrder = new OrderTicket();
            dataOrder.setCustomerOrder(customerID);
            dataOrder.setTicket(ticketID);
            orderRepository.save(dataOrder);

            ticketCategoryRepository.save(ticketCat);
           

            return mappingData(ticketID);

        

    }

    private TicketCustomerVo mappingData(Ticket ticket) {

        TicketCustomerVo data = new TicketCustomerVo();

        data.setSerialNumber(ticket.getSerialNumber());
        data.setSeat(ticket.getSeat());
        data.setPurchaseDate(ticket.getPurchaseDate());
        data.setDescription(ticket.getTicketCategory().getDesc());
        data.setStartDate(ticket.getTicketCategory().getStartDate());
        data.setEndDate(ticket.getTicketCategory().getEndDate());
        data.setPrice(ticket.getTicketCategory().getPrice());
        data.setArea(ticket.getTicketCategory().getArea());
        ConcertVo dataConcert = concertService.getConcert(ticket.getConcert().getId());
        data.setConcert(dataConcert);

        return data;
    }

}
