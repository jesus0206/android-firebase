package com.jesus.autenciation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UsuarioFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View vista=inflater.inflate(R.layout.activity_fragment_usuario,container,false);

        FirebaseUser usuario= FirebaseAuth.getInstance().getCurrentUser();

        TextView nombre=(TextView) vista.findViewById(R.id.nombre);
        nombre.setText(usuario.getDisplayName());

        TextView email=(TextView) vista.findViewById(R.id.email);
        email.setText(usuario.getEmail());

        TextView number=(TextView) vista.findViewById(R.id.number);
        number.setText(usuario.getPhoneNumber());

        TextView proveedor=(TextView) vista.findViewById(R.id.proveedor);
        proveedor.setText(usuario.getProviders().get(0));

        return vista;

    }
}
