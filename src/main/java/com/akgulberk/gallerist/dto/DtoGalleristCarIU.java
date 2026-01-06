package com.akgulberk.gallerist.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoGalleristCarIU {

    @NotNull
    private Long galleristId;

    @NotNull
    private Long carId;
}

