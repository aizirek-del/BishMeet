// Generated by view binder compiler. Do not edit!
package com.example.myapplication.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputEditText;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityCreateNewEventBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final ImageView addEventImg;

  @NonNull
  public final AutoCompleteTextView autoComplete;

  @NonNull
  public final TextInputEditText dateformat;

  @NonNull
  public final TextInputEditText eventDescription;

  @NonNull
  public final TextInputEditText eventLocation;

  @NonNull
  public final TextInputEditText eventTitle;

  @NonNull
  public final ImageButton gobck;

  @NonNull
  public final TextInputEditText hourMinute;

  @NonNull
  public final Button initbtn;

  @NonNull
  public final Button selectFoto;

  private ActivityCreateNewEventBinding(@NonNull ScrollView rootView,
      @NonNull ImageView addEventImg, @NonNull AutoCompleteTextView autoComplete,
      @NonNull TextInputEditText dateformat, @NonNull TextInputEditText eventDescription,
      @NonNull TextInputEditText eventLocation, @NonNull TextInputEditText eventTitle,
      @NonNull ImageButton gobck, @NonNull TextInputEditText hourMinute, @NonNull Button initbtn,
      @NonNull Button selectFoto) {
    this.rootView = rootView;
    this.addEventImg = addEventImg;
    this.autoComplete = autoComplete;
    this.dateformat = dateformat;
    this.eventDescription = eventDescription;
    this.eventLocation = eventLocation;
    this.eventTitle = eventTitle;
    this.gobck = gobck;
    this.hourMinute = hourMinute;
    this.initbtn = initbtn;
    this.selectFoto = selectFoto;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityCreateNewEventBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityCreateNewEventBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_create_new_event, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityCreateNewEventBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.addEventImg;
      ImageView addEventImg = rootView.findViewById(id);
      if (addEventImg == null) {
        break missingId;
      }

      id = R.id.autoComplete;
      AutoCompleteTextView autoComplete = rootView.findViewById(id);
      if (autoComplete == null) {
        break missingId;
      }

      id = R.id.dateformat;
      TextInputEditText dateformat = rootView.findViewById(id);
      if (dateformat == null) {
        break missingId;
      }

      id = R.id.eventDescription;
      TextInputEditText eventDescription = rootView.findViewById(id);
      if (eventDescription == null) {
        break missingId;
      }

      id = R.id.event_location;
      TextInputEditText eventLocation = rootView.findViewById(id);
      if (eventLocation == null) {
        break missingId;
      }

      id = R.id.eventTitle;
      TextInputEditText eventTitle = rootView.findViewById(id);
      if (eventTitle == null) {
        break missingId;
      }

      id = R.id.gobck;
      ImageButton gobck = rootView.findViewById(id);
      if (gobck == null) {
        break missingId;
      }

      id = R.id.hour_minute;
      TextInputEditText hourMinute = rootView.findViewById(id);
      if (hourMinute == null) {
        break missingId;
      }

      id = R.id.initbtn;
      Button initbtn = rootView.findViewById(id);
      if (initbtn == null) {
        break missingId;
      }

      id = R.id.selectFoto;
      Button selectFoto = rootView.findViewById(id);
      if (selectFoto == null) {
        break missingId;
      }

      return new ActivityCreateNewEventBinding((ScrollView) rootView, addEventImg, autoComplete,
          dateformat, eventDescription, eventLocation, eventTitle, gobck, hourMinute, initbtn,
          selectFoto);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
