package com.codywiki.tonton.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginDto {

    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @JsonProperty(access = Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 100)
    private String password;

}
