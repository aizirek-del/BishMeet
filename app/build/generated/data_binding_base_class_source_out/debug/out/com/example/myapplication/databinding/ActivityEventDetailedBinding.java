// Generated by view binder compiler. Do not edit!
package com.example.myapplication.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public final class ActivityEventDetailedBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final ImageButton bBtn;

  @NonNull
  public final TextView descriptionEventDetailed;

  @NonNull
  public final ImageView detLocat;

  @NonNull
  public final ImageView fotoEventDetailed;

  @NonNull
  public final ImageView grImageInEvDetailed;

  @NonNull
  public final TextView grTitleInEvDetailed;

  @NonNull
  public final Button joinToEvent;

  @NonNull
  public final TextView locationOfEventDetailed;

  @NonNull
  public final TextView membersNum;

  @NonNull
  public final ShapeableImageView pFoto;

  @NonNull
  public final ShapeableImageView parFoto;

  @NonNull
  public final ShapeableImageView psfoto;

  @NonNull
  public final TextView timeOfEventDetailed;

  @NonNull
  public final TextView titleOfEventDetailed;

  private ActivityEventDetailedBinding(@NonNull ScrollView rootView, @NonNull ImageButton bBtn,
      @NonNull TextView descriptionEventDetailed, @NonNull ImageView detLocat,
      @NonNull ImageView fotoEventDetailed, @NonNull ImageView grImageInEvDetailed,
      @NonNull TextView grTitleInEvDetailed, @NonNull Button joinToEvent,
      @NonNull TextView locationOfEventDetailed, @NonNull TextView membersNum,
      @NonNull ShapeableImageView pFoto, @NonNull ShapeableImageView parFoto,
      @NonNull ShapeableImageView psfoto, @NonNull TextView timeOfEventDetailed,
      @NonNull TextView titleOfEventDetailed) {
    this.rootView = rootView;
    this.bBtn = bBtn;
    this.descriptionEventDetailed = descriptionEventDetailed;
    this.detLocat = detLocat;
    this.fotoEventDetailed = fotoEventDetailed;
    this.grImageInEvDetailed = grImageInEvDetailed;
    this.grTitleInEvDetailed = grTitleInEvDetailed;
    this.joinToEvent = joinToEvent;
    this.locationOfEventDetailed = locationOfEventDetailed;
    this.membersNum = membersNum;
    this.pFoto = pFoto;
    this.parFoto = parFoto;
    this.psfoto = psfoto;
    this.timeOfEventDetailed = timeOfEventDetailed;
    this.titleOfEventDetailed = titleOfEventDetailed;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityEventDetailedBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityEventDetailedBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_event_detailed, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityEventDetailedBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.b_btn;
      ImageButton bBtn = ViewBindings.findChildViewById(rootView, id);
      if (bBtn == null) {
        break missingId;
      }

      id = R.id.description_event_detailed;
      TextView descriptionEventDetailed = ViewBindings.findChildViewById(rootView, id);
      if (descriptionEventDetailed == null) {
        break missingId;
      }

      id = R.id.det_locat;
      ImageView detLocat = ViewBindings.findChildViewById(rootView, id);
      if (detLocat == null) {
        break missingId;
      }

      id = R.id.foto_event_detailed;
      ImageView fotoEventDetailed = ViewBindings.findChildViewById(rootView, id);
      if (fotoEventDetailed == null) {
        break missingId;
      }

      id = R.id.gr_image_in_evDetailed;
      ImageView grImageInEvDetailed = ViewBindings.findChildViewById(rootView, id);
      if (grImageInEvDetailed == null) {
        break missingId;
      }

      id = R.id.gr_title_in_evDetailed;
      TextView grTitleInEvDetailed = ViewBindings.findChildViewById(rootView, id);
      if (grTitleInEvDetailed == null) {
        break missingId;
      }

      id = R.id.join_to_event;
      Button joinToEvent = ViewBindings.findChildViewById(rootView, id);
      if (joinToEvent == null) {
        break missingId;
      }

      id = R.id.location_of_event_detailed;
      TextView locationOfEventDetailed = ViewBindings.findChildViewById(rootView, id);
      if (locationOfEventDetailed == null) {
        break missingId;
      }

      id = R.id.membersNum;
      TextView membersNum = ViewBindings.findChildViewById(rootView, id);
      if (membersNum == null) {
        break missingId;
      }

      id = R.id.p_foto;
      ShapeableImageView pFoto = ViewBindings.findChildViewById(rootView, id);
      if (pFoto == null) {
        break missingId;
      }

      id = R.id.par_foto;
      ShapeableImageView parFoto = ViewBindings.findChildViewById(rootView, id);
      if (parFoto == null) {
        break missingId;
      }

      id = R.id.psfoto;
      ShapeableImageView psfoto = ViewBindings.findChildViewById(rootView, id);
      if (psfoto == null) {
        break missingId;
      }

      id = R.id.time_of_event_detailed;
      TextView timeOfEventDetailed = ViewBindings.findChildViewById(rootView, id);
      if (timeOfEventDetailed == null) {
        break missingId;
      }

      id = R.id.title_of_event_detailed;
      TextView titleOfEventDetailed = ViewBindings.findChildViewById(rootView, id);
      if (titleOfEventDetailed == null) {
        break missingId;
      }

      return new ActivityEventDetailedBinding((ScrollView) rootView, bBtn, descriptionEventDetailed,
          detLocat, fotoEventDetailed, grImageInEvDetailed, grTitleInEvDetailed, joinToEvent,
          locationOfEventDetailed, membersNum, pFoto, parFoto, psfoto, timeOfEventDetailed,
          titleOfEventDetailed);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}