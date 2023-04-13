package com.example.parqueaderoApi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ParkingResponse {
  
  private Long id;
  private Boolean status;
  
}
