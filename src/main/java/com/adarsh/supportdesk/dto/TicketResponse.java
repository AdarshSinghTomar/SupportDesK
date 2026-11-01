package com.adarsh.supportdesk.dto;
// DTO Type - data going out of the backened
public record TicketResponse(
 Long id, String title, String status
) {   
}
