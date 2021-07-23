package com.llw.mvplibrary.newnet.environment;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.llw.mvplibrary.R;


/**
 * 设置网络环境Activity
 *
 * @author llw
 */
public class NetworkEnvironmentActivity extends AppCompatActivity {

    //网络环境
    public static final String NETWORK_ENVIRONMENT = "network_environment";
    //当前网络环境
    private static String mCurrentNetworkEnvironment = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_environment);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, new MyPreferenceFragment())
                .commit();
        //获取默认缓存
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        //如果没有值就默认为 “1”  在这里 1 表示正式环境
        mCurrentNetworkEnvironment = preferences.getString(NETWORK_ENVIRONMENT,"1");
    }

    /**
     * 内部缓存变化监听类
     */
    public static class MyPreferenceFragment extends PreferenceFragmentCompat
            implements Preference.OnPreferenceChangeListener {

        //创建缓存
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            //这个相当于Activity的setContentView，从资源文件中添Preferences ，选择的值将会自动保存到SharePreferences
            addPreferencesFromResource(R.xml.environment_preference);
            //设置缓存变化监听 ， 通过键来设置监听
            findPreference(NETWORK_ENVIRONMENT).setOnPreferenceChangeListener(this);
        }

        //缓存变化
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            if (!mCurrentNetworkEnvironment.equalsIgnoreCase(String.valueOf(newValue))) {
                //当前值与缓存中不一致时，说明切换了网络，这时提醒一下
                Toast.makeText(getContext(), R.string.network_change_tip, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    }

    /**
     * 页面返回
     */
    @Override
    public void onBackPressed() {
        //获取缓存对象
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        //通过键获取缓存则，没有则使用默认值
        String value = preferences.getString(NETWORK_ENVIRONMENT, "1");
        if (!mCurrentNetworkEnvironment.equalsIgnoreCase(value)) {
            //不一致.说明有修改，从操作系统中结束掉当前程序的进程
            android.os.Process.killProcess(android.os.Process.myPid());
        } else {
            //一致  没有修改则关闭当前页面
            finish();
        }
    }

    /**
     * 是否为正式环境
     */
    public static boolean isFormalEnvironment(Application application) {
        //获取当前应用的缓存
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(application);
        String networkEnvironment = preferences.getString(NETWORK_ENVIRONMENT, "1");
        return "1".equalsIgnoreCase(networkEnvironment);
    }
}
