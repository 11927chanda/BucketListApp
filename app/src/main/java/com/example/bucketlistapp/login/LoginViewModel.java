package com.example.bucketlistapp.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.bucketlistapp.BucketListRepository;
import com.example.bucketlistapp.User;

// TODO: Implement the ViewModel
public class LoginViewModel extends AndroidViewModel {
    //business model
    private User loggedInUser;
    private BucketListRepository bucketListRepository;
    public LoginViewModel(@NonNull Application application) {
        super(application);

        //get repository
        bucketListRepository = new BucketListRepository(application);
    }
    public User getLoggedInUser(){
        return loggedInUser;
    }
//    public void insert(User user){
//        BucketListRepository.insert(user);
//    }

   public void validateCredential(String email, String password) throws UserNotFoundException, WrongPasswordException{
       //findUserByEmail
       User userByEmail = findUserByEmail(email);
       if(userByEmail != null){
//           user exists
           if(userByEmail.getPassword().equals(password)){
               loggedInUser = userByEmail;
           }else{
               throw new WrongPasswordException("Wrong password");
               }
       }

       else{
           throw new UserNotFoundException("User does not exist");
       }
   }
   public User findUserByEmail(String email){
        return bucketListRepository.findUserByEmail(email);
   }

   public String fullname(User user){
        return user.getFirstname().concat(" ").concat(user.getLastname());
   }
   public boolean userExists(User user){
        boolean exists = Boolean.FALSE;
        User existingUser = findUserByEmail(user.getEmail());
        if(existingUser != null){
            exists = Boolean.TRUE;
        }
        return  exists;
   }

//   public void registerUser(User user){
//        insert(user);
//        loggedInUser = user;
//   }

}