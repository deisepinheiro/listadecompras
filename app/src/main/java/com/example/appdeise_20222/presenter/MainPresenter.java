package com.example.appdeise_20222.presenter;

import android.content.Intent;

import com.example.appdeise_20222.broadcast.MyBroadcastReceiver;
import com.example.appdeise_20222.view.ListaActivity;
import com.example.appdeise_20222.view.LoginActivity;
import com.example.appdeise_20222.view.ModelosActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainPresenter implements MainPresenterContract.presenter{

    private MainPresenterContract.view activity;

    public MainPresenter(MainPresenterContract.view activity) {
        this.activity = activity;
    }

    @Override
    public void startListaActivity() {
        Intent intent = new Intent(activity.getActivity(), ListaActivity.class);
        activity.getActivity().startActivity(intent);
    }

    @Override
    public void startModelosActivity() {
        Intent intent = new Intent(activity.getActivity(), ModelosActivity.class);
        activity.getActivity().startActivity(intent);
    }

    @Override
    public void logoff() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(activity.getActivity(), LoginActivity.class);
        activity.getActivity().startActivity(intent);
        activity.getActivity().finish();
    }

    @Override
    public void getUserInfo() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(userID);
        documentReference.addSnapshotListener((documentSnapshot, error) -> {
            if(documentSnapshot != null){
                activity.setViews(documentSnapshot.getString("nome"), email);
            }
        });
    }
}
