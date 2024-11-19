package com.example.bucketlistapp.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bucketlistapp.R;
import com.example.bucketlistapp.User;
import com.example.bucketlistapp.databinding.LoginFragmentBinding;
import com.example.bucketlistapp.databinding.SignupFragmentBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;


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
        LoginViewModel loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);

        //navigate to main page
        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname =binding.signupFnameTIL.getEditText().getText().toString();
                String lastname =binding.signupLnameTIL.getEditText().getText().toString();
                String email =binding.signupEmailTIL.getEditText().getText().toString();
                String password = binding.signupPasswordTIL.getEditText().getText().toString();
                String confirmPassword =binding.signupCfmPasswordTIL.getEditText().getText().toString();
                if(firstname.isBlank()){
                    Snackbar.make(view, "Insert your first name", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(lastname.isBlank()){
                    Snackbar.make(view, "Insert your last name", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(email.isBlank()){
                    Snackbar.make(view, "Insert your  email", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(!email.contains("@")||!email.contains(".")){
                    Snackbar.make(view, "Invalid email", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(password.isBlank()){
                    Snackbar.make(view, "Insert your  password", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(password.length()<8){
                    Snackbar.make(view, "Password must contain at least 8 characters", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(confirmPassword.isBlank()){
                    Snackbar.make(view, "Rewrite your password", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(!password.equals(confirmPassword)){
                    Snackbar.make(view, "Password doesn't match", Snackbar.LENGTH_LONG).show();
                    return;
                }
                User user = new User(email, password, firstname, lastname, new Date());

                if(loginViewModel.userExists(user)){
                    Snackbar.make(view,"User already exists", Snackbar.LENGTH_LONG).show();
                }
                loginViewModel.registerUser(user);

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