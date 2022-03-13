package com.example.ui.sign;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.R;
import com.example.databinding.FragmentLogInBinding;
import com.example.ui.main.PostViewModel;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogInFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FragmentLogInBinding binding;
    private NavController navController;
    PostViewModel postViewModel;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LogInFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LogInFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LogInFragment newInstance(String param1, String param2) {
        LogInFragment fragment = new LogInFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        postViewModel = new ViewModelProvider(this , ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(PostViewModel.class);
        postViewModel.getUser().observe(this, new Observer<FirebaseUser>() {
             @Override
             public void onChanged(FirebaseUser firebaseUser) {
                 // if there is user , go to home page
                 if (firebaseUser!=null) {
                     Log.d("TAG", "Navegate from LogIn to home: ");
                     navController.navigate(R.id.action_logInFragment_to_homeFragment);
                 }
             }
         });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLogInBinding.inflate(inflater,container,false);
        return  binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        // navigation to register fragment
        binding.btnFGoReg.setOnClickListener(v->{ navController.navigate(R.id.action_logInFragment_to_registerFragment);
        });
        // login viewmodel ,database
        binding.btnFLogin.setOnClickListener(v->{
            Log.d("TAG", "button Navegate from LogIn to register: ");
            postViewModel.logIn(binding.etFEmailLog.getText().toString(),binding.etFPasswordLog.getText().toString());
        });
    }
}