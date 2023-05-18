package com.moviebookingapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class AuthResponse {
  private Customer customer;
  private String token;

}
