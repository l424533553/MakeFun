package com.test.ui.login;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.test.base.ViewModelFactory;
import com.xuanyuan.makefun.R;
import com.xuanyuan.makefun.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
//        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new ViewModelFactory()).get(LoginViewModel.class);

        setListener();
        setObserve();
    }

    private void setObserve() {
        // 登陆表单字段信息已经变化
        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                binding.btnlogin.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    binding.tilUsername.setError(getString(loginFormState.getUsernameError()));
                } else {
                    binding.tilUsername.setErrorEnabled(false);
                }
                if (loginFormState.getPasswordError() != null) {
                    binding.tilPassword.setError(getString(loginFormState.getPasswordError()));
                } else {
                    binding.tilPassword.setErrorEnabled(false);
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                binding.loading.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);
                //Complete and destroy login activity once successful
                finish();
            }
        });
    }

    /**
     * 设置监听
     */
    private void setListener() {
        // 输入控制器
        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (binding.username.getText() == null || binding.password.getText() == null) {
                    return;
                }
                loginViewModel.loginDataChanged(binding.username.getText().toString(), binding.password.getText().toString());
            }
        };
        binding.username.addTextChangedListener(afterTextChangedListener);
        binding.password.addTextChangedListener(afterTextChangedListener);
        binding.password.setOnEditorActionListener(this);
        binding.setOnClickListener(this);
    }

    //登陆成功体验
    private void updateUiWithUser(LoggedInUserView model) {
        //提示登录成功
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    /**
     * 登陆失败
     *
     * @param errorString 提示的登录错误信息
     */
    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnlogin:
                binding.loading.setVisibility(View.VISIBLE);
                loginViewModel.login(binding.username.getText(), binding.password.getText());
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            loginViewModel.login(binding.username.getText(), binding.password.getText());
        }
        return false;
    }
}
