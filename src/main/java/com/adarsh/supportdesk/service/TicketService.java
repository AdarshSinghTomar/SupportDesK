package com.adarsh.supportdesk.service;
import com.adarsh.supportdesk.dto.*;

import java.util.List;
import java.util.Optional;

import com.adarsh.supportdesk.repository.TicketRepository;
import com.adarsh.supportdesk.model.Ticket;
import com.adarsh.supportdesk.exception.TicketNotFoundException;

import org.springframework.stereotype.Service;
@Service 
//“Spring, this class contains application/business logic and I want you to manage its object
/*That second part is important.
Normally in Java you would write:
TicketService service = new TicketService();
You create the object yourself.
With Spring we will not normally do that.
Instead:
Spring application starts
        ↓
Spring finds @Service
        ↓
Spring creates TicketService object
        ↓
Spring keeps that object available
That Spring-managed object has an official name:
Bean
Don't make the word complicated.
For now:
Bean
=
an object created and managed by Spring */

public class TicketService {
//     public TicketResponse createTicket(TicketCreateRequest request)
//     {
//         return new TicketResponse(3L,request.title(),"OPEN");
//     }
//     public List<TicketResponse> getAllTickets() {

//     TicketResponse ticket1 =
//             new TicketResponse(
//                     1L,
//                     "Unable to login",
//                     "OPEN"
//             );

//     TicketResponse ticket2 =
//             new TicketResponse(
//                     2L,
//                     "Payment failed",
//                     "IN_PROGRESS"
//             );

//     return List.of(ticket1, ticket2);
// }
// public TicketResponse getTicketById(Long id) {
   
//     return new TicketResponse(
//             id,
//             "Unable to login",
//             "OPEN"
//     );
// }
// now what currently we were doing is that TicketService  was creating the ticket and saving it inside himself 
// but saving and storage related things will be handled by repository this service will handle only ticket creating and validation logic
// so first importing Ticket and TicketRepository
// then doing constructor injection ie spring will provide the object of ticketrepository when object of this class is made 
// again we can define variables and methods anywhere but for the learning purpose only
private final TicketRepository ticketRepository;
public TicketService(TicketRepository ticketRepository)
{
        this.ticketRepository=ticketRepository;
}
// now using both our model ie ticket and ticket repository to create ticket 
public TicketResponse createTicket(TicketCreateRequest request)
{
        Ticket ticket = new Ticket(null,request.title(),"Online");
        Ticket savedTicket = ticketRepository.save(ticket);
        return new TicketResponse(savedTicket.getId(), savedTicket.getTitle(),savedTicket.getStatus());
}
public List<TicketResponse> getAllTickets()
{
  List<Ticket> tickets = ticketRepository.findAll();
  return tickets.stream().map(ticket-> new TicketResponse(ticket.getId(),ticket.getTitle(),ticket.getStatus())).toList();
}
public TicketResponse getTicketById(Long id) {
        //created a new Ticket not found Exception in exception package to handle  Ticket not found 
        // but there is one more problem suppose it throws exception then what should http response should be 
        // as it need 404 NOT FOUND + clean json 
        // now we use GlobalExceptionHandler
   Ticket ticket = ticketRepository.findbyId(id).orElseThrow(()->new TicketNotFoundException(id));
        
     return new TicketResponse(ticket.getId(),ticket.getTitle(),ticket.getStatus());
}
}
