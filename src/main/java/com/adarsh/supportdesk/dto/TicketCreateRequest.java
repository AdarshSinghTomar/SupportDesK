package com.adarsh.supportdesk.dto;
// DTo type - Data coming INTO the backened
// client only sends title now converting that data to java object

import javax.swing.Spring;

//2 - adding validation that title sent by user cant be blank 
import jakarta.validation.constraints.NotBlank;;

public record TicketCreateRequest 
(  // title must not be null, empty, or only spaces.
   //@NotBlank 
   // or we can put one message also if validation fails
   @NotBlank (message = "Title is required")
    String title
    /*But this alone is not enough
This is important.
We have defined the rule:
title must not be blank
But Spring also needs to know:
“Please actually run validation on this incoming request.”
For that, modify your controller method.*/
// @valid in calling function 
/*@NotBlank
=
defines the rule

@Valid
=
tells Spring to run validation */
){
    
}
