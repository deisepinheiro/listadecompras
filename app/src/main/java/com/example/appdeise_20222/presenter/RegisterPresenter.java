package com.example.appdeise_20222.presenter;

import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.appdeise_20222.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterPresenter implements RegisterPresenterContract.presenter{
    private RegisterPresenterContract.view activity;

    public RegisterPresenter(RegisterPresenterContract.view activity) {
        this.activity = activity;
    }


    @Override
    public void tryLogin(User user) {
        if(user.getNome().isEmpty() || user.getLogin().isEmpty() || user.getSenha().isEmpty()){
            activity.showSnakeBar("Preencha todos os campos");
        }else{
            cadastrarUsuario( user );
        }
    }

    private void cadastrarUsuario( User user){


        FirebaseApp.initializeApp(activity.getActivity());
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.getLogin(),user.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    salvarDadosUsuario(user.getLogin());
                    activity.showSnakeBar("Cadastro realizado com sucesso");
                    new Handler().postDelayed(() -> activity.getActivity().finish(), 1500);

                }else{
                    String erro;
                    try {
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        erro = "Digite uma senha com no mínimo 6 caracteres.";
                    }catch (FirebaseAuthUserCollisionException e){
                        erro = "Esta conta já foi cadastrada.";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        erro = "E-mail inválido.";
                    }catch (Exception e){
                        erro = "Erro ao cadastrar usuario.";
                    }
                    activity.showSnakeBar(erro);
                }
            }
        });

    }

    private void salvarDadosUsuario( String nome){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> usuarios = new HashMap<>();
        usuarios.put("nome", nome);

        String usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);
        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("db","Sucesso ao salvar os dados");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("db_error", "Erro ao salvar os dados" + e.toString());
                    }
                });
    }
}
