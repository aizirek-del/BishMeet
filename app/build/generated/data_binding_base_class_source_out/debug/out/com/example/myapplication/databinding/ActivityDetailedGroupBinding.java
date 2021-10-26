// Generated by view binder compiler. Do not edit!
package com.example.myapplication.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.myapplication.R;
import com.google.android.material.imageview.ShapeableImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDetailedGroupBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final ImageButton backButton;

  @NonNull
  public final TextView cardsTextTitle;

  @NonNull
  public final TextView categoryClub;

  @NonNull
  public final TextView descriptionClub;

  @NonNull
  public final ImageView detCardImageView;

  @NonNull
  public final ImageView imgofClub;

  @NonNull
  public final TextView interestOfClub;

  @NonNull
  public final TextView membersNum;

  @NonNull
  public final ShapeableImageView partFt;

  @NonNull
  public final ShapeableImageView partcpsFoto;

  @NonNull
  public final ShapeableImageView sFoto;

  @NonNull
  public final TextView titleOfclub;

  private ActivityDetailedGroupBinding(@NonNull ScrollView rootView,
      @NonNull ImageButton backButton, @NonNull TextView cardsTextTitle,
      @NonNull TextView categoryClub, @NonNull TextView descriptionClub,
      @NonNull ImageView detCardImageView, @NonNull ImageView imgofClub,
      @NonNull TextView interestOfClub, @NonNull TextView membersNum,
      @NonNull ShapeableImageView partFt, @NonNull ShapeableImageView partcpsFoto,
      @NonNull ShapeableImageView sFoto, @NonNull TextView titleOfclub) {
    this.rootView = rootView;
    this.backButton = backButton;
    this.cardsTextTitle = cardsTextTitle;
    this.categoryClub = categoryClub;
    this.descriptionClub = descriptionClub;
    this.detCardImageView = detCardImageView;
    this.imgofClub = imgofClub;
    this.interestOfClub = interestOfClub;
    this.membersNum = membersNum;
    this.partFt = partFt;
    this.partcpsFoto = partcpsFoto;
    this.sFoto = sFoto;
    this.titleOfclub = titleOfclub;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDetailedGroupBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDetailedGroupBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_detailed_group, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDetailedGroupBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.back_button;
      ImageButton backButton = ViewBindings.findChildViewById(rootView, id);
      if (backButton == null) {
        break missingId;
      }

      id = R.id.cards_text_title;
      TextView cardsTextTitle = ViewBindings.findChildViewById(rootView, id);
      if (cardsTextTitle == null) {
        break missingId;
      }

      id = R.id.categoryClub;
      TextView categoryClub = ViewBindings.findChildViewById(rootView, id);
      if (categoryClub == null) {
        break missingId;
      }

      id = R.id.descriptionClub;
      TextView descriptionClub = ViewBindings.findChildViewById(rootView, id);
      if (descriptionClub == null) {
        break missingId;
      }

      id = R.id.det_card_image_view;
      ImageView detCardImageView = ViewBindings.findChildViewById(rootView, id);
      if (detCardImageView == null) {
        break missingId;
      }

      id = R.id.imgofClub;
      ImageView imgofClub = ViewBindings.findChildViewById(rootView, id);
      if (imgofClub == null) {
        break missingId;
      }

      id = R.id.interestOfClub;
      TextView interestOfClub = ViewBindings.findChildViewById(rootView, id);
      if (interestOfClub == null) {
        break missingId;
      }

      id = R.id.membersNum;
      TextView membersNum = ViewBindings.findChildViewById(rootView, id);
      if (membersNum == null) {
        break missingId;
      }

      id = R.id.part_ft;
      ShapeableImageView partFt = ViewBindings.findChildViewById(rootView, id);
      if (partFt == null) {
        break missingId;
      }

      id = R.id.partcps_foto;
      ShapeableImageView partcpsFoto = ViewBindings.findChildViewById(rootView, id);
      if (partcpsFoto == null) {
        break missingId;
      }

      id = R.id.sFoto;
      ShapeableImageView sFoto = ViewBindings.findChildViewById(rootView, id);
      if (sFoto == null) {
        break missingId;
      }

      id = R.id.titleOfclub;
      TextView titleOfclub = ViewBindings.findChildViewById(rootView, id);
      if (titleOfclub == null) {
        break missingId;
      }

      return new ActivityDetailedGroupBinding((ScrollView) rootView, backButton, cardsTextTitle,
          categoryClub, descriptionClub, detCardImageView, imgofClub, interestOfClub, membersNum,
          partFt, partcpsFoto, sFoto, titleOfclub);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
