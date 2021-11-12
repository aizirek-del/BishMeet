// Generated by view binder compiler. Do not edit!
package com.example.myapplication.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.example.myapplication.R;
import com.google.android.material.imageview.ShapeableImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ListLayoutBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final CardView cardViewGroup;

  @NonNull
  public final ShapeableImageView ivOfClubList;

  @NonNull
  public final TextView txtView;

  private ListLayoutBinding(@NonNull CardView rootView, @NonNull CardView cardViewGroup,
      @NonNull ShapeableImageView ivOfClubList, @NonNull TextView txtView) {
    this.rootView = rootView;
    this.cardViewGroup = cardViewGroup;
    this.ivOfClubList = ivOfClubList;
    this.txtView = txtView;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ListLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ListLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.list_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ListLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      CardView cardViewGroup = (CardView) rootView;

      id = R.id.ivOfClubList;
      ShapeableImageView ivOfClubList = rootView.findViewById(id);
      if (ivOfClubList == null) {
        break missingId;
      }

      id = R.id.txtView;
      TextView txtView = rootView.findViewById(id);
      if (txtView == null) {
        break missingId;
      }

      return new ListLayoutBinding((CardView) rootView, cardViewGroup, ivOfClubList, txtView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
