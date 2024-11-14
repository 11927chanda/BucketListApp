package com.example.bucketlistapp.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bucketlistapp.R;
import com.example.bucketlistapp.databinding.LoginFragmentBinding;
import com.example.bucketlistapp.databinding.SignupFragmentBinding;


public class SignupFragment extends Fragment {

    private LoginViewModel mViewModel;
    private SignupFragmentBinding binding;

    public static SignupFragment newInstance() {

        return new SignupFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // return inflater.inflate(R.layout.login_fragment, container, false);
        binding = SignupFragmentBinding.inflate(inflater,container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //navigate to main page
        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_signupFragment_to_mainFragment);
            }
        });
        //navigate to login
        binding.loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_signupFragment_to_loginFragment);
            }
        });
    }
}