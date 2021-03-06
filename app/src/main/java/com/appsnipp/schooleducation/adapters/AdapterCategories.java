package com.appsnipp.schooleducation.adapters;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appsnipp.schooleducation.R;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * Adaptador de fotos para el recycler view
 */

public class AdapterCategories extends RecyclerView.Adapter<AdapterCategories.ViewHolder> {


    private int cartProductNumber = 0;
    //private List<Foto> mProductObject;


    //Added here temporary ArrayList
    private String[] mSelectedPosition ;
    private Context context;
    private boolean isAnimated;
    private boolean on_attach = true;
    long DURATION_IN_FADE_ID = 400;

    interface EscuchaEventosClick {
        void onItemClick(ViewHolder holder, int posicion);
    }

    private EscuchaEventosClick escucha;
    View view;

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        // Campos respectivos de un item
        public ImageView avatar;
        public TextView titulo;
        public ImageView imagen;
        public TextView noVisualizaciones;
        public TextView noLikes;
        public ImageView iconoFavorito;
        public TextView noComentarios;
        public ImageView select_favorito;


        public ViewHolder(View v) {
            super(v);

            //avatar = (ImageView) v.findViewById(R.id.avatar);
            titulo = (TextView) v.findViewById(R.id.titulo_imagen);
            imagen = (ImageView) v.findViewById(R.id.imagen);

            select_favorito = (ImageView) v.findViewById(R.id.select_favorito);

            //noVisualizaciones = (TextView) v.findViewById(R.id.noVisualizaciones);

            //iconoFavorito = (ImageView) v.findViewById(R.id.iconoFavorito);




            //v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            escucha.onItemClick(this, getAdapterPosition());
        }
    }

    public AdapterCategories(Context context, String[] mProductObject) {
        this.context = context;
        this.mSelectedPosition = mProductObject;

    }

    private void setAnimation(View itemView, int i) {
        if (!on_attach) {
            i = -1;
        }
        boolean isNotFirstItem = i == -1;
        i++;
        itemView.setAlpha(0.f);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(itemView, "alpha", 0.f, 0.5f, 1.0f);
        ObjectAnimator.ofFloat(itemView, "alpha", 0.f).start();
        animator.setStartDelay(isNotFirstItem ? DURATION_IN_FADE_ID / 2 : (i * DURATION_IN_FADE_ID / 3));
        animator.setDuration(500);
        animatorSet.play(animator);
        animator.start();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                Log.d(TAG, "onScrollStateChanged: Called " + newState);
                on_attach = false;
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return mSelectedPosition.length;
    }

//    @Override
//    public long getItemId(int position) {
//        return position;
//    }


    @Override
    public long getItemId(int position) {
        //String product = mSelectedPosition.length();
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_galeria_fotos, viewGroup, false);


//        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());
//        view = mInflater.inflate(R.layout.item_galeria_fotos, viewGroup, false);
//
//        final ViewHolder productHolder = new ViewHolder(view);
//        productHolder.select_favorito.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = productHolder.getAdapterPosition();
//                //Toast.makeText(context,"Item at position "+position+" deleted",Toast.LENGTH_SHORT).show();
//                //mProductObject.remove(position);
//                notifyDataSetChanged();
//            }
//        });

        return new ViewHolder(v);
//        return productHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        final int position = viewHolder.getAdapterPosition();

        //Set ViewTag
        viewHolder.itemView.setTag(position);

        final String itemActual = mSelectedPosition[position];

        int esFavorito= 0;
        //Glide.with(viewHolder.avatar.getContext())
        //        .load(itemActual.getIdAvatarUsuario())
        //        .into(viewHolder.avatar);
        viewHolder.titulo.setText(itemActual);

        setAnimation(viewHolder.itemView, i);
//        Glide.with(viewHolder.imagen.getContext())
//                .load(itemActual.getIdImagen())
//                .into(viewHolder.imagen);


    }






}
