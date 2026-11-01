package com.adarsh.supportdesk.service;
import com.adarsh.supportdesk.dto.*;

import java.util.List;

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
    public TicketResponse createTicket(TicketCreateRequest request)
    {
        return new TicketResponse(3L,request.title(),"OPEN");
    }
    public List<TicketResponse> getAllTickets() {

    TicketResponse ticket1 =
            new TicketResponse(
                    1L,
                    "Unable to login",
                    "OPEN"
            );

    TicketResponse ticket2 =
            new TicketResponse(
                    2L,
                    "Payment failed",
                    "IN_PROGRESS"
            );

    return List.of(ticket1, ticket2);
}
public TicketResponse getTicketById(Long id) {

    return new TicketResponse(
            id,
            "Unable to login",
            "OPEN"
    );
}

}
