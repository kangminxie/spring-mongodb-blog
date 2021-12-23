package com.kangmin.app.config.oauth2;

import com.kangmin.app.config.oauth2.model.AuthProvider;
import com.kangmin.app.config.oauth2.model.OAuth2UserInfo;
import com.kangmin.app.config.oauth2.model.OAuth2UserPrincipal;
import com.kangmin.app.dao.AccountMongoDao;
import com.kangmin.app.model.Account;
import com.kangmin.app.config.oauth2.exception.OAuth2AuthenticationProcessingException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;
import java.util.UUID;

@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final AccountMongoDao accountDao;

    public OAuth2UserService(final AccountMongoDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public OAuth2User loadUser(
        final OAuth2UserRequest oAuth2UserRequest
    ) throws OAuth2AuthenticationException {
        final OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (final AuthenticationException ex) {
            throw ex;
        } catch (final Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(
        final OAuth2UserRequest oAuth2UserRequest,
        final OAuth2User oAuth2User
    ) {
        final OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(
                oAuth2UserRequest.getClientRegistration().getRegistrationId(),
                oAuth2User.getAttributes()
        );

        if (ObjectUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        final Optional<Account> accountOpt = accountDao.findByEmail(oAuth2UserInfo.getEmail());
        Account account;
        if (accountOpt.isPresent()) {
            account = accountOpt.get();
            if (!account.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId().toUpperCase()))) {
                throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with "
                        + account.getProvider() + " account. Please use your " + account.getProvider()
                        + " account to login.");
            }
            account = updateExistingUser(account, oAuth2UserInfo);
        } else {
            account = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }

        return OAuth2UserPrincipal.create(account, oAuth2User.getAttributes());
    }

    private Account registerNewUser(
        final OAuth2UserRequest oAuth2UserRequest,
        final OAuth2UserInfo oAuth2UserInfo
    ) {
        Account user = new Account();
        final String gid = oAuth2UserRequest.getClientRegistration().getRegistrationId().toUpperCase();
        System.out.println("### gid is: " + gid);
        user.setProvider(AuthProvider.valueOf(gid));
        user.setProviderId(oAuth2UserInfo.getId()); // (google sub) 103540028556894457296
        user.setId(UUID.randomUUID().toString());
        user.setUsername(oAuth2UserInfo.getEmail()); // same as email
        user.setDisplayName(oAuth2UserInfo.getName());
        user.setEmail(oAuth2UserInfo.getEmail());
        // user.setImageUrl(oAuth2UserInfo.getImageUrl());
        user.setRole("NORMAL");
        user.setCreatedTimestamp(System.currentTimeMillis());
        return accountDao.save(user);
    }

    private Account updateExistingUser(
        final Account existingUser,
        final OAuth2UserInfo oAuth2UserInfo
    ) {
        existingUser.setDisplayName(oAuth2UserInfo.getName());
        // existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
        return accountDao.save(existingUser);
    }
}
