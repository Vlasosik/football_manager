package org.example.football_manager.command.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommandResponse {
    private String message;
    private int statusCode;
}
