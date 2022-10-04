package uz.gita.gitaimtihon6samandar.ui.screen.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tapadoo.alerter.Alerter
import uz.gita.gitaimtihon6samandar.R
import uz.gita.gitaimtihon6samandar.databinding.ScreenSiginInAuthBinding
import uz.gita.gitaimtihon6samandar.presenter.viewmodel.SignInViewModel
import uz.gita.gitaimtihon6samandar.presenter.viewmodel.impl.SignInViewModelImpl

class SignInScreen:Fragment(R.layout.screen_sigin_in_auth) {
    private var _binding:ScreenSiginInAuthBinding?=null
    private val binding get()=_binding!!

    private val  viewModel : SignInViewModel by viewModels<SignInViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loginViewModelLiveData.observe(this){
            withIcon(it)
        }
        viewModel.signUpLiveData.observe(this){
            findNavController().navigate(SignInScreenDirections.actionSignInScreenToSignUpScreen())
        }
        viewModel.loginMainScreenViewModelLiveData.observe(this){
            findNavController().navigate(SignInScreenDirections.actionSignInScreenToMainScreen(it))

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.signUpBtn.setOnClickListener {
            viewModel.signUp()
        }
        binding.loginBtn.setOnClickListener {
            viewModel.login(binding.inputName.text.toString(),binding.inputPassword1.text.toString())
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=ScreenSiginInAuthBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
    fun withIcon(text:String) {
        Alerter.create(requireActivity())
            .setText(text)
            .setIcon(R.drawable.notification)
            .setBackgroundColorRes(R.color.tab_layout_bg)

            .show()
    }
}