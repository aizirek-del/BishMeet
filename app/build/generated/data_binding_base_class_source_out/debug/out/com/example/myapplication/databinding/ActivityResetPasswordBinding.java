// Generated by view binder compiler. Do not edit!
package com.example.myapplication.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputEditText;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityResetPasswordBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final ImageButton resetBackBtn;

  @NonNull
  public final TextInputEditText resetEmail;

  private ActivityResetPasswordBinding(@NonNull ScrollView rootView,
      @NonNull ImageButton resetBackBtn, @NonNull TextInputEditText resetEmail) {
    this.rootView = rootView;
    this.resetBackBtn = resetBackBtn;
    this.resetEmail = resetEmail;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityResetPasswordBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityResetPasswordBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_reset_password, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityResetPasswordBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.resetBack_btn;
      ImageButton resetBackBtn = rootView.findViewById(id);
      if (resetBackBtn == null) {
        break missingId;
      }

      id = R.id.resetEmail;
      TextInputEditText resetEmail = rootView.findViewById(id);
      if (resetEmail == null) {
        break missingId;
      }

      return new ActivityResetPasswordBinding((ScrollView) rootView, resetBackBtn, resetEmail);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
