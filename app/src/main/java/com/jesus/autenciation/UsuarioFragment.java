package com.jesus.autenciation;


import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
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


        RequestQueue colaPeticiones = Volley.newRequestQueue(getActivity().getApplicationContext());

        ImageLoader lectorImagenes = new ImageLoader(colaPeticiones, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(10);
            public void putBitmap(String url, Bitmap bitmap)
            {
                cache.put(url, bitmap);
            }
            public Bitmap getBitmap(String url)
            {
                return cache.get(url);
            }
        });


        Uri urlImagen = usuario.getPhotoUrl();
        if (urlImagen != null)
        {
            NetworkImageView fotoUsuario = (NetworkImageView) vista.findViewById(R.id.imagen);
            fotoUsuario.setImageUrl(urlImagen.toString(), lectorImagenes);
        }

        return vista;
    }



}
