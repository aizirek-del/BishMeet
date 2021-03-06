// Generated by view binder compiler. Do not edit!
package com.example.myapplication.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputEditText;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLoginBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final Button LoginsBtnEnter;

  @NonNull
  public final TextInputEditText LoginsPassword;

  @NonNull
  public final TextInputEditText editEmailLogin;

  @NonNull
  public final ImageButton prevoiusBtn;

  @NonNull
  public final TextView registerUser;

  private ActivityLoginBinding(@NonNull ScrollView rootView, @NonNull Button LoginsBtnEnter,
      @NonNull TextInputEditText LoginsPassword, @NonNull TextInputEditText editEmailLogin,
      @NonNull ImageButton prevoiusBtn, @NonNull TextView registerUser) {
    this.rootView = rootView;
    this.LoginsBtnEnter = LoginsBtnEnter;
    this.LoginsPassword = LoginsPassword;
    this.editEmailLogin = editEmailLogin;
    this.prevoiusBtn = prevoiusBtn;
    this.registerUser = registerUser;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Logins_btnEnter;
      Button LoginsBtnEnter = rootView.findViewById(id);
      if (LoginsBtnEnter == null) {
        break missingId;
      }

      id = R.id.LoginsPassword;
      TextInputEditText LoginsPassword = rootView.findViewById(id);
      if (LoginsPassword == null) {
        break missingId;
      }

      id = R.id.edit_Email_Login;
      TextInputEditText editEmailLogin = rootView.findViewById(id);
      if (editEmailLogin == null) {
        break missingId;
      }

      id = R.id.prevoius_btn;
      ImageButton prevoiusBtn = rootView.findViewById(id);
      if (prevoiusBtn == null) {
        break missingId;
      }

      id = R.id.registerUser;
      TextView registerUser = rootView.findViewById(id);
      if (registerUser == null) {
        break missingId;
      }

      return new ActivityLoginBinding((ScrollView) rootView, LoginsBtnEnter, LoginsPassword,
          editEmailLogin, prevoiusBtn, registerUser);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
