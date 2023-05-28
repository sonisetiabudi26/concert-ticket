package com.ticket.concert.service;

import com.ticket.concert.domain.Customer;
import com.ticket.concert.domain.CustomerOrder;
import com.ticket.concert.domain.OrderTicket;
import com.ticket.concert.domain.Ticket;
import com.ticket.concert.repository.CustomerOrderRepository;
import com.ticket.concert.repository.CustomerRepository;
import com.ticket.concert.repository.OrderRepository;
import com.ticket.concert.repository.TicketRepository;
import com.ticket.concert.vo.BookVo;
import com.ticket.concert.vo.ConcertVo;
import com.ticket.concert.vo.TicketVo;

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
    private CustomerOrderRepository custOrderRepository;

    @Transactional
    public TicketVo bookTicket(BookVo bookReq) throws Exception {
        try {
            CustomerOrder dataCustomer = new CustomerOrder();
            dataCustomer.setOrderTime(new Date());
            dataCustomer.setDeliveryEmailAddress(bookReq.getCustOrder().getDeliveryEmailAddress());
            dataCustomer.setTimePaid(new Date());
            dataCustomer.setTimeSent(new Date());
            dataCustomer.setTotalPrice(bookReq.getCustOrder().getTotalPrice());
            dataCustomer.setDiscount(bookReq.getCustOrder().getDiscount());
            dataCustomer.setFinalPrice(bookReq.getCustOrder().getFinalPrice());

            Optional<Customer> customer = custRepository.findById(bookReq.getCustOrder().getCustId());
            if (!customer.isPresent()) {
                throw new Exception("Sorry, customer not found");
            }
            dataCustomer.setCustomer(customer.get());

            CustomerOrder customerID = custOrderRepository.saveAndFlush(dataCustomer);
           

            Optional<Ticket> ticket = ticketRepository.findById(bookReq.getOrder().getTicketId());

            if (!ticket.isPresent()) {
                throw new Exception("Sorry, ticket not found");
            }
            OrderTicket dataOrder = new OrderTicket();
            dataOrder.setCustomerOrder(customerID);
            dataOrder.setTicket(ticket.get());
            orderRepository.save(dataOrder);
            return mappingData(ticket.get());

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw new Exception("Error");
        }

    }

    private TicketVo mappingData(Ticket ticket) throws Exception {

        TicketVo data = new TicketVo();

        data.setSerialNumber(ticket.getSerialNumber());
        data.setSeat(ticket.getSeat());
        data.setPurchaseDate(ticket.getPurchaseDate());
        data.setDescription(ticket.getTicketCategory().getDesc());
        data.setStartDate(ticket.getTicketCategory().getStartDate());
        data.setEndDate(ticket.getTicketCategory().getEndDate());
        data.setPrice(ticket.getTicketCategory().getPrice());
        data.setArea(ticket.getTicketCategory().getArea());
        data.setTicketSlot(ticket.getTicketCategory().getTicketSlot());
        data.setRemainingTicket(ticket.getTicketCategory().getRemainingTicket());
        ConcertVo dataConcert = concertService.getConcert(ticket.getConcert().getId());
        data.setConcert(dataConcert);

        return data;
    }

}
