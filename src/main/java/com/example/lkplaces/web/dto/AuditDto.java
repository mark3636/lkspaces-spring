package com.example.lkplaces.web.dto;

import com.example.lkplaces.jpa.enums.EnumActionType;
import com.example.lkplaces.jpa.enums.EnumDomainType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditDto {
    private String initiator;
    private EnumActionType actionType;
    private EnumDomainType domainType;
    private String dateTime;
}
