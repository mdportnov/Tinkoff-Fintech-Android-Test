package com.example.fintechapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fintechapp.R
import com.example.fintechapp.Utils
import okhttp3.*
import java.io.IOException

class TabTopFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
//        print("\n TOP TAB")

        return inflater.inflate(R.layout.fragment_tab_best, container, false)
    }

}