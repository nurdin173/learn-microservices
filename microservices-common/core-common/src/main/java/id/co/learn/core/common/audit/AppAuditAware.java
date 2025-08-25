package id.co.learn.core.common.audit;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@NoArgsConstructor
public class AppAuditAware implements AuditorAware<String> {
    private HttpServletRequest request;

    @Override
    public Optional<String> getCurrentAuditor() {
        String loggedUser = request.getHeader("loggedUser");
        if (loggedUser == null) return Optional.of("SYSTEM");
        return Optional.of(loggedUser);
    }
}
