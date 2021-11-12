// Generated by view binder compiler. Do not edit!
package com.example.myapplication.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHomeBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final Button WillGoBtn;

  @NonNull
  public final TextView events;

  @NonNull
  public final FloatingActionButton fabButton;

  @NonNull
  public final RecyclerView horizontalRecyclerView;

  @NonNull
  public final ProgressBar inProgress;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final ProgressBar progressBAr;

  @NonNull
  public final RecyclerView verticalRecyclerView;

  @NonNull
  public final View view;

  @NonNull
  public final Button wholeList;

  private FragmentHomeBinding(@NonNull RelativeLayout rootView, @NonNull Button WillGoBtn,
      @NonNull TextView events, @NonNull FloatingActionButton fabButton,
      @NonNull RecyclerView horizontalRecyclerView, @NonNull ProgressBar inProgress,
      @NonNull LinearLayout linearLayout, @NonNull ProgressBar progressBAr,
      @NonNull RecyclerView verticalRecyclerView, @NonNull View view, @NonNull Button wholeList) {
    this.rootView = rootView;
    this.WillGoBtn = WillGoBtn;
    this.events = events;
    this.fabButton = fabButton;
    this.horizontalRecyclerView = horizontalRecyclerView;
    this.inProgress = inProgress;
    this.linearLayout = linearLayout;
    this.progressBAr = progressBAr;
    this.verticalRecyclerView = verticalRecyclerView;
    this.view = view;
    this.wholeList = wholeList;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Will_goBtn;
      Button WillGoBtn = rootView.findViewById(id);
      if (WillGoBtn == null) {
        break missingId;
      }

      id = R.id.events;
      TextView events = rootView.findViewById(id);
      if (events == null) {
        break missingId;
      }

      id = R.id.fab_button;
      FloatingActionButton fabButton = rootView.findViewById(id);
      if (fabButton == null) {
        break missingId;
      }

      id = R.id.horizontal_recycler_view;
      RecyclerView horizontalRecyclerView = rootView.findViewById(id);
      if (horizontalRecyclerView == null) {
        break missingId;
      }

      id = R.id.in_progress;
      ProgressBar inProgress = rootView.findViewById(id);
      if (inProgress == null) {
        break missingId;
      }

      id = R.id.linearLayout;
      LinearLayout linearLayout = rootView.findViewById(id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.progressBAr;
      ProgressBar progressBAr = rootView.findViewById(id);
      if (progressBAr == null) {
        break missingId;
      }

      id = R.id.vertical_recycler_view;
      RecyclerView verticalRecyclerView = rootView.findViewById(id);
      if (verticalRecyclerView == null) {
        break missingId;
      }

      id = R.id.view;
      View view = rootView.findViewById(id);
      if (view == null) {
        break missingId;
      }

      id = R.id.wholeList;
      Button wholeList = rootView.findViewById(id);
      if (wholeList == null) {
        break missingId;
      }

      return new FragmentHomeBinding((RelativeLayout) rootView, WillGoBtn, events, fabButton,
          horizontalRecyclerView, inProgress, linearLayout, progressBAr, verticalRecyclerView, view,
          wholeList);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
