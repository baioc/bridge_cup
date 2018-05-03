package br.ufsc.bridge.android.bridgecup.view.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BindingActivity<T extends ViewDataBinding> extends AppCompatActivity {

    public T mBinding;

    public T getBinding() {
        return mBinding;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, getLayoutId());
    }

    public abstract int getLayoutId();
}
