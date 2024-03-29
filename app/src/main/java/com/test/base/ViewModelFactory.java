package com.test.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.test.data.LoginDataSource;
import com.test.data.LoginRepository;
import com.test.ui.login.LoginViewModel;

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * ViewModel的生成工厂，将提供LoginViewModel。
 * Required given LoginViewModel has a non-empty constructor
 * 给定LoginViewModel必须具有非空的构造函数
 *
 * 说明： ViewModel的构造工厂
 */
public class ViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(LoginRepository.getInstance(new LoginDataSource()));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
