package com.test.ui.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Patterns;

import com.com.xuanyuan.library.LiveBus;
import com.test.data.LoginRepository;
import com.test.data.Result;
import com.test.data.model.LoggedInUser;
import com.xuanyuan.makefun.R;

public class LoginViewModel extends ViewModel {
    // 登录状态 实体
    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private LoginFormState loginStateBean = new LoginFormState(false);
    // 登陆结果 实体
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }


    //  登陆数据库控制器
    private LoginRepository loginRepository;

    public LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;

    }


    public void login(final Editable username, final Editable password) {
        if (username == null || password == null) {
            return;
        }
        // can be launched in a separate asynchronous job
        // 可以是一个异步线程
        // 此处使用 Rxjava方式
        Result<LoggedInUser> result = loginRepository.login(username.toString(), password.toString());
        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }

    // 登陆数据变化了，紧接着会进行监控处理
    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginStateBean.setUsernameError(R.string.invalid_username);
            loginFormState.setValue(loginStateBean);
//            loginFormState.setValue(  new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginStateBean.setPasswordError(R.string.invalid_password);
            loginFormState.setValue(loginStateBean);

//            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginStateBean.setDataValid(true);
            loginFormState.setValue(loginStateBean);
//            loginFormState.setValue(new LoginFormState(true));
            //
        }
    }

    /**
     * A placeholder username validation check
     * 字段名验证，属于业务逻辑故需要放在ViewModel中
     */
    private boolean isUserNameValid(String username) {
        if (TextUtils.isEmpty(username)) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return username.trim().isEmpty();
        }
    }

    /**
     * 业务逻辑的判断
     * A placeholder password validation check
     */
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

}
