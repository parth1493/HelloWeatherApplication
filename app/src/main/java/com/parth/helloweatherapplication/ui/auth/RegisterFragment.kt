package com.parth.helloweatherapplication.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.parth.helloweatherapplication.R
import com.parth.helloweatherapplication.databinding.FragmentLoginBinding
import com.parth.helloweatherapplication.databinding.FragmentRegisterBinding
import com.parth.helloweatherapplication.ui.auth.state.AuthStateEvent
import com.parth.helloweatherapplication.ui.auth.state.RegistrationFields
import com.parth.helloweatherapplication.util.ApiEmptyResponse
import com.parth.helloweatherapplication.util.ApiErrorResponse
import com.parth.helloweatherapplication.util.ApiSuccessResponse

class RegisterFragment : BaseAuthFragment() {

    private var _binding: FragmentRegisterBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "RegisterFragment: ${viewModel}")
        binding.registerButton.setOnClickListener {
            register()
        }
        subscribeObservers()
    }

    fun subscribeObservers(){
        viewModel.viewState.observe(viewLifecycleOwner, Observer{viewState ->
            viewState.registrationFields?.let {
                it.registration_email?.let{binding.inputEmail.setText(it)}
                it.registration_username?.let{binding.inputUsername.setText(it)}
                it.registration_password?.let{binding.inputPassword.setText(it)}
                it.registration_confirm_password?.let{binding.inputPasswordConfirm.setText(it)}
            }
        })
    }

    fun register(){
        viewModel.setStateEvent(
            AuthStateEvent.RegisterAttemptEvent(
                binding.inputEmail.text.toString(),
                binding.inputUsername.text.toString(),
                binding.inputPassword.text.toString(),
                binding.inputPasswordConfirm.text.toString()
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.setRegistrationFields(
            RegistrationFields(
                binding.inputEmail.text.toString(),
                binding.inputUsername.text.toString(),
                binding.inputPassword.text.toString(),
                binding.inputPasswordConfirm.text.toString()
            )
        )
        _binding = null
    }
}