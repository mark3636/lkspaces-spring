package com.example.lkplaces.service;

import com.example.lkplaces.jpa.enums.EnumActionType;
import com.example.lkplaces.jpa.enums.EnumDomainType;

public interface AuditService {
    void audit(EnumActionType actionType, EnumDomainType domainType);

    void audit(String initiator, EnumActionType actionType, EnumDomainType domainType);
}
