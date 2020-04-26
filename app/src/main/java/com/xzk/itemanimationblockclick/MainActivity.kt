package com.xzk.itemanimationblockclick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.xzk.itemanimationblockclick.ui.main.BaseFragment
import com.xzk.itemanimationblockclick.ui.main.ClickAnimatorFragment
import com.xzk.itemanimationblockclick.ui.main.DefaultAnimatorFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        findViewById<View>(R.id.default_fragment).setOnClickListener {
            setFragment(DefaultAnimatorFragment())
        }

        findViewById<View>(R.id.click_fragment).setOnClickListener {
            setFragment(ClickAnimatorFragment())
        }
    }

    private fun setFragment(fragment: BaseFragment<*>) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commitNow()
    }
}
