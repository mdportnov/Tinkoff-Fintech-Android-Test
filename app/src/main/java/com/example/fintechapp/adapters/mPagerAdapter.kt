package com.example.fintechapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.fintechapp.fragments.TabTopFragment
import com.example.fintechapp.fragments.TabLatestFragment
import com.example.fintechapp.fragments.TabRandomFragment

class mPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return TabRandomFragment()
            1 -> return TabLatestFragment()
            2 -> return TabTopFragment()
        }
        return TabRandomFragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> return "Случайные"
            1 -> return "Последние"
            2 -> return "Лучшие"
        }
        return super.getPageTitle(position)
    }
}