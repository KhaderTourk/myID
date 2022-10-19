package com.example.waseef.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.waseef.ui.fragments.CollectedFragment
import com.example.waseef.ui.fragments.InvoicesFragment
import com.example.waseef.ui.fragments.PendingFragment

class TabsAdapter(
    fragmentManager: FragmentManager
) :
    FragmentStatePagerAdapter(fragmentManager) {

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> InvoicesFragment()
            1 -> CollectedFragment()
            2 -> PendingFragment()
            else -> {
                InvoicesFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        when (position) {
            0 -> {
                title = "All Invoices"
            }
            1 -> {
                title = "Collected"
            }
            2 -> {
                title = "Pending"
            }
        }
        return title
    }
}
