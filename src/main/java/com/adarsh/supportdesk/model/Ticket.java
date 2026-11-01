package com.adarsh.supportdesk.model;

public class Ticket {
    private Long id;
    private String status;
    private String title;

    public Ticket(Long id, String title, String status)
    { this.id = id;
      this.title = title;
      this.status = status;
    }

    public Long getId() {
        return id;
    }
    public String getStatus() {
        return status;
    }
    public String getTitle() {
        return title;
    }

    
}
