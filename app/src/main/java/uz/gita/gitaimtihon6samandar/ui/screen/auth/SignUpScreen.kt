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
import uz.gita.gitaimtihon6samandar.databinding.ScreenSaignUpAuthBinding
import uz.gita.gitaimtihon6samandar.presenter.viewmodel.SignUpViewModel
import uz.gita.gitaimtihon6samandar.presenter.viewmodel.impl.SignUpViewModelImpl

class SignUpScreen:Fragment(R.layout.screen_saign_up_auth) {
    private var _binding: ScreenSaignUpAuthBinding?=null
    private val binding get()=_binding!!

    private val viewModel:SignUpViewModel by viewModels<SignUpViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.signErrorUpLiveData.observe(this){
            withIcon(it)
        }
        viewModel.signInLivedata.observe(this){
            findNavController().navigate(SignUpScreenDirections.actionSignUpScreenToSignInScreen2())
        }
        viewModel.signUpViewModel.observe(this){
//            withIcon(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.signUpBtn.setOnClickListener {
            viewModel.signUp(binding.inputName.text.toString(),binding.inputPassword.text.toString(),binding.inputPassword1.text.toString())
        }
        binding.loginBtn.setOnClickListener {
            viewModel.signIn()
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= ScreenSaignUpAuthBinding.inflate(inflater,container,false)
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