package uz.gita.gitaimtihon6samandar.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle
import uz.gita.gitaimtihon6samandar.R
import uz.gita.gitaimtihon6samandar.data.models.IntroData
import uz.gita.gitaimtihon6samandar.databinding.ScreenIntroBinding
import uz.gita.gitaimtihon6samandar.presenter.viewmodel.IntroScreenViewModel
import uz.gita.gitaimtihon6samandar.presenter.viewmodel.SplashScreenViewModel
import uz.gita.gitaimtihon6samandar.presenter.viewmodel.impl.IntroScreenViewModelImpl
import uz.gita.gitaimtihon6samandar.presenter.viewmodel.impl.SplashScreenViewModelImpl
import uz.gita.gitaimtihon6samandar.ui.adapter.IntroAdapter

class IntroScreen:Fragment(R.layout.screen_intro) {
    private var _binding:ScreenIntroBinding?=null
    private val binding get() = _binding!!
    private val list=(arrayListOf<IntroData>(
        IntroData(R.drawable.intro1,"Intro1","Intro description1"),
        IntroData(R.drawable.intro2,"Intro2","Intro description2"),
        IntroData(R.drawable.intro3,"Intro3","Intro description3")
    ))
    private val adapter:IntroAdapter by lazy { IntroAdapter(this,list)}

    private val viewModel:IntroScreenViewModel by viewModels<IntroScreenViewModelImpl>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.openMainScreenLiveData.observe(this){
            findNavController().navigate(IntroScreenDirections.actionIntroScreenToSignInScreen())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.introViewpager.adapter=adapter
        binding.indicatorView.apply {
            setSliderColor(R.color.purple_500, R.color.teal_700)
            setSliderWidth(resources.getDimension(R.dimen.dp_10))
            setSliderHeight(resources.getDimension(R.dimen.dp_5))
            setSlideMode(IndicatorSlideMode.WORM)
            setIndicatorStyle(IndicatorStyle.CIRCLE)
            setPageSize(binding.introViewpager.adapter!!.itemCount)
            notifyDataChanged()
        }
        binding.introViewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                binding.indicatorView.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position==2){
                    binding.nextBtn.text="Go"
                }else{
                    binding.nextBtn.text="Next"
                }
                binding.indicatorView.onPageSelected(position)
            }
        })
        viewModel.nextPageOpenLiveData.observe(viewLifecycleOwner){
            binding.introViewpager.currentItem = it
        }
        binding.nextBtn.setOnClickListener {
            viewModel.next(binding.introViewpager.currentItem)
        }
        binding.skipBtn.setOnClickListener {
            viewModel.next(2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=ScreenIntroBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}