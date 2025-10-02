package me.marensovich.wareznet.controller.v1.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class UpdateTypeRequest {
    private UUID id;
    private String name;
}
