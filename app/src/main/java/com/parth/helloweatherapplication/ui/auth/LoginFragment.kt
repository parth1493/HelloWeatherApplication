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
import com.parth.helloweatherapplication.model.AuthToken
import com.parth.helloweatherapplication.ui.auth.state.LoginFields
import com.parth.helloweatherapplication.util.ApiEmptyResponse
import com.parth.helloweatherapplication.util.ApiErrorResponse
import com.parth.helloweatherapplication.util.ApiSuccessResponse
import com.parth.helloweatherapplication.util.Constants


class LoginFragment : BaseAuthFragment() {

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "LoginFragment: ${viewModel}")

        subscribeObservers()

        binding.loginButton.setOnClickListener {

            // Check network connection
            if (Constants.isNetworkConnected){
                Log.i("Auth Activity", "Internet available")
            }else{
                Log.i("Auth Activity", "Internet not available")
            }
            viewModel.setAuthToken(
                AuthToken(
                    1,
                    "gdfngidfng4nt43n43jn34jn"
                )
            )
        }
    }

    fun subscribeObservers(){
        viewModel.viewState.observe(viewLifecycleOwner, Observer{
            it.loginFields?.let{
                it.login_email?.let{binding.inputEmail.setText(it)}
                it.login_password?.let{binding.inputPassword.setText(it)}
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.setLoginFields(
            LoginFields(
                binding.inputEmail.text.toString(),
                binding.inputPassword.text.toString()
            )
        )
        _binding = null
    }
}