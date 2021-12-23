package com.kangmin.app.config.security;

import com.kangmin.app.dao.AccountMongoDao;
import com.kangmin.app.model.Account;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

import static com.kangmin.app.model.security.WebUserRole.ADMIN;
import static com.kangmin.app.model.security.WebUserRole.NORMAL;
import static com.kangmin.app.model.security.WebUserRole.SUPER_ADMIN;
import static com.kangmin.app.model.security.WebUserRole.UNKNOWN;

@Import({
        PasswordEncoderConfig.class,
})
@Repository
public class WebUserDetailsDaoImpl implements WebUserDetailsDao {

    private final AccountMongoDao accountDao;

    public WebUserDetailsDaoImpl(final AccountMongoDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Optional<WebUserDetails> getUserDetailsByUsername(final String username) {
        final Optional<Account> accountOpt = accountDao.findByUsername(username);

        if (accountOpt.isPresent()) {
            final Account account = accountOpt.get();
            return Optional.of(mapAccountToWebUserDetails(account));
        }

        return Optional.empty();
    }

    private WebUserDetails mapAccountToWebUserDetails(final Account account) {
        return new WebUserDetails(
                account.getUsername(),
                account.getPassword(),
                getAuthorities(account.getRole()),
                true,
                true,
                true,
                true
        );
    }

    private Set<SimpleGrantedAuthority> getAuthorities(final String role) {
        switch (role) {
            case "NORMAL":
                return NORMAL.getGrantedAuthorities();
            case "ADMIN":
                return ADMIN.getGrantedAuthorities();
            case "SUPER_ADMIN":
                return SUPER_ADMIN.getGrantedAuthorities();
            default:
                return UNKNOWN.getGrantedAuthorities();
        }
    }
}
