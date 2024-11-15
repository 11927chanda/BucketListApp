package com.example.bucketlistapp.login;

import androidx.lifecycle.ViewModelProvider;

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
import com.google.android.material.snackbar.Snackbar;

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;
    private LoginFragmentBinding binding;

    public static LoginFragment newInstance() {

        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       // return inflater.inflate(R.layout.login_fragment, container, false);
        binding = LoginFragmentBinding.inflate(inflater,container, false);
        return binding.getRoot();
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
//        // TODO: Use the ViewModel
//    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);

        //navigate to main page
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email =binding.loginEmailTIL.getEditText().getText().toString();
                String password = binding.loginPasswordTIL.getEditText().getText().toString();
                if(email.isBlank()){
                    Snackbar.make(view, "Email cannot be empty", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(password.isBlank()){
                    Snackbar.make(view, "Password cannot be empty", Snackbar.LENGTH_LONG).show();
                    return;
                }
                try{
                    mViewModel.validateCredential(email, password);
                }catch(UserNotFoundException e){
                    Snackbar.make(view, "User does not exist", Snackbar.LENGTH_LONG).show();
                    return;
                }catch(WrongPasswordException e){
                    Snackbar.make(view, "Wrong Password", Snackbar.LENGTH_LONG).show();
                    return;
                }
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_loginFragment_to_mainFragment);
            }
        });

        binding.signUpLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_loginFragment_to_signupFragment);

            }
        });
    }
}