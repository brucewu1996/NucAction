// Generated by view binder compiler. Do not edit!
package com.google.mlkit.md.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.google.android.material.card.MaterialCardView;
import com.google.mlkit.md.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ProductsPreviewCardBinding implements ViewBinding {
  @NonNull
  private final MaterialCardView rootView;

  @NonNull
  public final ImageView cardImage;

  @NonNull
  public final TextView cardSubtitle;

  @NonNull
  public final TextView cardTitle;

  private ProductsPreviewCardBinding(@NonNull MaterialCardView rootView,
      @NonNull ImageView cardImage, @NonNull TextView cardSubtitle, @NonNull TextView cardTitle) {
    this.rootView = rootView;
    this.cardImage = cardImage;
    this.cardSubtitle = cardSubtitle;
    this.cardTitle = cardTitle;
  }

  @Override
  @NonNull
  public MaterialCardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ProductsPreviewCardBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ProductsPreviewCardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.products_preview_card, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ProductsPreviewCardBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.card_image;
      ImageView cardImage = rootView.findViewById(id);
      if (cardImage == null) {
        break missingId;
      }

      id = R.id.card_subtitle;
      TextView cardSubtitle = rootView.findViewById(id);
      if (cardSubtitle == null) {
        break missingId;
      }

      id = R.id.card_title;
      TextView cardTitle = rootView.findViewById(id);
      if (cardTitle == null) {
        break missingId;
      }

      return new ProductsPreviewCardBinding((MaterialCardView) rootView, cardImage, cardSubtitle,
          cardTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
