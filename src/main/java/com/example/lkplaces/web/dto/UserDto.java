package com.example.lkplaces.web.dto;

import com.example.lkplaces.jpa.enums.EnumRole;
import com.example.lkplaces.web.validation.ExistingG;
import com.example.lkplaces.web.validation.NewG;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"password"})
@EqualsAndHashCode
public class UserDto {
    @Null(groups = {NewG.class})
    @NotNull(groups = {ExistingG.class})
    private Integer id;
    @NotEmpty(groups = {NewG.class, ExistingG.class})
    private String email;
    @NotEmpty(groups = {NewG.class})
    private String firstName;
    @NotEmpty(groups = {NewG.class})
    private String lastName;
    @NotEmpty(groups = {NewG.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private EnumRole role;
}
