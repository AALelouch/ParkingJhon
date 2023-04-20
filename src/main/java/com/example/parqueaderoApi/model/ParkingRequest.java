package com.example.parqueaderoApi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParkingRequest {
  private Long id;
  private Boolean status;
  
}
