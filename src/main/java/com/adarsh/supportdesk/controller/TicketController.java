package com.adarsh.supportdesk.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import com.adarsh.supportdesk.dto.TicketResponse;
import com.adarsh.supportdesk.service.TicketService;

import jakarta.validation.constraints.PositiveOrZero;

import com.adarsh.supportdesk.dto.TicketCreateRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController 
//Spring, watch this class because methods inside it may handle HTTP requests.
public class TicketController {
    // Serialization - java object to json
    // @GetMapping("/api/tickets")
    // When an HTTP GET request arrives at /api/tickets, 
    // execute the Java method directly below this annotation.
    // public String getTickets()
    // {
    //     return "Support Desk api is working";
    // }
    // now instead of returning java
    // @GetMapping("/api/tickets")
    // public Map<String,Object> getTickets()
    // {
        // HashMap<String,Object> ticket = new HashMap<>();
        // ticket.put("id",1);
        // ticket.put("title","Unable to login");
        // ticket.put("status","open");
        // return ticket;
        // now when spring recieves Map 
        // its library jackson converts Map
        // into json  and send back response using tomcat 

        //returning arbitrary Maps from APIs is a bad idea, 
        // and  professional Spring applications use DTOs.
    /*     Map
     ────────────────────────────
      any String key
      almost any Object value
      DTO creation using record TicketResponse
    ────────────────────────────
        id must be Long
        title must be String
        status must be String
        */
    //    @GetMapping("/api/tickets")
    // public TicketResponse getTickets()
    // {
    //   TicketResponse ticket= new TicketResponse(1L /*long literal; Java autoboxes it to Long */,"Unable to login","online");
    //   return ticket;
    // }
    // now fixing url logic as it wants tickets and we are giving him 
    // one ticket instead we should return collection of tickets for ticket
        // //  @GetMapping("/api/tickets")
        //  public List<TicketResponse> getTickets()
        //  {
        //    TicketResponse ticket1 = new TicketResponse(1L,"Unable to Log in ","Open");
        //    TicketResponse ticket2 = new TicketResponse(1L,"Unable to Log in ","Open");
        //    List<TicketResponse> ls  = new ArrayList<>();
        // //    ls.add(ticket1);
        // //    ls.add(ticket2);
        // //    return ls; 
        //     return List.of(ticket1,ticket2);

        //  }
        //   @GetMapping("/api/tickets/{id}")
        //   public TicketResponse getTicketByID(@PathVariable Long id)
        //   { 
        //      return new TicketResponse(id,"Unable to login","Open");
            
        //   }
          // deserialization json to java object 
//           @PostMapping("/api/tickets")
//                   //@RequestBody understands:
// //"Take the JSON inside the HTTP request body and convert it into this Java type."
// //"{
// //   "title": "Payment failed"
// // }
// // becomes approximately:
// // new TicketCreateRequest("Payment failed")
// // automatically."
//           public TicketResponse createTicket(@Valid @RequestBody TicketCreateRequest request)
//           // now what if validation fails
//           // that where we need something to handle errors creating exception package
//           {
//             return new TicketResponse(3L,request.title(),"Open");
//           }
    //now why controller does everythign thats why we will make service that will handle all the business logic 
    // here as we know beans - spring creates and manages objects under services 
// now we get to know about dependency injection 
 // as order dont matter in java we can create anywhere 
 // but always do it initially for readibilty
 // why final we dont want change the character of this servicde to someother one 
 private final TicketService ticketService;
 // calling ticketcontroller constructore to get ticketservice 
 public TicketController(TicketService ticketService)
 {
    this.ticketService = ticketService;
 }
 // notice we never written new Ticketservice and then passed to constructor
 // and theree may be doubt that how ticketcontroller object will be created and ticketservice object be passed 
 // thats where dependency injection comes into play
 /*Conceptually:
Application starts
      ↓
Spring sees @Service
      ↓
creates TicketService
      ↓
Spring sees @RestController
      ↓
TicketController constructor requires TicketService
      ↓
Spring already has TicketService
      ↓
passes it into constructor
Equivalent idea:
TicketService service = new TicketService();
TicketController controller =
        new TicketController(service);
Except Spring performs this object creation and connection for us.
That is:
Dependency Injection
Plain English:
A class says what object it needs, and Spring provides that object instead of the class creating it itself. */
//now chaging post method upper one is 
@PostMapping("/api/tickets")
  public TicketResponse createTicket(@Valid @RequestBody TicketCreateRequest request){
     return ticketService.createTicket(request);
  }
  // NOW DOING SAME FOR GET METHODS
    @GetMapping("/api/tickets")
         public List<TicketResponse> getTickets()
         {
           return ticketService.getAllTickets();
         }
  
          @GetMapping("/api/tickets/{id}")
          public TicketResponse getTicketByID(@PathVariable Long id)
          { 
             return ticketService.getTicketById(id);
            
          }

}
