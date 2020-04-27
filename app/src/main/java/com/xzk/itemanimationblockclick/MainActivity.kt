package com.xzk.itemanimationblockclick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.xzk.itemanimationblockclick.ui.main.BaseFragment
import com.xzk.itemanimationblockclick.ui.main.ClickAnimatorFragment
import com.xzk.itemanimationblockclick.ui.main.DefaultAnimatorFragment
import com.xzk.itemanimationblockclick.ui.main.TagAnimatorFragment

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

        findViewById<View>(R.id.tag_fragment).setOnClickListener {
            setFragment(TagAnimatorFragment())
        }
    }

    private fun setFragment(fragment: BaseFragment<*>) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commitNow()
    }
}
