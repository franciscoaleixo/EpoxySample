package com.fa.epoxysample.others;

import android.content.res.ColorStateList;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.fa.epoxysample.R;
import java.text.DecimalFormat;

public class AppSecondaryInfoViewHolder {
  private final DecimalFormat oneDecimalFormatter;
  private final LinearLayout appcLayout;
  private final TextView appcText;
  private final LinearLayout ratingLayout;
  private final TextView rating;
  private final ImageView startImageView;

  public AppSecondaryInfoViewHolder(View itemView, DecimalFormat oneDecimalFormatter) {
    this.oneDecimalFormatter = oneDecimalFormatter;
    appcLayout = (LinearLayout) itemView.findViewById(R.id.appc_info_layout);
    appcText = (TextView) itemView.findViewById(R.id.appc_text);
    ratingLayout = (LinearLayout) itemView.findViewById(R.id.rating_info_layout);
    rating = (TextView) itemView.findViewById(R.id.rating_label);
    startImageView =  itemView.findViewById(R.id.star_imageview);
  }

  public void setInfo(boolean hasAppcBilling, float appRating, boolean showRating,
      boolean showBoth) {
    if (appcText != null) {
      appcText.setText(R.string.appc_card_short);
    }
    setRating(appRating);
    if (showBoth) {
      if (hasAppcBilling) {
        setAppcVisibility(View.VISIBLE);
      } else {
        setAppcVisibility(View.INVISIBLE);
      }
      setRatingVisibility(View.VISIBLE);
    } else {
      if (hasAppcBilling) {
        setAppcVisibility(View.VISIBLE);
        setRatingVisibility(View.INVISIBLE);
      } else if (showRating) {
        setAppcVisibility(View.INVISIBLE);
        setRatingVisibility(View.VISIBLE);
      } else {
        setAppcVisibility(View.INVISIBLE);
      }
    }
  }

  private void setAppcVisibility(int visibility) {
    if (appcLayout != null) {
      appcLayout.setVisibility(visibility);
    }
  }

  private void setRatingVisibility(int visibility) {
    if (ratingLayout != null) {
      ratingLayout.setVisibility(visibility);
    }
  }

  private void setRating(float rating) {
    if (this.rating != null) {
      if (rating == 0) {
        this.rating.setText(R.string.appcardview_title_no_stars);
      } else {
        this.rating.setText(oneDecimalFormatter.format(rating));
      }
    }
  }

  public void setColor(int color) {
    this.rating.setTextColor(color);
    this.appcText.setTextColor(color);
    if(startImageView != null){
      startImageView.setImageResource(R.drawable.ic_star_white);
    }
  }
}
