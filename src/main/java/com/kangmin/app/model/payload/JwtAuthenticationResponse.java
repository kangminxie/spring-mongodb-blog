package com.kangmin.app.model.payload;

public class JwtAuthenticationResponse {

    private String jwtToken;
    private Boolean success;

    public JwtAuthenticationResponse(final String jwtToken, final Boolean success) {
        this.jwtToken = jwtToken;
        this.success = success;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(final String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(final Boolean success) {
        this.success = success;
    }
}
