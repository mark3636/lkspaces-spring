package com.example.lkplaces.service.impl;

import com.example.lkplaces.jpa.entity.User;
import com.example.lkplaces.jpa.enums.EnumActionType;
import com.example.lkplaces.jpa.enums.EnumDomainType;
import com.example.lkplaces.security.CurrentUserProvider;
import com.example.lkplaces.service.AuditService;
import com.example.lkplaces.web.dto.AuditDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class AuditServiceImpl implements AuditService {
    private final CurrentUserProvider currentUserProvider;
    private WebClient webClient;
    @Value("${lkplaces.audit.url}")
    private String auditServiceURL;

    @Autowired
    public AuditServiceImpl(CurrentUserProvider currentUserProvider) {
        this.currentUserProvider = currentUserProvider;
    }

    @PostConstruct
    public void initWebClient() {
        this.webClient = WebClient.create(auditServiceURL);
    }

    @Override
    public void audit(EnumActionType actionType, EnumDomainType domainType) {
        audit(Optional.ofNullable(currentUserProvider.getCurrentUser())
                        .orElse(User.builder().email("undefined").build())
                        .getEmail(),
                actionType, domainType);
    }

    @Override
    public void audit(String initiator, EnumActionType actionType, EnumDomainType domainType) {
        AuditDto auditDto = AuditDto.builder()
                .initiator(initiator)
                .actionType(actionType)
                .domainType(domainType)
                .dateTime(LocalDateTime.now().toString())
                .build();
        try {
            webClient.post()
                    .body(BodyInserters.fromValue(auditDto))
                    .retrieve()
                    .toBodilessEntity()
                    .block();
        } catch (Exception e) {
            log.error("Failed to invoke audit service", e);
        }
    }
}
