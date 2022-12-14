package uz.gita.gitaimtihon6samandar.ui.pages.intro_pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.gita.gitaimtihon6samandar.R
import uz.gita.gitaimtihon6samandar.data.models.IntroData
import uz.gita.gitaimtihon6samandar.databinding.PageIntroBinding

class IntroPage: Fragment(R.layout.page_intro) {

    private var _binding:PageIntroBinding?=null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       val data=arguments?.get("data")as IntroData
        binding.imageIntro.setImageResource(data.image)
        binding.description.text=data.description
        binding.title.text=data.description
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding=PageIntroBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}