package com.example.demo.audit;


import com.example.demo.entity.User;
import lombok.NonNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class SpringSecurityAuditAwareImpl implements AuditorAware<UUID> {

    @Override
    public @NonNull Optional<UUID> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication == null || !authentication.isAuthenticated()
                || Objects.equals(authentication.getPrincipal(),"anonymousUser"))){
            return Optional.of(((User) authentication.getPrincipal()).getId());
        }
        return Optional.empty();
    }
}
