package me.marensovich.wareznet.controller.v1.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateCategoryRequest {
    private UUID uuid;
    private String name;
}
