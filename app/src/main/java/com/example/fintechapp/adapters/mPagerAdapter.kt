package com.example.fintechapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.fintechapp.fragments.TabTopFragment
import com.example.fintechapp.fragments.TabHotFragment
import com.example.fintechapp.fragments.TabLatestFragment

class mPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return TabLatestFragment()
            1 -> return TabTopFragment()
            2 -> return TabHotFragment()
        }
        return TabLatestFragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> return "Последние"
            1 -> return "Лучшие"
            2 -> return "Горячие"
        }
        return super.getPageTitle(position)
    }
}