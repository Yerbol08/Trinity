package com.enu.trinity.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.enu.trinity.R
import com.enu.trinity.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root



        val data = arrayListOf<HomeItems>()
        data.add(HomeItems("Манты паровые с говядиной", R.drawable.manty, "Рубленная говядина, завернутая в тонкое тесто 1 шт", "195 ₸"))
        data.add(HomeItems("Тефтели 1 шт.", R.drawable.teftelya, "Колобок из говяжьего фарша и риса под морковно-томатным соусом, подается со сметаной. 1 шт.", "385 ₸"))
        data.add(HomeItems("Куриная котлета на пару", R.drawable.kurinaya, "сочный и приготовленный на пару кружочек из куриного филе с овощами: кабачок, перец болгарский, лук, морковь", "385 ₸"))
        data.add(HomeItems("Котлета", R.drawable.kotleta_min, "Говядина, баранина, курица и секретные специи, как у бабушки!", "385 ₸"))
        data.add(HomeItems("Пельмени домашние", R.drawable.pelmeni, "пельмени из говядины и баранины, лепим сами!", "725 ₸"))
        data.add(HomeItems("Вареники с картофелем", R.drawable.vareniki, "Украшенные хрустящим жареным луком и политые топленным сливочным маслом.", "475 ₸"))

        val adapter = context?.let { HomeAdapter(data, it) }
        binding.recyclerView.adapter = adapter
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}