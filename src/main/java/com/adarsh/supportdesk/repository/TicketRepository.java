package com.adarsh.supportdesk.repository;
import com.adarsh.supportdesk.model.Ticket;
import java.util.List;
import java.lang.classfile.ClassFile.Option;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.Map;
import org.springframework.stereotype.Repository;
@Repository 
public class TicketRepository {
    private Long nextID =3L;
    private Map<Long,Ticket> tickets = new LinkedHashMap<>();
    public TicketRepository(){
        tickets.put(1L , new Ticket(1L,"Unable to open","open"));
        tickets.put(2L,new Ticket(2L,"Payment Failed","InProgress"));
    }
    public List<Ticket> findAll(){
        return new ArrayList<>(tickets.values());
    }
    public Optional<Ticket> findbyId(Long Id)
    {
        return Optional.ofNullable(tickets.get(Id));
    }
    public Ticket save(Ticket ticket)
    { // notice we are not directly putting ticket as client will send only title other fields will not be visible 
        Ticket savedTicket= new Ticket(nextID,ticket.getTitle(), ticket.getStatus());
        tickets.put(nextID,savedTicket);
        nextID++;
        return savedTicket;
    }
}
