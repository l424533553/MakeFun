package com.xuanyuan.makefun;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Toast;

import com.com.xuanyuan.library.MyToast;
import com.test.ui.login.LoginActivity;
import com.xuanyuan.makefun.databinding.ActivityHomeBinding;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private ActivityHomeBinding binding;
    private String TAG = "123456";
    private String Place = "HomeActivity";
    private Context context;

    /**
     * @param savedInstanceState 保存的记录状态
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
        Log.i(TAG, Place + "=onCreate");
        context = this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        initDrawer();
        setListener();
    }

    /**
     * VM 初始化设置 Drawer
     */
    private void initDrawer() {
//        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(binding.layoutBarHome.toolbar);

//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.layoutBarHome.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        binding.navView.setNavigationItemSelectedListener(this);
    }

    /**
     * VM 设置点击监听
     */
    private void setListener() {
        binding.setOnClickListener(this);
        //需要额外的进行查找id设置监听
        View view = binding.navView.getHeaderView(0);
        view.findViewById(R.id.ivHead).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, Place + "=onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, Place + "=onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, Place + "=onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    // 使用 功能 与开发测试
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
    }

    /**
     * 返回按键,VM。
     * 使用，使用设置
     */
    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // 使用，
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    /**
     * @param item 菜单 Item 。
     * @return 返回菜单是否可以点击
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * @param item 项目
     * @return 返回结果
     */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        item.getSubMenu().setGroupVisible(R.id.gp11, true);
        if (id == R.id.nav_home) {
            // Handle the camera action

        } else if (id == R.id.nav_gallery) {
            // 使用情况报告 ，说明情况。使用情况。// 使用结果

        } else if (id == R.id.nav_slideshow) {
            // 数据结果。

        } else if (id == R.id.nav_tools) {
            // 使用内容
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            Intent intent = new Intent(this, ItemListActivity.class);
            startActivity(intent);
        }

        //
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /***
     *
     * 使用情况，使用过程。
     * @param v 使用情况
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivHead:
                MyToast.show(this, "点击了头像");
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.fab:
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                MyToast.toastShort(context, "点击了Action");
                            }
                        }).show();
                break;
            default:
                break;
        }
    }
}