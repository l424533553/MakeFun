package com.test.ui.login;

import android.support.annotation.Nullable;

/**
 * Data validation state of the login form.
 */
class LoginFormState {
    @Nullable
    private Integer usernameError;
    @Nullable
    private Integer passwordError;
    //验证通过了
    private boolean isDataValid;

    LoginFormState(@Nullable Integer usernameError, @Nullable Integer passwordError) {
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.isDataValid = false;
    }

    LoginFormState(boolean isDataValid) {
        this.usernameError = null;
        this.passwordError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    Integer getUsernameError() {
        return usernameError;
    }

    @Nullable
    Integer getPasswordError() {
        return passwordError;
    }

    public void setUsernameError(@Nullable Integer usernameError) {
        this.usernameError = usernameError;
        passwordError = null;
        isDataValid = false;
    }

    public void setPasswordError(@Nullable Integer passwordError) {
        this.passwordError = passwordError;
        usernameError = null;
        isDataValid = false;
    }

    public void setDataValid(boolean dataValid) {
        isDataValid = dataValid;
        if (isDataValid) {
            passwordError = null;
            usernameError = null;
        }
    }

    boolean isDataValid() {
        return isDataValid;
    }
}
