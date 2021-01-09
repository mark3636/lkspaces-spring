package com.example.lkplaces.service;

import com.example.lkplaces.jpa.enums.EnumActionType;
import com.example.lkplaces.jpa.enums.EnumDomainType;

import java.util.List;

public interface AuditService {
    void audit(EnumActionType actionType, EnumDomainType domainType);

    void audit(String initiator, EnumActionType actionType, EnumDomainType domainType);

    List<Object> getAudit();
}
